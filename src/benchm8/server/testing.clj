(ns benchm8.server.testing)

(defn run-test [{:keys [setUp test-fns tearDown]}]
  (let [env (setUp)]
    (for [tfn test-fns]
      (let [time-start (System/nanoTime)]
        (let [measures (try (do (tfn env) {:success true
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
