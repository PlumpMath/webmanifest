(ns webmanifest.handler
  (:use compojure.core)
  (:require [compojure.handler]
            [compojure.route :as route]
            [cheshire.core :as json]
            [webmanifest.core :as wmc]))

(def version "0.1.0-SNAPSHOT")

(def base-path
  (or (System/getenv "WEBMANIFEST_DIR") "/tmp"))

(def files-root base-path)

(defroutes my-routes
  (route/files "/" (do (println "Serving files from: " files-root) {:root files-root}))
  (GET "/cgi-bin/manifest.py" {params :params}
       (let [path     (str base-path "/" (:environment params))
             manifest (when (:environment params)
                        (wmc/path->manifest path))
             data     (json/generate-string (or manifest {}))]
         {:headers {"Content-Type" "application/json"}
          :body    data}))
  (route/not-found "<h1>Page not found</h1>"))

(def app
  (-> my-routes
      compojure.handler/api))
