(ns dev
  (:require [benchm8.server.core :refer [make-handler]]))

(def handler (make-handler {:tests []}))