(defproject webmanifest "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :plugins [[lein-ring "0.8.5"]]
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[biscuit "1.0.0"]
                 [org.clojure/clojure "1.5.1"]
                 [cheshire "5.2.0"]
                 [compojure "1.1.5"]
                 [digest "1.3.0"]]
  :ring {:handler webmanifest.handler/app})
