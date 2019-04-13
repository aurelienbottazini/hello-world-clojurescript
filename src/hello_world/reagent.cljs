(ns hello-world.reagent
  (:require [reagent.core :as r])
  (:require-macros [devcards.core :refer [defcard deftest dom-node]]))

(defn simple-reagent-component [color]
  [:div {:style {:background-color (str "light" color)}}
   [:h2 "I am a reagent component!"]
   [:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color color}} (str " and " color " ")] "text."]])

(defcard blue-component
  (r/as-element [simple-reagent-component "blue"]))

(defcard green-component
  (r/as-element [simple-reagent-component "green"]))

(defn mount-reagent-component []
  (when-let [app-elem (js/document.getElementById "app-reagent")]
    (r/render [simple-reagent-component "purple"]
              app-elem)))
