(ns qoes.core
  (:require [reagent.core :as r]
            ["@material-ui/core" :as mui]
            ["@material-ui/core/styles" :refer [withStyles]]
            [qoes.theme :refer [custom-theme]]
            [qoes.phone :refer [phone]]))

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

(defn main []
  [:> mui/MuiThemeProvider
   {:theme custom-theme}
   [app]
   [:> (with-custom-styles (r/reactify-component spacer))]
   [phone]])

(r/render [main]
          (js/document.getElementById "app"))
