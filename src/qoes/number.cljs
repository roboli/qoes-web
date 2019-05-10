(ns qoes.number
  (:require [reagent.core :as r]
            ["@material-ui/core" :as mui]
            ["@material-ui/icons" :as mui-icons]))

(defn rm-number [{:keys [on-click] :as props}]
  [:> mui/InputAdornment {:position "end"}
   [:> mui/IconButton {:on-click on-click}
    [:> mui-icons/Backspace]]])

(defn number-text [value rm-num]
  [:> mui/Input {:fullWidth true
                 :value value
                 :endAdornment (r/create-element (r/reactify-component rm-number) #js{:on-click rm-num})}])
