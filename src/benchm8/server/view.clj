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
        [:link {:rel "stylesheet" :href "css/style.css"}]
        [:title "benchm8"]]
      [:body
        [:div#app]
        [:script {:src "js/compiled/benchm8.js"}]]]))

(defn list-benchmarks []
  (-> (json/write-str (map drop-last benchmarks))
      response/response
      (response/content-type "application/json")))
