(ns benchm8.client.core
  (:require [rum.core :as rum]
            [benchm8.client.data :as data]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:test-results [{:name "test #0"
                                          :success true
                                          :exception nil
                                          :exception-traceback nil
                                          :time 200}
                                         {:name "test #1"
                                          :success false
                                          :exception "AssertionError"
                                          :exception-traceback "trace back xd"
                                          :time nil}]}))


(rum/defc test-item [result]
  [:div.test-item {:data-success (str (:success result))}
    [:div.test-info
      [:div.test-item-name (:name result)]
      [:div.test-item-success (if (:success result)
                                (str (:time result) "ms")
                                (:exception result))]]
    [:div.test-exception-traceback
      [:pre (:exception-traceback result)]]])


(rum/defc benchm8-app < rum/reactive []
  (let [state (rum/react app-state)]
    [:div.benchm8-app
      [:div.benchm8-app-header
        [:h1 "benchm8"]
        [:button.run-tests "run tests"]]
      [:div.tests-list
        (for [result (:test-results state)]
          (test-item result))]]))

(rum/mount (benchm8-app) (.getElementById js/document "app"))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
