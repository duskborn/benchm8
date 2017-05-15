(ns dev
  (:require [benchm8.server.core :refer [make-handler]]
            [benchm8.server.tests :refer [TestGroup]])
  )

(def handler (make-handler {:tests [{:set-up nil
                                     :tests []
                                     :tear-down nil}]}))