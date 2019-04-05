(ns ^:figwheel-hooks hello-world.cards
  (:require [devcards.core]
            [hello-world.core]))

(enable-console-print!)

(defn render []
  (devcards.core/start-devcard-ui!))

(defn ^:after-load render-on-reload []
  (render))

(render)
