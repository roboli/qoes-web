(ns qoes.core
  (:require [reagent.core :as r]
            ["@material-ui/core" :as mui]
            ["@material-ui/core/styles" :refer [withStyles]]
            ["@material-ui/icons" :as mui-icons]
            [qoes.theme :refer [custom-theme]]
            [qoes.number :refer [number-text]]
            [qoes.phone :refer [phone]]
            [qoes.operators :as ops]))

(def state (r/atom {:phone-number ""}))

(defn custom-styles [theme]
  #js {:spacer #js {:height (* (.. theme -spacing -unit) 8)}})

(def with-custom-styles (withStyles custom-styles))

(defn app []
  [:> mui/AppBar
    [:> mui/Toolbar
     [:> mui/Typography {:variant "title"
                         :color "inherit"}
      "Que operador es?"]]])

(defn spacer [{:keys [classes] :as props}]
  [:div {:class (.-spacer classes)}])

(defn operator-label []
  (let [op (ops/identify-op (subs (:phone-number @state) 0 4))]
     [:img {:src (condp = op
                   ops/CLARO "img/claro.png"
                   ops/MOVISTAR "img/movistar.png"
                   ops/TIGO "img/tigo.png"
                   "img/question.png")}]))

(defn drop-last-str [v]
  (apply str (drop-last (vec v))))

(defn rm-number []
  (swap! state update-in [:phone-number] drop-last-str))

(defn update-number [num]
  (swap! state update-in [:phone-number] str num))

(defn main []
  [:> mui/MuiThemeProvider
   {:theme custom-theme}
   [app]
   [:> (with-custom-styles (r/reactify-component spacer))]
   [operator-label]
   [:div
    [number-text (:phone-number @state) rm-number]]
   [phone update-number]])

(r/render [main]
          (js/document.getElementById "app"))
