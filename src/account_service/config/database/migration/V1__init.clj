(ns account-service.config.database.migration.V1--init
  (:require [clojure.java.jdbc :as jdbc]
            [account-service.config.database.setting :refer [db-spec]]
            [account-service.infrastructure.client.database.account :as account]))

(defn- create_init_db []
  (jdbc/create-table-ddl :account [
                                   [:id :serial :primary :key]
                                   [:name :varchar "not null"]
                                   [:email :varchar :unique "not null"]
                                   [:password :varchar "not null"]
                                   ]))
(defn- create_init_index []
  "CREATE INDEX account_name_ix ON account (name);")

(defn- insert_init_data []
  {
   :name "test"
   :email "test@example.com"
   :password "test"
   })


(defn- migrate []
  (jdbc/db-do-commands
    db-spec
    [create_init_db
     create_init_index]))

(defn- init-data[]
  (account/save insert_init_data))

(defn- migrate_delete[]
  (jdbc/db-do-commands
    db-spec
    (jdbc/drop-table-ddl :account)))