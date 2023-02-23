(ns account-service.core
  (:require
    [account-service.application.handler.root :refer [handler]]
    [account-service.config.database.migration.V1--init]
    [ring.adapter.jetty :as server]))


(defonce server (atom nil))

(defn- start-server []
  (when-not @server
    (reset! server (server/run-jetty handler {:port 8000 :join? false}))))

(defn -main []
  start-server)
