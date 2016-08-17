(ns quil-site.core
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.handler :refer [site]]
            [compojure.route :refer [files]]
            [ring.util.response :as resp]
            [ring.middleware.json :as json]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.stacktrace :as stacktrace]
            [quil-site.controllers.sketches :as sketches]
            [quil-site.controllers.api :as api]))

(defroutes app
  sketches/routes
  (files "/out-main" {:root "out-main"})
  (files "/out-editor" {:root "out-editor"})
  (files "/out-preload" {:root "out-preload"})
  (files "/"))

(defn dump-request [handler]
  (fn [req]
    (clojure.pprint/pprint req)
    (handler req)))

(def handler
  (-> #'app
      ;dump-request
      site
      (json/wrap-json-body {:keywords? true})
      json/wrap-json-response
      stacktrace/wrap-stacktrace))

(defn run [port]
  (run-jetty handler {:port (Integer/parseInt port)}))

(comment

 (def server (run-jetty #(handler %) {:port 8080 :join? false}))

 (.stop server)

)
