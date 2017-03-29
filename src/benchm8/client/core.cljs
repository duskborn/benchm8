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
                               (if (= key (nth benchmark 0))
                                 (assoc benchmark 2 enabled)
                                 benchmark)))))


(rum/defc check-box [checked on-check-state]
  [:input {:type "checkbox"
           :checked checked
           :on-click (fn [_] (on-check-state (not checked)))}])


(rum/defc benchm8-app < rum/reactive
                        {:did-mount (fn [state]
                                      (data/get-benchmarks (fn [bchs]
                                                             (swap! app-state assoc :benchmarks (map #(conj %1 false) bchs))))
                                      state)}
  []
  (let [state (rum/react app-state)
        benchmarks (:benchmarks state)
        results (:results state)]
    [:div.benchm8-app
     [:pre (with-out-str (cljs.pprint/pprint state))]
     (if (not-empty results)
       [:div.results "results"]
       [:table
        [:thead]
        [:tbody
         (for [benchmark benchmarks]
           (let [key (nth benchmark 0)
                 name (nth benchmark 1)
                 enabled (nth benchmark 2)]
             [:tr
              [:td key]
              [:td name]
              [:td
               (check-box enabled #(swap! app-state toggle-benchmark key %1))]]))]])]))

(rum/mount (benchm8-app) (.getElementById js/document "app"))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  (swap! app-state update-in [:__figwheel_counter] inc)
)
