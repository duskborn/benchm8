(ns benchm8.api
  (:gen-class :name benchm8.Benchm8JavaApi
              :methods [#^{:static true} [createBenchm8Config [Object Object] Object]
                        #^{:static true} [createBenchm8DBConfig [Object Object] Object]
                        #^{:static true} [createBenchm8Test [Object Object] Object]]))

(defn -createBenchm8Test [name f]
  {:name name
   :fn f})

(defn -createBenchm8DBConfig [name create]
  {:name name
   :create create})

(defn -createBenchm8Config [dbcfg tests]
  {:dbcfg dbcfg
   :tests tests})
