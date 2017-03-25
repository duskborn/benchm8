(ns benchm8.server.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [benchm8.server.view :as view]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
           (GET "/" [] (view/index-view))
           (GET "/benchmarks.json" [] (view/list-benchmarks))
           (route/not-found "not found"))

(def handler (wrap-defaults app-routes site-defaults))
