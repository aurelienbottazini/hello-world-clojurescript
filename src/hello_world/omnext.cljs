(ns hello-world.omnext
  (:require [goog.dom :as gdom]
            [react-dom]
            [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]))

(defui HelloWorld
  Object
  (render [this]
          (dom/div nil "Hello, world Om Next!")))

(def hello (om/factory HelloWorld))

(defn mount-omnext-component []
  (when-let [app-elem (js/document.querySelector "#app-omnext")]
    (js/ReactDOM.render (hello) app-elem)))
