(ns datething.scratch
  (:gen-class
    :extends org.apache.jena.sparql.function.FunctionBase1)
  (:require [duckling.core :as p]
            [clojure.pprint :as pp]))

; don't look here -- just a scratch pad



; (p/load!)
; (pp/pprint 44  )
; (pp/pprint (p/parse :en$core
;          "21 October 2021" [:time]))

; (p/load! {:languages '("en")})
; (p/get-classifier 'core$en)
; (p/classifiers-map)
; (pp/pprint 44  )

; (pp/pprint (p/parse :en$core
;          "eastern daylight savings time" [:timezone]))

; (do
;  (print "_") 
;  (pp/pprint (p/parse :en$core
;          "40000000 days" [:duration]))
;   (print "_"))

; (:value (:value (first (p/parse :en$core
;          "12 Sep 1999" [:time]))))



(def ret (new org.apache.jena.sparql.expr.nodevalue.NodeValueString
     "done"))

(defn bob [l]
  (println "justin")
  ret)

(comment (compile 'datething.scratch))

; (def prx (proxy [org.apache.jena.sparql.function.FunctionBase1] []
;     (exec 
;       ([l] (println "justin") ret))))

(comment 
  (.exec prx (new org.apache.jena.sparql.expr.nodevalue.NodeValueString
                  "hih"))

  (.put  (org.apache.jena.sparql.function.FunctionRegistry/get)
         "http://example.org/function#myFunction"
         (class prx))

  ; (jstatic "createDataset" (jclass "org.apache.jena.tdb2.TDB2Factory"))
  (def ds (org.apache.jena.tdb2.TDB2Factory/createDataset))
  (class prx)
  prx)




;;;;;;;;;;;;;;;;

; gen-class

(print *e)
(print *ns*)

;; Defining a custom constructor which calls a superclass constructor:
; (ns my.CustomException)
(gen-class
 :name "datething.core.Fred"
 ; :implements [clojure.lang.IExceptionInfo]
 ; :extends java.lang.RuntimeException
 ; :constructors {[String Throwable clojure.lang.IPersistentMap] [String Throwable]} ; mapping of my-constructor -> superclass constuctor
 ; :init init
 ; :state state ; name for the var that holds your internal state
 ; :main false
 )

(defn my-ex-init [msg t context]
  ;; first element of vector contains parameters for the superclass constructor
  ;; Second element will be your internal state 
  [[msg t] context]) 

(defn my-ex-getData [this]
  (.state this)) ; accessing the internal state



;;;;;;;;


;; It is possible to attach Java annotations to the class,
;; constructors, and methods 
;; Source: https://github.com/clojure/clojure/blob/8af7e9a92570eb28c58b15481ae9c271d891c028/test/clojure/test_clojure/genclass/examples.clj#L34
(print 4454)
(print *e)
 clojure.test_clojure.genclass.examples.ExampleAnnotationClass
(gen-class :name ^{Deprecated {}
                   SuppressWarnings ["Warning1"] ; discarded
                   java.lang.annotation.Target []}
                 clojure.test_clojure.genclass.examples.ExampleAnnotationClass
           :prefix "annot-"
           :methods [[^{Deprecated {}
                        Override {}} ;discarded
                      foo [^{java.lang.annotation.Retention java.lang.annotation.RetentionPolicy/SOURCE
                             java.lang.annotation.Target    [java.lang.annotation.ElementType/TYPE
                                                             java.lang.annotation.ElementType/PARAMETER]}
                           String] void]])

;;;;;;;;;;

(def aa (clojure.core/gen-class :name com.bob.thing))
(type aa)
aa
