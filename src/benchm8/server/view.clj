(ns benchm8.server.view
  (:require [hiccup.core :refer [html]]))

(defn index-view []
  (html
    [:html
      [:head
        [:meta {:charset "utf-8"}]
        [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
        [:title "benchm8"]
        [:link {:href "css/style.css" :rel "stylesheet"}]]
      [:body
        [:div#app]
        [:script {:src "js/compiled/benchm8.js"}]]]))
