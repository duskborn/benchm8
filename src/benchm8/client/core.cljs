(ns benchm8.client.core
  (:require [rum.core :as rum]
            [ajax.core :as ajax]
            [garden.core :refer [css]]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"
                          :benchmarks {}}))

(rum/defc benchm8-app < rum/reactive
  {:did-mount (fn [e] (ajax/GET "/benchmarks.json"
                                {:handler (fn [^PersistentArrayMap response]
                                            (swap! app-state assoc :benchmarks (get response "benchmarks")))})
                nil)}
  []
  [:div#benchm8-app
    [:style (css [:body {:font-size "16px"}])]
    [:table
      [:thead]
      [:tbody
        (for [bench (:benchmarks (rum/react app-state))]
          [:tr [:td (pr-str bench)]])]]])

(rum/mount (benchm8-app)
           (.getElementById js/document "app"))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
