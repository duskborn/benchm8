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
     :bench-fn conj}]

   [:presistent-vector-conj
    (format "%s conj" (.getSimpleName PersistentVector))
    {:initial-state (fn [] [])
     :bench-fn conj}]

   [:array-list-conj
    (format "%s conj" (.getSimpleName ArrayList))
    {:initial-state (fn [] (ArrayList.))
     :bench-fn conj}]])


(defn measure-benchmark-step [{:keys [state bench-fn]} step]
  {:state (bench-fn state step)
   :bench-fn bench-fn})


(defn measure-benchmark [benchmark tries]
  (let [{:keys [initial-state bench-fn]} benchmark]
    (reduce measure-benchmark-step {:state (initial-state)
                                    :bench-fn bench-fn} (range tries))))


(defn measure-benchmarks [benchmarks]
  (map #(measure-benchmark % 100) benchmarks))
