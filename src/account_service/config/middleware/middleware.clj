(ns account-service.config.middleware.middleware
  (:require [ring.middleware.defaults :refer :all]
            [ring.middleware.json :as json]))

(defn wrapHandler [handler]
  (-> handler
      (wrap-defaults api-defaults)
      (json/wrap-json-body))
  )