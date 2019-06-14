(ns qoes.phone
  (:require [reagent.core :as r]))

(defn custom-styles [theme]
  #js {:wrapper #js {:height (.spacing theme 8)
                     :display "flex"
                     :justifyContent "center"
                     :alignItems "center"}
       :button #js {:display "flex"
                    :flexDirection "column"
                    :alignItems "center"
                    :paddingRight (.spacing theme 3)
                    :paddingLeft (.spacing theme 3)
                    :cursor "pointer"
                    :userSelect "none"}
       :number #js {:color "#6a81ff"
                    :marginBottom (.spacing theme 1)}
       :text #js {:fontSize (.spacing theme 1.1)}})

(def with-custom-styles (js/MaterialUI.withStyles custom-styles))

(defn keyboard [{:keys [classes on-click] :as props}]
  [:> js/MaterialUI.Grid {:container true
            :spacing 4}
   [:> js/MaterialUI.Grid {:item true
             :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 1)}
      [:> js/MaterialUI.Typography {:variant "h5"
                      :class (.-number classes)} "1"]
      [:span {:dangerouslySetInnerHTML {:__html "&nbsp;"}}]]]]
   [:> js/MaterialUI.Grid {:item true
             :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 2)}
      [:> js/MaterialUI.Typography {:variant "h5"
                      :class (.-number classes)} "2"]
      [:> js/MaterialUI.Typography {:variant "caption"
                      :color "textSecondary"
                      :class (.-text classes)} "ABC"]]]]
   [:> js/MaterialUI.Grid {:item true
             :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 3)}
      [:> js/MaterialUI.Typography {:variant "h5"
                      :class (.-number classes)} "3"]
      [:> js/MaterialUI.Typography {:variant "caption"
                      :color "textSecondary"
                      :class (.-text classes)} "DEF"]]]]
   [:> js/MaterialUI.Grid {:item true
             :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 4)}
      [:> js/MaterialUI.Typography {:variant "h5"
                      :class (.-number classes)} "4"]
      [:> js/MaterialUI.Typography {:variant "caption"
                      :color "textSecondary"
                      :class (.-text classes)} "GHI"]]]]
   [:> js/MaterialUI.Grid {:item true
             :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 5)}
      [:> js/MaterialUI.Typography {:variant "h5"
                      :class (.-number classes)} "5"]
      [:> js/MaterialUI.Typography {:variant "caption"
                      :color "textSecondary"
                      :class (.-text classes)} "JKL"]]]]
   [:> js/MaterialUI.Grid {:item true
             :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 6)}
      [:> js/MaterialUI.Typography {:variant "h5"
                      :class (.-number classes)} "6"]
      [:> js/MaterialUI.Typography {:variant "caption"
                      :color "textSecondary"
                      :class (.-text classes)} "MNO"]]]]
   [:> js/MaterialUI.Grid {:item true
             :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 7)}
      [:> js/MaterialUI.Typography {:variant "h5"
                      :class (.-number classes)} "7"]
      [:> js/MaterialUI.Typography {:variant "caption"
                      :color "textSecondary"
                      :class (.-text classes)} "PQRS"]]]]
   [:> js/MaterialUI.Grid {:item true
             :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 8)}
      [:> js/MaterialUI.Typography {:variant "h5"
                      :class (.-number classes)} "8"]
      [:> js/MaterialUI.Typography {:variant "caption"
                      :color "textSecondary"
                      :class (.-text classes)} "TUV"]]]]
   [:> js/MaterialUI.Grid {:item true
             :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 9)}
      [:> js/MaterialUI.Typography {:variant "h5"
                      :class (.-number classes)} "9"]
      [:> js/MaterialUI.Typography {:variant "caption"
                      :color "textSecondary"
                      :class (.-text classes)} "WXYZ"]]]]
   [:> js/MaterialUI.Grid {:item true
             :xs 4}]
   [:> js/MaterialUI.Grid {:item true
             :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 0)}
      [:> js/MaterialUI.Typography {:variant "h5"
                      :class (.-number classes)} "0"]
      [:> js/MaterialUI.Typography {:variant "caption"
                      :color "textSecondary"
                      :class (.-text classes)} "+"]]]]
   [:> js/MaterialUI.Grid {:item true
             :xs 4}]])

(defn phone [on-click]
  (r/create-element (with-custom-styles (r/reactify-component keyboard)) #js{:on-click on-click}))
