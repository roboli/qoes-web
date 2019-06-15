(ns qoes.number
  (:require [reagent.core :as r]))

(def timer (r/atom nil))

(defn custom-styles [theme]
  #js {:input #js {:fontSize (.spacing theme 3)}})

(def with-custom-styles (js/MaterialUI.withStyles custom-styles))

(defn handle-press [f]
  (fn []
    (reset! timer (js/setTimeout f 1000))))

(defn handle-release []
  (js/clearTimeout @timer))

(defn rm-number [{:keys [on-click clear-num] :as props}]
  [:> js/MaterialUI.InputAdornment {:position "end"}
   [:> js/MaterialUI.IconButton {:on-click on-click
                   :onMouseDown (handle-press clear-num)
                   :onMouseUp handle-release
                   :onMouseLeave handle-release}
    [:i {:class "material-icons"} "backspace"]]])

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

(defn input [{:keys [classes value update-num rm-num clear-num] :as props}]
  [:> js/MaterialUI.Input {:class (.-input classes)
             :inputProps {:style {:textAlign "center"}}
             :fullWidth true
             :autoFocus true
             :value value
             :onChange (on-change update-num)
             :disableUnderline true
             :endAdornment (r/create-element (r/reactify-component rm-number) #js{:on-click rm-num
                                                                                  :clear-num clear-num})}])

(defn number-text [value update-num rm-num clear-num]
  (r/create-element (with-custom-styles (r/reactify-component input)) #js{:value value
                                                                          :update-num update-num
                                                                          :rm-num rm-num
                                                                          :clear-num clear-num}))
