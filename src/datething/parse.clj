(ns datething.parse
  (:gen-class
   :extends org.apache.jena.sparql.function.FunctionBase1)
  (:require [duckling.core :as duck]
            [clojure.pprint :as pp]))

; we need this so we don't keep loading the classifiers
;  Duckling didn't export the function to check if we've already loaded
(def loaded (atom ()))
(reset! loaded false)


; TODO this assumes you want a time 
;   it would be nice to return the matched granularity (can we return multiple bindings?)
; also i am assuming positions in the NodeValue list (i should see if they are reliable)
;
; TODO
;  "Context is a map with a :reference-time key. If not provided, the system current date and time is used."
;  ^ alow passingin a reference-time from SPARQL
(defn -exec ^org.apache.jena.sparql.expr.NodeValue [^org.apache.jena.sparql.expr.NodeValue & v]
  (do (if (= @loaded true)
        nil
        (do
          (.println (java.lang.System/out)
                    "loading Duckling rules and classifiers...")
          (duck/load! {:languages '("en")}) ; load only english  --   TODO make this a param?
          (reset! loaded true)))
      (let [asked (.asVar (.get (.getList (nth v 2)) ; asked is the Var passed into the function
                                0))
            looked (str (.getLiteralValue (.get (nth v 1) ; looked is the Var's value as found (looked up) in the bindings
                                                asked)))
            parsed (str (:value (:value (first (duck/parse :en$core    ; parsed is the parsed Var's value
                                                           looked 
                                                           [:time])))))] 
        (new org.apache.jena.sparql.expr.nodevalue.NodeValueString parsed))))
