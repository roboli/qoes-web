(ns qoes.theme
  (:require [material-ui :refer [createMuiTheme]]))

(def custom-theme
  (createMuiTheme
   #js {:palette #js {:primary #js {:main "#e91e63"}
                      :secondary #js {:main "#a31545"}}}))
