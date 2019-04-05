(ns hello-world.core
  (:require react-dom))

(.render js/ReactDOM
         (.createElement js/React "h2" nil "Hello, React world!")
         (.getElementById js/document "app"))

(+ 1 2 3)
