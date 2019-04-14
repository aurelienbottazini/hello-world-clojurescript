(ns hello-world.core
  (:require [devcards.core]
            [react-dom]
            [hello-world.reframe :as hrf]
            [hello-world.reagent :as hre]
            [hello-world.omnext :as hon]))


(when-let [app-elem (js/document.querySelector "#app-react")]
  (.render js/ReactDOM
           (.createElement js/React "h2" nil "Hello, React world!")
           app-elem))

(hre/mount-reagent-component)
(hrf/mount-reframe-clock)
(hon/mount-omnext-component)
