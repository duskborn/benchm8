(ns benchm8.client.core
  (:require [rum.core :as rum]
            [ajax.core :as ajax]
            [garden.core :refer [css]]))

(enable-console-print!)

(println "This text is printed from src/benchm8/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(rum/defc benchm8-app <
  rum/reactive
  {:did-mount (fn [e] (ajax/GET "/benchmarks.json"
                                {:handler (fn [response]
                                            (swap! app-state assoc :text (str response)))}))}
  []
  [:div#benchm8-app
    [:style (css [:body {:font-size "16px"}])]
    [:p (:text (rum/react app-state))]])

(rum/mount (benchm8-app)
           (.getElementById js/document "app"))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
