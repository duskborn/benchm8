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
      (let [key (nth bench 0)
            class-name (nth bench 1)
            measures (nth bench 2)]
        [:tr
         [:td key]
         [:td class-name]
         [:td (for [[name enabled] measures]
                [:button (str name " " enabled)])]]))]])

(rum/defc benchm8-app < rum/reactive
  {:did-mount (fn [e] (ajax/GET "/benchmarks.json"
                                {:handler (fn [benchs]
                                            (swap! app-state assoc :benchmarks
                                                   (for [[key name measures] benchs]
                                                     [key name (zipmap measures (repeat false))])))})
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
