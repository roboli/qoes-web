(ns qoes.core
  (:import [goog.async Debouncer])
  (:require [reagent.core :as r]
            ["@material-ui/core" :as mui]
            ["@material-ui/core/styles" :refer [withStyles]]
            ["@material-ui/icons" :as mui-icons]
            [qoes.theme :refer [custom-theme]]
            [qoes.number :refer [number-text]]
            [qoes.phone :refer [phone]]
            [qoes.operators :as ops]
            [qoes.logo :refer [op-logo]]))

(def state (r/atom {:phone-number ""
                    :operator ops/UNKNOWN}))

(defn custom-styles [theme]
  #js {:spacer #js {:height (* (.. theme -spacing -unit) 8)}
       :top-divider #js {:margin-bottom (* (.. theme -spacing -unit) 2)}
       :bottom-divider #js {:margin-top (* (.. theme -spacing -unit) 2)
                            :margin-bottom (* (.. theme -spacing -unit) 3)}})

(def with-custom-styles (withStyles custom-styles))

(defn app []
  [:> mui/AppBar
    [:> mui/Toolbar
     [:> mui/Typography {:variant "title"
                         :color "inherit"}
      "Que operador es?"]]])

(defn spacer [{:keys [classes] :as props}]
  [:div {:class (.-spacer classes)}])

(defn divider [{:keys [classes pos] :as props}]
  [:> mui/Divider {:class (aget classes pos)}])

(defn debounce [f interval]
  (let [dbnc (Debouncer. f interval)]
    ;; We use apply here to support functions of various arities
    (fn [& args] (.apply (.-fire dbnc) dbnc (to-array args)))))

(defn operator-logo []
  [op-logo (:operator @state)])

(defn drop-last-str [v]
  (apply str (drop-last (vec v))))

(defn upd-operator [num]
  (.log js/console num)
  (swap! state assoc-in [:operator] (ops/identify-op (subs num 0 4))))

(def update-operator
  (debounce upd-operator 500))

(defn rm-number []
  (swap! state update-in [:phone-number] drop-last-str)
  (update-operator (:phone-number @state)))

(defn update-number [num]
  (swap! state update-in [:phone-number] str num)
  (update-operator (:phone-number @state)))

(defn main []
  [:> mui/MuiThemeProvider
   {:theme custom-theme}
   [app]
   [:> (with-custom-styles (r/reactify-component spacer))]
   [operator-logo]
   [:> (with-custom-styles (r/reactify-component divider)) {:pos "top-divider"}]
   [number-text (:phone-number @state) rm-number]
   [:> (with-custom-styles (r/reactify-component divider)) {:pos "bottom-divider"}]
   [phone update-number]])

(r/render [main]
          (js/document.getElementById "app"))
