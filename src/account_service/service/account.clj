(ns account-service.service.account
  (:require [account-service.infrastructure.client.database.account :as account]
            [account-service.util.response :refer [created forbidden noContent notFound ok]]
            [clojure.data.json :as json]
            [clojure.tools.logging :as log]
            [ring.util.codec :as codec]))

(defn fetchAll [{:as req :keys [params]}]
  (if (and (some? (:offset params)) (some? (:size params)))
    (do
      (let [result (account/fetchAll
                     (Long/parseLong (:size params)) (Long/parseLong (:offset params)))]
        (log/info result))
      )
    (do
      (let [result account/fetchAll]
        (log/info result)
        (log/info (json/write-str result))
        )
      ))
  )

(defn fetchOne [{:keys [params]}]
  (if-let [result (not-empty (account/fetchOne (Long/parseLong (:id params))))]
    (-> result
        (ok))
    (notFound)))

(defn create [request]
  (let [body (slurp (:body request) :encoding "utf-8")
        params (codec/form-decode body "utf-8")]
    (-> (account/save (json/read-str params :key-fn keyword))
        (created))
    )
  )

(defn delete [{:keys [params]}]
  (-> (:id params)
      (Long/parseLong)
      (account/delete)
      )
  (noContent)
  )

(defn update [request]
  (let [body (slurp (:body request) :encoding "utf-8")
        params (codec/form-decode body "utf-8")]
    (-> (json/read-str params :key-fn keyword)
        (assoc :id (Long/parseLong (:id (:params request))))
        (account/change)
        )
    (-> (Long/parseLong (:id (:params request)))
        (account/fetchOne)
        (ok))
    )
  )

(defn changePassword [request]
  (if-let [personal (account/fetchOne (Long/parseLong (:id (:params request))))]
    (let [body (slurp (:body request) :encoding "utf-8")
          params (codec/form-decode body "utf-8")]
      (let [requestParam (json/read-str params :key-fn keyword)]
        (if (= (:password personal) (:currentPassword requestParam))
          (-> (account/changePassword (:newPassword requestParam) (Long/parseLong (:id (:params request))))
              (ok))
          (forbidden {"message" "you can't change password, incorrect current password"}))
        )
      )
    (notFound)
    ))
