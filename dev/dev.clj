(ns dev
  (:require [benchm8.server.core :refer [make-handler map->ServerConfig map->Test]]))


(def cfg {:dbcfg {:name "benchm8testenv"
                  :create true}
          :tests [{:name "failing test"
                   :fn (fn [] (throw (RuntimeException. "test exception")))}
                  {:name "nice test!"
                   :fn (fn [] (Thread/sleep 100))}]})


(def handler (make-handler cfg))
