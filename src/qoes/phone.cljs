(ns qoes.phone
  (:require [reagent.core :as r]
            ["@material-ui/core" :refer [Typography Grid]]
            ["@material-ui/core/styles" :refer [withStyles]]))

(defn custom-styles [theme]
  #js {:wrapper #js {:height (* (.. theme -spacing -unit) 8)
                     :display "flex"
                     :justifyContent "center"
                     :alignItems "center"}
       :button #js {:display "flex"
                    :flexDirection "column"
                    :alignItems "center"
                    :paddingRight (* (.. theme -spacing -unit) 3)
                    :paddingLeft (* (.. theme -spacing -unit) 3)
                    :cursor "pointer"
                    :userSelect "none"}
       :number #js {:color "#6a81ff"
                    :marginBottom (* (.. theme -spacing -unit) 1)}
       :text #js {:fontSize (* (.. theme -spacing -unit) 1.1)}})

(def with-custom-styles (withStyles custom-styles))

(defn keyboard [{:keys [classes on-click] :as props}]
  [:> Grid {:container true
            :spacing 40}
   [:> Grid {:item true
             :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 1)}
      [:> Typography {:variant "h5"
                      :class (.-number classes)} "1"]
      [:span {:dangerouslySetInnerHTML {:__html "&nbsp;"}}]]]]
   [:> Grid {:item true
             :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 2)}
      [:> Typography {:variant "h5"
                      :class (.-number classes)} "2"]
      [:> Typography {:variant "caption"
                      :color "textSecondary"
                      :class (.-text classes)} "ABC"]]]]
   [:> Grid {:item true
             :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 3)}
      [:> Typography {:variant "h5"
                      :class (.-number classes)} "3"]
      [:> Typography {:variant "caption"
                      :color "textSecondary"
                      :class (.-text classes)} "DEF"]]]]
   [:> Grid {:item true
             :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 4)}
      [:> Typography {:variant "h5"
                      :class (.-number classes)} "4"]
      [:> Typography {:variant "caption"
                      :color "textSecondary"
                      :class (.-text classes)} "GHI"]]]]
   [:> Grid {:item true
             :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 5)}
      [:> Typography {:variant "h5"
                      :class (.-number classes)} "5"]
      [:> Typography {:variant "caption"
                      :color "textSecondary"
                      :class (.-text classes)} "JKL"]]]]
   [:> Grid {:item true
             :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 6)}
      [:> Typography {:variant "h5"
                      :class (.-number classes)} "6"]
      [:> Typography {:variant "caption"
                      :color "textSecondary"
                      :class (.-text classes)} "MNO"]]]]
   [:> Grid {:item true
             :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 7)}
      [:> Typography {:variant "h5"
                      :class (.-number classes)} "7"]
      [:> Typography {:variant "caption"
                      :color "textSecondary"
                      :class (.-text classes)} "PQRS"]]]]
   [:> Grid {:item true
             :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 8)}
      [:> Typography {:variant "h5"
                      :class (.-number classes)} "8"]
      [:> Typography {:variant "caption"
                      :color "textSecondary"
                      :class (.-text classes)} "TUV"]]]]
   [:> Grid {:item true
             :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 9)}
      [:> Typography {:variant "h5"
                      :class (.-number classes)} "9"]
      [:> Typography {:variant "caption"
                      :color "textSecondary"
                      :class (.-text classes)} "WXYZ"]]]]
   [:> Grid {:item true
             :xs 4}]
   [:> Grid {:item true
             :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 0)}
      [:> Typography {:variant "h5"
                      :class (.-number classes)} "0"]
      [:> Typography {:variant "caption"
                      :color "textSecondary"
                      :class (.-text classes)} "+"]]]]
   [:> Grid {:item true
             :xs 4}]])

(defn phone [on-click]
  (r/create-element (with-custom-styles (r/reactify-component keyboard)) #js{:on-click on-click}))
