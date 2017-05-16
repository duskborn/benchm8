(ns benchm8.client.core
  (:require [rum.core :as rum]
            [benchm8.client.data :as data]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:test-results nil}))


(rum/defc test-item [result]
  [:.test-item {:data-success (get result "success")}
    [:.test-info
      [:.test-item-name (get result "name")]
      [:.test-item-time (if-let [error (get result "error")]
                          (str (get error "toString") " (" (get result "time") "ms)")
                          (str (get result "time") "ms"))]]
    [:.test-exception-traceback
      [:pre (get (get result "error") "printStackTrace")]]])


(defn require-tests [_]
  (data/get-tests (fn [data] (swap! app-state assoc :test-results data))))


(rum/defc benchm8-app < rum/reactive []
  (let [state (rum/react app-state)]
    [:.benchm8-app
      [:.benchm8-app-header
        [:h1 "benchm8"]
        [:button.run-tests {:on-click require-tests} "run tests"]]
      (if-let [test-results (:test-results state)]
        [:.tests-list
         (for [test-result test-results]
           (test-item test-result))])]))

(rum/mount (benchm8-app) (.getElementById js/document "app"))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
