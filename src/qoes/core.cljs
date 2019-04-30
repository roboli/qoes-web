(ns qoes.core
  (:require [reagent.core :as r]
            ["@material-ui/core" :as mui]
            [qoes.theme :refer [custom-theme]]))

(defn app []
  [:> mui/AppBar
    [:> mui/Toolbar
     [:> mui/Typography {:variant "title"
                         :color "inherit"}
      "Que operador es?"]]])

(defn main []
  [:> mui/MuiThemeProvider
   {:theme custom-theme}
   [app]])

(r/render [main]
          (js/document.getElementById "app"))
