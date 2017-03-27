(ns benchm8.client.core
  (:require [rum.core :as rum]
            [ajax.core :as ajax]
            [garden.core :refer [css]]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"
                          :benchmarks []}))

(rum/defc bench-table [benchmarks]
  [:table
    [:thead]
    [:tbody
      (for [bench benchmarks]
        (let [key (first bench)
              measure-name (second bench)]
          [:tr
            [:td key]
            [:td measure-name]
            [:td
              [:input {:type "checkbox" :checked false}]
              [:input {:type "number" :value "0" :disabled true}]]]))]])

(rum/defc benchm8-app < rum/reactive
  {:did-mount (fn [e] (ajax/GET "/benchmarks.json"
                                {:handler (fn [benchmarks]
                                            (swap! app-state assoc :benchmarks (map #(conj %1 false 0) benchmarks))
                                            nil)})
                nil)}
  []
  [:div#benchm8-app
    [:style (css [:body {:font-size "16px"}])]
    (bench-table (:benchmarks (rum/react app-state)))])

(rum/mount (benchm8-app) (.getElementById js/document "app"))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
