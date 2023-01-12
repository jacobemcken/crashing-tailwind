(ns my.app
  (:require [reagent.dom :as rdom]))

(defn simple-component []
  [:div
   [:p "I am a component!"]
   [:p
    "I have " [:strong "bold"]
    [:span {:class "text-red-700"} " and red "] "text."]])

(defn ^:dev/after-load mount-root []
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [simple-component] root-el)))

(defn ^:export run []
  (mount-root))
