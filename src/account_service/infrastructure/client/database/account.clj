(ns account-service.infrastructure.client.database.account
  (:require [account-service.config.database.setting :refer [db-spec]]
            [clojure.java.jdbc :as jdbc]))

; RAW SQL
(def -fetchOneQuery
  "Select * From account where id = ?")

(def -fetchAll
  "Select * From account")


(def -fetchAllWithPageQuery
  "Select * From account Order By id Limit ? Offset ?")

(def -fetchOneByEmailAndPasswordQuery
  "Select * From account where email = ? and password = ?")


(defn save [entity]
  (first (jdbc/insert!
           db-spec
           :account entity)))

(defn change [entity]
  (first (jdbc/update!
           db-spec
           :account {:name (:name entity) :email (:email entity)} ["id = ?" (:id entity)])))

(defn changePassword [newPassword id]
  (first (jdbc/update!
           db-spec
           :account {:password newPassword} ["id = ?" id])))

(defn fetchAll
  ([]
   (jdbc/query
     db-spec
     [-fetchAll]))
  ([size offset]
   (jdbc/query
     db-spec
     [-fetchAllWithPageQuery size offset])))

(defn fetchOne [id]
  (first (jdbc/query
           db-spec
           [-fetchOneQuery id]
           )))

(defn delete [id]
  (jdbc/delete!
    db-spec :account ["id = ?" id]))
