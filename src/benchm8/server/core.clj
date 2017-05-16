(ns benchm8.server.core
  (:gen-class :name benchm8.Benchm8
              :methods [#^{:static true} [runServer [Object] void]])
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [benchm8.server.view :as view]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.adapter.jetty :refer [run-jetty]]))


(defn error-view [e]
  {:toString (.toString e)
   :printStackTrace (let [sw (java.io.StringWriter.)
                          pw (java.io.PrintWriter. sw)]
                      (.printStackTrace e pw)
                      (str sw))})


(defn run-test [{:keys [test-name test-fn]}]
  (let [start (System/currentTimeMillis)]
    (try (do
           (test-fn)
           {:name test-name
            :success true
            :time (- start (System/currentTimeMillis))
            :error nil})
         (catch Throwable t
           {:name test-name
            :success false
            :time (- start (System/currentTimeMillis))
            :error (error-view t)}))))


(defn make-routes [cfg]
  (routes (GET "/" [] (view/index-view))
          (GET "/ajax/get-tests" [] (view/results-view (map run-test (:tests cfg))))
          (route/not-found "not found")))

(defn make-handler [cfg]
  (wrap-defaults (make-routes cfg) site-defaults))

(defn run-server [cfg]
  (run-jetty (make-handler cfg) {:port 3000}))

(defn -runServer [cfg]
  (run-server cfg))
