(ns benchm8.server.db
  (:require [clojure.java.jdbc :refer :all]))


(defrecord DatabaseConfig [name create])


(defn create-db [cfg]
  (let [db {:classname   "org.sqlite.JDBC"
            :subprotocol "sqlite"
            :subname    (:name cfg)}]
    (if (:create cfg)
      (try (db-do-commands db (create-table-ddl :exception [[:id :int]
                                                            [:toString :text]
                                                            [:printStackTrace :text]]))
           (db-do-commands db (create-table-ddl :test [[:id :int]
                                                       [:id_group :int]
                                                       [:success :bool]
                                                       [:id_exception :int]
                                                       [:time_millis :int]]))
           (db-do-commands db (create-table-ddl :group [[:id :int]
                                                        [:time_started :int]
                                                        [:time_elapsed_millis :int]
                                                        [:success :bool]]))
           (catch Exception e (println e))))
    db))

