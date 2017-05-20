(ns dev
  (:require [benchm8.server.core :refer [make-handler map->ServerConfig map->Test]]))


(def cfg (map->ServerConfig
           {:dbname "benchm8testenv"
            :tests [(map->Test {:test-name "failing test"
                                :test-fn (fn [] (throw (RuntimeException. "test exception")))})
                    (map->Test {:test-name "nice test!"
                                :test-fn (fn [] (Thread/sleep 100))})]}))


(def handler (make-handler cfg))
