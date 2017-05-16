(ns benchm8.client.data
  (:require [ajax.core :as ajax]))

(defn get-tests [callback]
  (ajax/GET "/ajax/get-tests" {:handler callback}))
