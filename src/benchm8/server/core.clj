(ns benchm8.server.core
  (:gen-class :name benchm8.Benchm8
              :methods [#^{:static true} [runServer [Object] void]])
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [benchm8.server.view :as view]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
           (GET "/" [] (view/index-view))
           (GET "/measure" {{:keys [keys]} :params} (view/measure-view keys))
           (route/not-found "not found"))

(def handler (wrap-defaults app-routes site-defaults))

(defn -runServer [tests]
  )
