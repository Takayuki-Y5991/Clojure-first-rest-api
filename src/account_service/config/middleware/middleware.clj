(ns account-service.config.middleware.middleware
  (:require [ring.middleware.defaults :refer :all]))

(defn wrapHandler [handler]
  (-> handler
      (wrap-defaults api-defaults))
  )