(ns qoes.number
  (:require [reagent.core :as r]
            ["@material-ui/core" :as mui]
            ["@material-ui/core/styles" :refer [withStyles]]
            ["@material-ui/icons" :as mui-icons]))

(defn custom-styles [theme]
  #js {:input #js {:fontSize (* (.. theme -spacing -unit) 3)}})

(def with-custom-styles (withStyles custom-styles))

(defn rm-number [{:keys [on-click] :as props}]
  [:> mui/InputAdornment {:position "end"}
   [:> mui/IconButton {:on-click on-click}
    [:> mui-icons/Backspace]]])

(defn input [{:keys [classes value rm-num] :as props}]
  [:> mui/Input {:class (.-input classes)
                 :inputProps {:style {:textAlign "center"}}
                 :fullWidth true
                 :value value
                 :disableUnderline true
                 :endAdornment (r/create-element (r/reactify-component rm-number) #js{:on-click rm-num})}])

(defn number-text [value rm-num]
  (r/create-element (with-custom-styles (r/reactify-component input)) #js{:value value
                                                                          :rm-num rm-num}))
