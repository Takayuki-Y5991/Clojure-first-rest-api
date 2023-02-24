(ns account-service.util.response
  (:require [clojure.data.json :as json]))

(def -header
  {"Content-Type" "application/json"})

(defn ok [body]
  {
   :status 200
   :header -header
   :body   (-> body
               (dissoc :password)
               (json/write-str))
   })

(defn noContent []
  {
   :status 204
   :header -header
   })

(defn notFound []
  {
   :status 404
   :header -header
   })

(defn created [body]
  {
   :status 201
   :header -header
   :body   (-> body
               (dissoc :password)
               (json/write-str))
   })

(defn forbidden [body]
  {
   :status 403
   :header -header
   :body   (-> body
               (json/write-str))
   }
  )

(defn ok-array [body]
  {
   :status 200
   :header -header
   :body   body
   })