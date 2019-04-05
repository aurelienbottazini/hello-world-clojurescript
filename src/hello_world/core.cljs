(ns hello-world.core
  (:require [devcards.core] [react-dom])
  (:require-macros
   [cljs.test :refer [is testing]]
   [ devcards.core :refer [defcard deftest dom-node]]))

(defcard app-card
  "Example card" "foo")

(deftest sample-test
  (testing "happy test"
    (is (= 1 1))))

(when-let [app-elem (js/document.querySelector "#app")]
  (.render js/ReactDOM
           (.createElement js/React "h2" nil "Hello, React world!")
           (.getElementById js/document "app")))

(+ 1 2 3)
