(ns benchm8.server.benchmark
  (:import [clojure.lang PersistentList]
           [java.util ArrayList]))

(defrecord Benchmark
  [meta-info
   measures])

(defmacro measure [& forms]
  `(let [start# (System/nanoTime)]
     ~@forms
     (- (System/nanoTime) start#)))

(def benchmarks
  [[:persistent-list (.getSimpleName PersistentList)
    {:conj  (fn [^PersistentList l i] (measure (conj l i)))
     :iter (fn [^PersistentList l] (measure (vec l)))}]
   [:array-list (.getSimpleName ArrayList)
    {:conj (fn [^ArrayList l i] (measure (conj l i)))}]])
