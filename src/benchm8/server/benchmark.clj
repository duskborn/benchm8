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
  [[:persistent-list-conj
    (format "%s conj" (.getSimpleName PersistentList))
    (fn [l i] (conj l i))]

   [:array-list-conj
    (format "%s conj" (.getSimpleName ArrayList))
    (fn [l i] (conj l i))]])


(defn measure-benchmark [benchmark]
  (let [funk (last benchmark)]
    
    )
  )


(defn measure-benchmarks [benchmarks]
  (map #(measure-benchmark %) benchmarks))
