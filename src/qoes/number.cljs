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

(defn map-char [char]
  (cond
    (re-matches #"(?i)[abc]" char) "2"
    (re-matches #"(?i)[def]" char) "3"
    (re-matches #"(?i)[ghi]" char) "4"
    (re-matches #"(?i)[jkl]" char) "5"
    (re-matches #"(?i)[mno]" char) "6"
    (re-matches #"(?i)[pqrs]" char) "7"
    (re-matches #"(?i)[tuv]" char) "8"
    (re-matches #"(?i)[wxyz]" char) "9"
    (re-matches #"\+" char) "0"
    :else nil))

(defn on-change [update-num]
  (fn [val]
    (let [num (-> val .-target .-value)]
      (cond
        (= (count num) 0) (update-num "")
        (re-matches #"\d+" num) (update-num num)
        (map-char (last num)) (update-num (str
                                           (apply str (drop-last num))
                                           (map-char (last num))))))))

(defn input [{:keys [classes value update-num rm-num] :as props}]
  [:> mui/Input {:class (.-input classes)
                 :inputProps {:style {:textAlign "center"}}
                 :fullWidth true
                 :autoFocus true
                 :value value
                 :onChange (on-change update-num)
                 :disableUnderline true
                 :endAdornment (r/create-element (r/reactify-component rm-number) #js{:on-click rm-num})}])

(defn number-text [value update-num rm-num]
  (r/create-element (with-custom-styles (r/reactify-component input)) #js{:value value
                                                                          :update-num update-num
                                                                          :rm-num rm-num}))
