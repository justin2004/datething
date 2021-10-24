(ns datething.core
  (:gen-class
   :extends org.apache.jena.sparql.function.FunctionBase1)
  (:require [duckling.core :as p]
            [clojure.pprint :as pp]))

; (p/load!)
; (p/get-classifier 'core$en)
; (p/classifiers-map)
; (pp/pprint 44  )

; (pp/pprint (p/parse :en$core
;          "21 October 2021" [:time]))

; (:value (:value (first (p/parse :en$core
;          "12 Sep 1999" [:time]))))


(comment (defn -exec ^org.apache.jena.sparql.expr.NodeValue [^org.apache.jena.sparql.expr.NodeValue & v]
  (new org.apache.jena.sparql.expr.nodevalue.NodeValueString
       (str "got dis:" (vec (p/parse :en$core
                                (.asString (first (.getList (nth v 2))))
                                [:time]))))))


(comment (defn -exec ^org.apache.jena.sparql.expr.NodeValue [^org.apache.jena.sparql.expr.NodeValue & v]
  (do (if (= @loaded true)
        nil
        (do
          (.println (java.lang.System/out)
                    "loading...")
          (p/load!)
          (reset! loaded true)))
      (new org.apache.jena.sparql.expr.nodevalue.NodeValueString
           (str (:value (:value (first (p/parse :en$core
                                                (.asString (first (.getList (nth v 2))))
                                                [:time])))))))))


; we need this so we don't keep loading the model
(def loaded (atom ()))
(reset! loaded false)

; TODO this assumes you want a time 
;   it would be nice to return the matched granularity (can we return multiple bindings?)
(defn -exec ^org.apache.jena.sparql.expr.NodeValue [^org.apache.jena.sparql.expr.NodeValue & v]
  (do (if (= @loaded true)
        nil
        (do
          (.println (java.lang.System/out)
                    "loading...")
          (p/load!) ; TODO how to load only english?
          (reset! loaded true)))
      (let [asked (.asVar (.get (.getList (nth v 2)) ; asked is the Var passed into the function
                                     0))
            looked (str (.getLiteralValue (.get (nth v 1) ; looked is the Var's value as found in the bindings
                              asked)))
            parsed (str (:value (:value (first (p/parse :en$core    ; parsed is the parsed Var's value
                                                        looked 
                                                        [:time])))))
            ] (new org.apache.jena.sparql.expr.nodevalue.NodeValueString parsed))))


(comment (defn -exec ^org.apache.jena.sparql.expr.NodeValue [^org.apache.jena.sparql.expr.NodeValue & v]
  (do (if (= @loaded true)
        nil
        (do
          (.println (java.lang.System/out)
                    "loading...")
          (p/load!)
          (reset! loaded true)))
      (new org.apache.jena.sparql.expr.nodevalue.NodeValueString
           (str (.get (nth v 1)
                      (org.apache.jena.sparql.core.Var/alloc "now")))))))


; (print (datething.core/-exec "hh" 44  ))
