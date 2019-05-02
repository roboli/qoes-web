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

(defn keyboard [{:keys [classes on-click] :as props}]
  [:> mui/Grid {:container true
                :spacing 40}
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-button classes)}
     [:div {:class (.-number classes)
            :on-click #(on-click 1)} "1"]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-button classes)}
     [:div {:class (.-number classes)
            :on-click #(on-click 2)} "2"]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-button classes)}
     [:div {:class (.-number classes)
            :on-click #(on-click 3)} "3"]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-button classes)}
     [:div {:class (.-number classes)
            :on-click #(on-click 4)} "4"]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-button classes)}
     [:div {:class (.-number classes)
            :on-click #(on-click 5)} "5"]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-button classes)}
     [:div {:class (.-number classes)
            :on-click #(on-click 6)} "6"]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-button classes)}
     [:div {:class (.-number classes)
            :on-click #(on-click 7)} "7"]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-button classes)}
     [:div {:class (.-number classes)
            :on-click #(on-click 8)} "8"]]]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-button classes)}
     [:div {:class (.-number classes)
            :on-click #(on-click 9)} "9"]]]
   [:> mui/Grid {:item true
                 :xs 4}]
   [:> mui/Grid {:item true
                 :xs 4}
    [:div {:class (.-button classes)}
     [:div {:class (.-number classes)
            :on-click #(on-click 0)} "0"]]]
   [:> mui/Grid {:item true
                 :xs 4}]])

(defn phone [on-click]
  (r/create-element (with-custom-styles (r/reactify-component keyboard)) #js{:on-click on-click}))
