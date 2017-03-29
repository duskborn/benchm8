(ns benchm8.client.data
  (:require [ajax.core :as ajax]))


(defn get-benchmarks [callback]
  (ajax/GET "/benchmarks.json"
            {:handler (fn [data]
                        (callback (for [item (get data "benchmarks")]
                                    {:key (nth item 0)
                                     :name (nth item 1)
                                     :enabled false})))}))

(defn get-measure [keys callback]
  (ajax/GET "/measure" {:params {:keys keys}
                        :handler callback}))
