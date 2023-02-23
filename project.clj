(defproject account-service "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [ring "1.4.0"]
                 [compojure "1.4.0"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [org.postgresql/postgresql "42.5.4"]
                 [org.clojure/data.json "2.4.0"]
                 [ring/ring-defaults "0.3.4"]
                 [org.clojure/tools.logging "1.2.4"]
                 ]
  :repl-options {:init-ns account-service.core})
