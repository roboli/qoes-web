(ns qoes.logo
  (:require [reagent.core :as r]
            [react-transition-group :refer [Transition]]
            ["@material-ui/core/styles" :refer [withStyles]]
            [qoes.operators :as ops]))

(def styles
  #js {:to-enter #js {:transition "opacity 0.9s ease-in-out, height 0.1s linear 0.9s"
                      :opacity 0
                      :height 0}
       :to-exit #js {:transition "opacity 0.9s ease-in-out 1s, height 0.1s linear 0.9s"
                     :opacity 0
                     :height 0}
       :entering #js {:opacity 0
                      :height 0}
       :entered #js {:opacity 1
                     :height "100%"}
       :exiting #js {:opacity 1
                     :height "100%"}
       :exited #js {:opacity 0
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
