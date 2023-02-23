(ns account-service.application.handler.root
  (:require [account-service.application.handler.account :refer [accountHandler]]
            [compojure.core :refer [GET defroutes routes]]
            [compojure.route :as route]))

(defroutes rootHandler
           (GET "/api" _ "HOME")
           (route/not-found "NOT FOUND"))

(defroutes handler
           (routes
             accountHandler
             rootHandler))