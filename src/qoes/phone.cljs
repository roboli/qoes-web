(ns qoes.phone
  (:require [reagent.core :as r]
            ["@material-ui/core" :as mui]
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
  [:> mui/Grid {:container true
                :spacing 40}
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 1)}
      [:> mui/Typography {:variant "h5"
                          :class (.-number classes)} "1"]
      [:span {:dangerouslySetInnerHTML {:__html "&nbsp;"}}]]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 2)}
      [:> mui/Typography {:variant "h5"
                          :class (.-number classes)} "2"]
      [:> mui/Typography {:variant "caption"
                          :color "textSecondary"
                          :class (.-text classes)} "ABC"]]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 3)}
      [:> mui/Typography {:variant "h5"
                          :class (.-number classes)} "3"]
      [:> mui/Typography {:variant "caption"
                          :color "textSecondary"
                          :class (.-text classes)} "DEF"]]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 4)}
      [:> mui/Typography {:variant "h5"
                          :class (.-number classes)} "4"]
      [:> mui/Typography {:variant "caption"
                          :color "textSecondary"
                          :class (.-text classes)} "GHI"]]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 5)}
      [:> mui/Typography {:variant "h5"
                          :class (.-number classes)} "5"]
      [:> mui/Typography {:variant "caption"
                          :color "textSecondary"
                          :class (.-text classes)} "JKL"]]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 6)}
      [:> mui/Typography {:variant "h5"
                          :class (.-number classes)} "6"]
      [:> mui/Typography {:variant "caption"
                          :color "textSecondary"
                          :class (.-text classes)} "MNO"]]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 7)}
      [:> mui/Typography {:variant "h5"
                          :class (.-number classes)} "7"]
      [:> mui/Typography {:variant "caption"
                          :color "textSecondary"
                          :class (.-text classes)} "PQRS"]]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 8)}
      [:> mui/Typography {:variant "h5"
                          :class (.-number classes)} "8"]
      [:> mui/Typography {:variant "caption"
                          :color "textSecondary"
                          :class (.-text classes)} "TUV"]]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 9)}
      [:> mui/Typography {:variant "h5"
                          :class (.-number classes)} "9"]
      [:> mui/Typography {:variant "caption"
                          :color "textSecondary"
                          :class (.-text classes)} "WXYZ"]]]]
   [:> mui/Grid {:item true
                 :xs 4}]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-wrapper classes)}
     [:div {:class (.-button classes)
            :on-click #(on-click 0)}
      [:> mui/Typography {:variant "h5"
                          :class (.-number classes)} "0"]
      [:> mui/Typography {:variant "caption"
                          :color "textSecondary"
                          :class (.-text classes)} "+"]]]]
   [:> mui/Grid {:item true
                 :xs 4}]])

(defn phone [on-click]
  (r/create-element (with-custom-styles (r/reactify-component keyboard)) #js{:on-click on-click}))
