(ns qoes.theme
  (:require [cljsjs.material-ui]))

(def custom-theme
  (js/MaterialUI.createMuiTheme
   #js {:palette #js {:primary #js {:main "#e91e63"}
                      :secondary #js {:main "#a31545"}}}))
