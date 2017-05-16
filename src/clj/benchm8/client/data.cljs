(ns benchm8.client.data
  (:require [ajax.core :as ajax]))

(defn run-tests [callbacks]
  (ajax/GET "/ajax/run-tests" {:handler callback}))

(defn get-state [callbacks]
  (ajax/GET "/ajax/state" {:handler callback}))
