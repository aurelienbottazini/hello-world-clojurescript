(ns hello-world.omnext
  (:require [goog.dom :as gdom]
            [react-dom]
            [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]))

(defui HelloWorld
  Object
  (render [this]
          (dom/div nil (get (om/props this) :msg))))

(def hello (om/factory HelloWorld))

(defn mount-omnext-component []
  (when-let [app-elem (js/document.querySelector "#app-omnext")]
    (js/ReactDOM.render (hello {:msg "Hello from Om Next!"}) app-elem)))

(def clock-state (atom {:time (js/Date.)}))

(defui Clock
  static om/IQuery
  (query [this] [:time])
  Object
  (render [this]
          (let [{:keys [time]} (om/props this)]
            (dom/div nil
                     (dom/span nil (.toTimeString time))))))

(defn read-fn
  [{:keys [state] :as env} key params]
  (let [st @state]
    (if-let [[_ v] (find st key)]
      {:value v}
      {:value :not-found})))

(def reconciler
  (om/reconciler {:state clock-state
                  :parser (om/parser {:read read-fn})}))

(defn mount-omnext-clock []
  (om/add-root! reconciler Clock (gdom/getElement "app-omnext-clock")))
