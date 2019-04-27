(ns qoes.core
  (:require [reagent.core :as r]))

(defn hello-qoes []
  [:h1 "Que operador es?"])

(r/render [hello-qoes]
          (js/document.getElementById "app"))
