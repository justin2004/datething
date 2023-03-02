(ns datething.jaro
  (:gen-class
   :extends org.apache.jena.sparql.function.FunctionBase2)
  (:require [clojure.pprint :as pp]))

; (def jw (new info.debatty.java.stringsimilarity.JaroWinkler))
; (.distance jw "atlanta, ga" "atlanta")

(defn -exec ^org.apache.jena.sparql.expr.NodeValue [^org.apache.jena.sparql.expr.NodeValue & v ]
  (do 
    ; (.println java.lang.System/err "you passed in") 
    ;   (.println java.lang.System/err v)
      (let [asked0 (.asVar (.get (.getList (nth v 2)) ; asked is the Var passed into the function
                                0))
            asked1 (.asVar (.get (.getList (nth v 2)) ; asked is the Var passed into the function
                                1)) 
            looked0 (str (.getLiteralValue (.get (nth v 1) ; looked is the Var's value as found (looked up) in the bindings
                                                asked0)))
            looked1 (str (.getLiteralValue (.get (nth v 1) ; looked is the Var's value as found (looked up) in the bindings
                                                asked1)))
            jaroDistance (.distance (new info.debatty.java.stringsimilarity.JaroWinkler)
                                    looked0 looked1) ]

      ; (.println java.lang.System/err "asked0")
      ; (.println java.lang.System/err looked0)
      ; (.println java.lang.System/err "asked1")
      ; (.println java.lang.System/err looked1)
      (new org.apache.jena.sparql.expr.nodevalue.NodeValueFloat jaroDistance))))

(comment  (defn -exec ^org.apache.jena.sparql.expr.NodeValue [^org.apache.jena.sparql.expr.NodeValue & v]
  (do (let [asked (.asVar (.get (.getList (nth v 2)) ; asked is the Var passed into the function
                                0))
            looked (str (.getLiteralValue (.get (nth v 1) ; looked is the Var's value as found (looked up) in the bindings
                                                asked)))
            parsed (str (:value (:value (first (duck/parse :en$core    ; parsed is the parsed Var's value
                                                           looked 
                                                           [:time])))))] 
        (new org.apache.jena.sparql.expr.nodevalue.NodeValueString parsed)))))
