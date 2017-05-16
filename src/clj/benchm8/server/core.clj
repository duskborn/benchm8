(ns benchm8.server.core
  (:gen-class :name benchm8.Benchm8
              :methods [#^{:static true} [runServer [Object] void]])
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [benchm8.server.view :as view]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.adapter.jetty :refer [run-jetty]]))

(defn make-routes [cfg]
  (routes (GET "/" [] (view/index-view))
          (GET "/ajax/get-test-results" [] (str cfg))
          (route/not-found "not found")))

(defn make-handler [cfg]
  (wrap-defaults (make-routes cfg) site-defaults))

(defn run-server [cfg]
  (run-jetty (make-handler cfg) {:port 3000}))

(defn -runServer [cfg]
  (run-server cfg))
