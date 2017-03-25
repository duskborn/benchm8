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
  {:persistent-list {:name     (-> PersistentList .getClass .getName)
                     :measures {:cons  (fn [^PersistentList l i] (measure (.cons l i)))
                                :clone (fn [^PersistentList l] 0)}}
   :array-list      {:name (-> ArrayList .getClass .getSimpleName)
                     :measures {:cons (fn [^ArrayList l i] (measure (.add l 0 i)))
                                :conj (fn [^ArrayList l i] (measure (.add l i)))
                                :clone (fn [^ArrayList l i] (measure (.clone l)))}}
   })
