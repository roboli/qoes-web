(ns qoes.core
  (:require [reagent.core :as r]
            ["@material-ui/core" :as mui]
            ["@material-ui/core/styles" :refer [withStyles]]
            [qoes.theme :refer [custom-theme]]
            [qoes.phone :refer [phone]]
            [qoes.operators :refer [identify-op]]))

(def state (r/atom {:phone-number ""}))

(defn custom-styles [theme]
  #js {:spacer #js {:height (* (.. theme -spacing -unit) 8)}})

(def with-custom-styles (withStyles custom-styles))

(defn app []
  [:> mui/AppBar
    [:> mui/Toolbar
     [:> mui/Typography {:variant "title"
                         :color "inherit"}
      "Que operador es?"]]])

(defn spacer [{:keys [classes] :as props}]
  [:div {:class (.-spacer classes)}])

(defn operator-label []
  (let [op (identify-op (subs (:phone-number @state) 0 4))]
    [:> mui/Typography {:variant "h4"
                        :align "center"} op]))

(defn number-text []
  [:> mui/TextField {:fullWidth true
                     :value (:phone-number @state)}])

(defn drop-last-str [v]
  (apply str (drop-last (vec v))))

(defn rm-number []
  [:span {:on-click (fn [] (swap! state update-in [:phone-number] drop-last-str))} "X"])

(defn update-number [num]
  (swap! state update-in [:phone-number] str num))

(defn main []
  [:> mui/MuiThemeProvider
   {:theme custom-theme}
   [app]
   [:> (with-custom-styles (r/reactify-component spacer))]
   [operator-label]
   [:div
    [number-text]
    [rm-number]]
   [phone update-number]])

(r/render [main]
          (js/document.getElementById "app"))
