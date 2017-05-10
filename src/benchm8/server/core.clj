(ns benchm8.server.core
  (:gen-class :name benchm8.Benchm8
              :methods [#^{:static true} [runServer [Object] void]])
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [benchm8.server.view :as view]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.adapter.jetty :refer [run-jetty]]))

(defn benchm8-routes [cfg]
  (routes (GET "/" [] (view/index-view))
          (GET "/measure" {{:keys [keys]} :params} (view/measure-view keys))
          (route/not-found "not found")))

(defn benchm8-handler [cfg]
  (wrap-defaults (benchm8-routes cfg) site-defaults))

(def benchm8-dev-handler (benchm8-handler nil))

(defn -runServer [cfg]
  (run-jetty (benchm8-handler cfg) {:port 3000}))
