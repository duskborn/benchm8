(ns dev
  (:require [benchm8.server.core :refer [make-handler]]))


(def cfg
  {:tests [{:test-name "failing test"
            :test-fn (fn [] (throw (RuntimeException. "test exception")))}
           {:test-name "nice test!"
            :test-fn (fn [] (Thread/sleep 100))}]})


(def handler (make-handler cfg))
