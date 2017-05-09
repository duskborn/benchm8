(ns benchm8.client.core
  (:require [rum.core :as rum]
            [benchm8.client.data :as data]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:benchmarks []
                          :results []}))


(defn toggle-benchmark [state key enabled]
  (let [benchmarks (:benchmarks state)]
    (assoc state :benchmarks (for [benchmark benchmarks]
                               (if (= key (:key benchmark))
                                 (assoc benchmark :enabled enabled)
                                 benchmark)))))


(rum/defc check-box [checked on-check-state]
  [:input {:type "checkbox"
           :checked checked
           :on-click (fn [_] (on-check-state (not checked)))}])


(rum/defc benchm8-app < rum/reactive []
  [:h1 "hey"])

(rum/mount (benchm8-app) (.getElementById js/document "app"))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
