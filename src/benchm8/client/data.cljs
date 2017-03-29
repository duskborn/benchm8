(ns benchm8.client.data
  (:require [ajax.core :as ajax]))


(defn get-benchmarks [callback]
  (ajax/GET "/benchmarks.json" {:handler callback}))
