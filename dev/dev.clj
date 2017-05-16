(ns dev
  (:require [benchm8.server.core :refer [make-handler]]))


(def cfg {:tests [(fn [] (throw (RuntimeException. "test exception")))
                  (fn [] (str "nice!"))]})


(def handler (make-handler cfg))
