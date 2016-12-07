(ns medipro.core
  (:use [tawny.owl] 
        [tawny.lookup])
  (:require [tawny
             [read]
             [polyglot]
             [reasoner :as r]
             [pattern :as p]])
  (:import (org.semanticweb.owlapi.model IRI OWLNamedObject OWLOntologyID)
           (org.semanticweb.owlapi.util SimpleIRIMapper))
  
  )

(defontology medipro
  :iri "http://mikeleganaaranguren.com/medipro"
  :prefix "medipro:"
  :comment "Testing medipro manipulation without loading an ontology"
  :versioninfo "Unreleased Version")

(as-inverse
 (defoproperty hasAnatomicalPart
   :characteristics :transitive)
 (defoproperty isAnatomicalPartf
   :characteristics :transitive
   )
 )

(defn generate-whole-parts [ & wholeparts]
  (doseq
      [[whole & parts] wholeparts]
    (owl-class
     whole
     :super (some-only hasAnatomicalPart parts))))

(declare-classes 
  stomach pylorus body-of-stomach fundus antrum
  eh bizkaia gipuzkoa araba nafarroa zuberoa behenafarroa lapurdi
  )

(generate-whole-parts 
  [stomach pylorus body-of-stomach fundus antrum]
  [eh bizkaia gipuzkoa araba nafarroa zuberoa behenafarroa lapurdi]
  )


(save-ontology "ontology.owl" :owl)








;(defn get-go-ontology []
;  (tawny.owl/remove-ontology-maybe
;   (OWLOntologyID. 
;     (IRI/create "http://purl.obolibrary.org/obo/go.owl")
;                   )
;   )
;  (.loadOntologyFromOntologyDocument
;   (tawny.owl/owl-ontology-manager)
;   (IRI/create 
;     (clojure.java.io/resource "go-snippet.owl")
;     )
;   )
;  )

;(println (get-current-ontology))

;(println (get-go-ontology))






;(with-ontology
;  get-go-ontology)
;
;(println (subclasses "http://purl.obolibrary.org/obo/GO_0000002"))
       
;(defontology "http://purl.obolibrary.org/obo/go.owl")

;(save-ontology "ontology.owl" :owl)
  
;(defn foo
;  "I don't do a whole lot."
;  [x]
;  (println x "Hello, World!"))
;
;
;(foo "xxx")
