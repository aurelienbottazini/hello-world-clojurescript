(ns hello-world.core
  (:require [devcards.core]
            [react-dom]
            [reagent.core :as r]
            [re-frame.core :as rf]
            [clojure.string :as str])
  (:require-macros [devcards.core :refer [defcard deftest dom-node]]))

(when-let [app-elem (js/document.querySelector "#app-react")]
  (.render js/ReactDOM
           (.createElement js/React "h2" nil "Hello, React world!")
           app-elem))


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

(when-let [app-elem (js/document.getElementById "app-reagent")]
  (r/render [simple-reagent-component "purple"]
            app-elem))


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
   (-> @(rf/subscribe [:time])
       .toTimeString
       (str/split " ")
       first)])

(when-let [app-elem (js/document.getElementById "app-reframe")]
  (rf/dispatch-sync [:initialize])
  (r/render [clock]
            app-elem))

(defcard reframe-clock
  (do
    (rf/dispatch-sync [:initialize])
    (r/as-element [clock])))
