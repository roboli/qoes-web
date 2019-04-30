(ns qoes.phone
  (:require [reagent.core :as r]
            ["@material-ui/core" :as mui]
            ["@material-ui/core/styles" :refer [withStyles]]))

(defn custom-styles [theme]
  #js {:button #js {:height (* (.. theme -spacing -unit) 8)
                    :display "flex"
                    :justifyContent "center"
                    :alignItems "center"}
       :number #js {:padding (* (.. theme -spacing -unit) 2)}})

(def with-custom-styles (withStyles custom-styles))

(defn keyboard [{:keys [classes] :as props}]
  [:> mui/Grid {:container true
                :spacing 40}
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-button classes)}
     [:div {:class (.-number classes)} "1"]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-button classes)}
     [:div {:class (.-number classes)} "2"]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-button classes)}
     [:div {:class (.-number classes)} "3"]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-button classes)}
     [:div {:class (.-number classes)} "4"]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-button classes)}
     [:div {:class (.-number classes)} "5"]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-button classes)}
     [:div {:class (.-number classes)} "6"]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-button classes)}
     [:div {:class (.-number classes)} "7"]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-button classes)}
     [:div {:class (.-number classes)} "8"]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-button classes)}
     [:div {:class (.-number classes)} "9"]]]
   [:> mui/Grid {:item true
                 :xs 4}]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-button classes)}
     [:div {:class (.-number classes)} "0"]]]
   [:> mui/Grid {:item true
                 :xs 4}]])

(defn phone []
  [:> (with-custom-styles (r/reactify-component keyboard))])
