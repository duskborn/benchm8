(defproject benchm8 "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.7.1"

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]
                 [org.clojure/core.async "0.2.395"
                  :exclusions [org.clojure/tools.reader]]
                 [org.clojure/data.json "0.2.6"]
                 [ring "1.5.0"]
                 [ring/ring-defaults "0.2.3"]
                 [compojure "1.5.2"]
                 [hiccup "1.0.5"]
                 [garden "1.3.2"]
                 [rum "0.10.8"]
                 [cljs-ajax "0.5.8"]]

  :plugins [[lein-figwheel "0.5.9"]
            [lein-cljsbuild "1.1.5" :exclusions [[org.clojure/clojure]]]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]
                :figwheel {:on-jsload "benchm8.client.core/on-js-reload"
                           ;; :open-urls ["http://localhost:3449/index.html"]
                           }
                :compiler {:main benchm8.client.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/benchm8.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true
                           :preloads [devtools.preload]}}
               {:id "min" ;; lein cljsbuild once min
                :source-paths ["src"]
                :compiler {:output-to "resources/public/js/compiled/benchm8.js"
                           :main benchm8.client.core
                           :optimizations :advanced
                           :pretty-print false}}]}

  :figwheel {;; :http-server-root "public" ;; default and assumes "resources"
             ;; :server-port 3449 ;; default
             ;; :server-ip "127.0.0.1"

             :css-dirs ["resources/public/css"] ;; watch and update CSS

             ;; Start an nREPL server into the running figwheel process
             ;; :nrepl-port 7888

             :ring-handler benchm8.server.core/handler

             ;; if you want to disable the REPL
             ;; :repl false

             ;; to configure a different figwheel logfile path
             ;; :server-logfile "tmp/logs/figwheel-logfile.log"
             }

  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.0"]
                                  [figwheel-sidecar "0.5.9"]
                                  [com.cemerick/piggieback "0.2.1"]]
                   :source-paths ["src"]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}}

)
