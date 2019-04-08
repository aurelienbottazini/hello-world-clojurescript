(ns hello-world.core
  (:require [devcards.core] [react-dom])
  (:require-macros
   [cljs.test :refer [is testing]]
   [ devcards.core :refer [defcard deftest dom-node]]))

(defcard example-dev-card
  "Example dev card title" "Example dev card text")

(when-let [app-elem (js/document.querySelector "#app")]
  (.render js/ReactDOM
           (.createElement js/React "h2" nil "Hello, React world!")
           (.getElementById js/document "app")))
