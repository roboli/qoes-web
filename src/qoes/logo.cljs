(ns qoes.logo
  (:require [reagent.core :as r]
            [react-transition-group :refer [Transition]]
            ["@material-ui/core/styles" :refer [withStyles]]
            [qoes.operators :as ops]))

(def styles
  #js {:default-enter #js {:transition "opacity 500ms ease-in-out"
                           :opacity 0}
       :default-exit #js {:transition "opacity 500ms ease-in-out"
                          :transitionDelay "500ms"
                           :opacity 0}
       :entering #js {:opacity 0}
       :entered #js {:opacity 1}
       :exiting #js {:opacity 1}
       :exited #js {:opacity 0}})


(defn logo [showed? src]
  (fn [status]
    (let [default (if showed? "default-exit" "default-enter")]
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
    (let [default (if showed? "default-exit" "default-enter")]
      (r/as-element [:img {:src "img/question.png"
                           :style (.assign js/Object #js {} (aget styles default) (aget styles status))}]))))

(defn trans-unknown [phone]
  (let [bool (= phone ops/UNKNOWN)]
    [:div
     [:> Transition {:in bool
                     :timeout 500} (unknown bool)]]))

(defn op-logo [phone]
  [:div
   [trans-unknown phone]
   [trans-logo phone]])
