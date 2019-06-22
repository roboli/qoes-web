(ns qoes.core
  (:import [goog.async Debouncer])
  (:require [reagent.core :as r]
            [cljsjs.material-ui]
            [qoes.theme :refer [custom-theme]]
            [qoes.number :refer [number-text]]
            [qoes.phone :refer [phone]]
            [qoes.operators :as ops]
            [qoes.logo :refer [op-logo]]))

(def state (r/atom {:phone-number ""
                    :operator ops/UNKNOWN}))

(defn custom-styles [theme]
  #js {:spacer #js {:height (.spacing theme 8)}
       :top-divider #js {:margin-bottom (.spacing theme 2)}
       :bottom-divider #js {:margin-top (.spacing theme 2)
                            :margin-bottom (.spacing theme 3)}})

(def with-custom-styles (js/MaterialUI.withStyles custom-styles))

(defn spacer [{:keys [classes] :as props}]
  [:div {:class (aget classes "spacer")}])

(defn divider [{:keys [classes pos] :as props}]
  [:> js/MaterialUI.Divider {:class (aget classes pos)}])

(defn debounce [f interval]
  (let [dbnc (Debouncer. f interval)]
    ;; We use apply here to support functions of various arities
    (fn [& args] (.apply (.-fire dbnc) dbnc (to-array args)))))

(defn operator-logo []
  [op-logo (:operator @state)])

(defn drop-last-str [v]
  (apply str (drop-last (vec v))))

(defn upd-operator [num]
  (swap! state assoc-in [:operator] (if (and (>= (count num) 4) (<= (count num) 8))
                                      (ops/identify-op (subs num 0 4))
                                      ops/UNKNOWN)))

(def update-operator
  (debounce upd-operator 500))

(defn rm-number []
  (swap! state update-in [:phone-number] drop-last-str)
  (update-operator (:phone-number @state)))

(defn change-number [num]
  (swap! state assoc :phone-number num)
  (update-operator (:phone-number @state)))

(defn update-number [num]
  (swap! state update-in [:phone-number] str num)
  (update-operator (:phone-number @state)))

(defn clear-number []
  (swap! state assoc :phone-number "")
  (update-operator (:phone-number @state)))

(defn main []
  [:> js/MaterialUI.MuiThemeProvider
   {:theme custom-theme}
   [operator-logo]
   [:> (with-custom-styles (r/reactify-component divider)) {:pos "top-divider"}]
   [number-text (:phone-number @state) change-number rm-number clear-number]
   [:> (with-custom-styles (r/reactify-component divider)) {:pos "bottom-divider"}]
   [phone update-number]])

(r/render [main]
          (js/document.getElementById "app"))
