(defproject datething "0.1.0-SNAPSHOT"
  :description "https://duckling.wit.ai/ for Apache Jena value functions"
  :url "https://github.com/justin2004/datething"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [wit/duckling "0.4.24"]
                 [org.apache.jena/apache-jena-libs "4.2.0" :extension "pom"]]
  :repl-options {:init-ns datething.parse}
  :profile {:uberjar {:aot :all
                      :debug true}}
  :main datething.parse) ; i think i just need this to get AOT when i run `lein uberjar`
                         ; TODO  just do AOT instead
