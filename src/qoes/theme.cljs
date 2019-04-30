(ns qoes.theme
  (:require ["@material-ui/core/styles" :as styles]))

(def custom-theme
  (styles/createMuiTheme
   #js {:palette #js {:primary #js {:main "#e91e63"}
                      :secondary #js {:main "#a31545"}}}))
