(ns benchm8.client.data
  (:require [ajax.core :as ajax]))

(defn data-function [source]
  (fn [callback] (ajax/GET source {:handler callback})))
