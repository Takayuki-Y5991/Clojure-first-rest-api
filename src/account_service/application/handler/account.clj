(ns account-service.application.handler.account
  (:require [account-service.service.account :as service]
            [compojure.core :refer [DELETE GET PATCH POST context defroutes]]))

(defroutes accountHandler
           (context "/api/account" _
             (GET "/" req service/fetchAll)
             (GET "/:id" req service/fetchOne)
             (DELETE "/:id" req service/delete)
             (POST "/" req service/create)
             (PATCH "/:id" req service/update)
             ;; FIXME: JWT Tokenからユーザ情報を取得するように修正
             (PATCH "/changePassword/:id" req service/changePassword)))