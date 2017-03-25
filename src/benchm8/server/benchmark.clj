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
  {:persistent-list {:name (.getSimpleName PersistentList)
                     :measures {:conj  (fn [^PersistentList l i] (measure (conj l i)))}}
   :array-list      {:name (.getSimpleName ArrayList)
                     :measures {:conj (fn [^ArrayList l i] (measure (conj l i)))}}
   })
