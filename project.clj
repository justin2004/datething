(defproject datething "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.10.0"] 
                 [wit/duckling "0.4.24"]
                 [org.apache.jena/apache-jena-libs "4.2.0" :extension "pom"]]
  :repl-options {:init-ns datething.core}
  :profile {:uberjar {:aot :all
                      :debug true}}
  :main datething.core) ; i think i just need this to get AOT when i run `lein uberjar`
