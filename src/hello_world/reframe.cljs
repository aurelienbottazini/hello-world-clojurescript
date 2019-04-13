(ns hello-world.reframe
  (:require [re-frame.core :as rf]
            [reagent.core :as r]
            [clojure.string :as str])
  (:require-macros [devcards.core :refer [defcard deftest dom-node]]))

(defn dispatch-timer-event
  []
  (let [now (js/Date.)]
    (rf/dispatch [:timer now])))

(defonce do-timer (js/setInterval dispatch-timer-event 1000))

(rf/reg-event-db
 :initialize
 (fn [_ _]
   {:time (js/Date.)
    :time-color "blue"}))

(rf/reg-event-db
 :timer
 (fn [db [_ new-time]]
   (assoc db :time new-time)))

(rf/reg-sub
 :time
 (fn [db _]
   (:time db)))

(rf/reg-sub
 :time-color
 (fn [db _]
   (:time-color db)))

(defn clock
  []
  [:div.example-clock
   {:style {:color @(rf/subscribe [:time-color])}}
   "I am a reframe clock"
   " "
   (-> @(rf/subscribe [:time])
       .toTimeString
       (str/split " ")
       first)])

(defn mount-reframe-clock []
  (when-let [app-elem (js/document.getElementById "app-reframe")]
    (rf/dispatch-sync [:initialize])
    (r/render [clock]
              app-elem)))

(defcard reframe-clock
  (do
    (rf/dispatch-sync [:initialize])
    (r/as-element [clock])))
