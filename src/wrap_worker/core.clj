
(ns wrap-worker.core
  (:use [worker.core :only [worker]]))

(defn- param-vector
  "Create ordered vector from all params"
  [req]
  (let [params (apply vector
                 (flatten
                   (apply list (:params req))))]
    (conj params (:uri req))))

;; Public
;; ------

(defn wrap-worker [handler]
  (fn [req]
    (if-not (= :get (:request-method req))
      (handler req)
      (worker
        (param-vector req)
        (handler req)))))

