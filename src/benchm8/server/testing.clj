(ns benchm8.server.testing)

(defrecord TestGroup [setUp fns tearDown])

(defn run-test [{:keys [setUp fns tearDown]}]
  (let [env (setUp)]
    (for [f fns]
      (let [time-start (System/nanoTime)]
        (let [measures (try (do (f env) {:success true
                                         :exception nil
                                         :time (- (System/nanoTime) time-start)})
                         (catch Throwable t {:success false
                                             :exception t
                                             :time (- (System/nanoTime) time-start)}))]
          (tearDown env)
          measures)))))

(defn run-tests [tests]
  (for [t tests]
    (try (run-test t)
      (catch Throwable t [{:success false
                           :exception t}]))))
