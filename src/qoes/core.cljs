(ns qoes.core
  (:require [reagent.core :as r]
            ["@material-ui/core" :as mui]
            ["@material-ui/core/styles" :refer [withStyles]]
            [qoes.theme :refer [custom-theme]]
            [qoes.phone :refer [phone]]))

(def state (r/atom {:operator "UNKNOWN"
                    :phone-number ""}))

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
  [:> mui/Typography {:variant "h4"
                      :align "center"} (:operator @state)])

(defn number-text []
  [:> mui/TextField {:fullWidth true
                     :value (:phone-number @state)}])

(defn update-number [num]
  (swap! state update-in [:phone-number] str num))

(defn main []
  [:> mui/MuiThemeProvider
   {:theme custom-theme}
   [app]
   [:> (with-custom-styles (r/reactify-component spacer))]
   [operator-label]
   [number-text]
   [phone update-number]])

(r/render [main]
          (js/document.getElementById "app"))
