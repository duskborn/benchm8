(ns benchm8.client.data
  (:require [ajax.core :as ajax]))

(defn get-test-results [callbacks]
  (ajax/GET "/ajax/get-test-results" {:handler callback}))
