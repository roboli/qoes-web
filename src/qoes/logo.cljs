(ns qoes.logo
  (:require [reagent.core :as r]
            [react-transition-group :refer [Transition]]
            ["@material-ui/core/styles" :refer [withStyles]]
            [qoes.operators :as ops]))

(def styles
  #js {:to-enter #js {:transition "margin-right 0.9s ease-in-out, height 0.1s linear 0.9s"}
       :to-exit #js {:transition "margin-right 0.9s ease-in-out 1s, height 0.1s linear 0.9s"}
       :entering #js {:marginRight 2200
                      :height 0}
       :entered #js {:marginRight 0}
       :exiting #js {:marginRight 2200}
       :exited #js {:marginRight -1500
                    :height 0}})


(defn logo [showed? src]
  (fn [status]
    (let [default (if showed? "to-exit" "to-enter")]
      (r/as-element [:img {:src src
                           :style (.assign js/Object #js {} (aget styles default) (aget styles status))}]))))

(defn trans-logo [phone]
  (let [bool (or (= phone ops/CLARO)
                 (= phone ops/MOVISTAR)
                 (= phone ops/TIGO))
        src (condp = phone
              ops/CLARO "img/claro.png"
              ops/MOVISTAR "img/movistar.png"
              "img/tigo.png")]
    [:div
     [:> Transition {:in bool
                     :timeout 500} (logo bool src)]]))

(defn unknown [showed?]
  (fn [status]
    (let [default (if showed? "to-exit" "to-enter")]
      (r/as-element [:img {:src "img/question.png"
                           :style (.assign js/Object #js {} (aget styles default) (aget styles status))}]))))

(defn trans-unknown [phone]
  (let [bool (= phone ops/UNKNOWN)]
    [:div
     [:> Transition {:in bool
                     :timeout 500} (unknown bool)]]))

(defn op-logo [phone]
  [:div {:style {:height 180
                 :textAlign "center"}}
   [trans-unknown phone]
   [trans-logo phone]])
