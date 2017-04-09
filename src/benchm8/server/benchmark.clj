(ns benchm8.server.benchmark
  (:import [clojure.lang PersistentList PersistentVector]
           [java.util ArrayList]))

(defmacro measure [& forms]
  `(let [start# (System/nanoTime)]
     ~@forms
     (- (System/nanoTime) start#)))

(def benchmarks
  [[:persistent-list-conj
    (format "%s conj" (.getSimpleName PersistentList))
    {:initial-state (fn [] '())
     :benchmarking conj}]

   [:presistent-vector-conj
    (format "%s conj" (.getSimpleName PersistentVector))
    {:initial-state (fn [] [])
     :benchmarking conj}]

   [:array-list-conj
    (format "%s conj" (.getSimpleName ArrayList))
    {:initial-state (fn [] (ArrayList.))
     :benchmarking conj}]])


(defn measure-benchmark [benchmark tries]
  (let [{:keys [initial-state benchmarking]} benchmark]
    (let [data (transient '())]
      )))


(defn measure-benchmarks [benchmarks]
  (map #(measure-benchmark % 100) benchmarks))
