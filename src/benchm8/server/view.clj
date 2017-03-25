(ns benchm8.server.view
  (:require [hiccup.core :refer [html]]
            [clojure.data.json :as json]
            [benchm8.server.benchmark :refer [benchmarks]]
            [ring.util.response :as response]))

(defn index-view []
  (html
    [:html
      [:head
        [:meta {:charset "utf-8"}]
        [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
        [:title "benchm8"]]
      [:body
        [:div#app]
        [:script {:src "js/compiled/benchm8.js"}]]]))

(defn list-benchmarks []
  (-> (json/write-str (map #(assoc %1 2 (keys (last %1))) benchmarks))
      response/response
      (response/content-type "application/json")))
