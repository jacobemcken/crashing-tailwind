(ns my.app
  (:require [reagent.dom :as rdom]))

(defn simple-component []
  [:div
   [:p "I am a component!"]
   [:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red "] "text."]])

(defn ^:export run []
  (rdom/render [simple-component] (js/document.getElementById "app")))