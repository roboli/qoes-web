(ns qoes.logo
  (:require [reagent.core :as r]
            [cljsjs.react-motion]
            [qoes.operators :as ops]))

(def items (r/atom [{:key ops/UNKNOWN
                     :style {:x 0}
                     :data {:src "img/question.png"}}]))

(defn build-logo [config]
  ^{:key (.-key config)} [:img {:src (-> config .-data .-src)
                                :style #js {:transform (str "translateX(" (-> config .-style .-x) "px)")
                                            :position "absolute"}}])

(defn build-list [styles]
  (r/as-element [:div (map build-logo styles)]))

(defn trans-motion []
  (let [will-leave (fn [] #js {:x (js/ReactMotion.spring -1100 #js {:stiffness 250, :damping 30})})
        will-enter (fn [] #js {:x 1100})]
    [:> js/ReactMotion.TransitionMotion {:willLeave will-leave
                                         :willEnter will-enter
                                         :styles (clj->js @items)} build-list]))

(defn op-logo [phone]
  (let [src (condp = phone
              ops/CLARO "img/claro.png"
              ops/MOVISTAR "img/movistar.png"
              ops/TIGO "img/tigo.png"
              "img/question.png")]
    (swap! items (fn [v]
                   (if (= (-> v first :key) phone)
                     [{:key phone
                       :style {:x -90}
                       :data {:src src}}]
                     [{:key phone
                       :style {:x (js/ReactMotion.spring -90 #js {:stiffness 200, :damping 100})}
                       :data {:src src}}])))
    [:div {:style {:height 180
                   :textAlign "center"}}
     [trans-motion]]))
