(ns medipro.core
  (:use [tawny.owl] 
        [tawny.lookup]
        [tawny.profile])
  (:require [tawny
             [read]
             [polyglot]
             [reasoner :as r]
             [pattern :as p]])
  (:import (org.semanticweb.owlapi.model IRI OWLNamedObject OWLOntologyID)
           (org.semanticweb.owlapi.util SimpleIRIMapper))
  
  )

(defn get-medipro-ontology []
  (tawny.owl/remove-ontology-maybe
   (OWLOntologyID. 
     (IRI/create "http://mikeleganaaranguren.com/MediPRO/AnatomicalPartonomy")
                   )
   )
  (.loadOntologyFromOntologyDocument
   (tawny.owl/owl-ontology-manager)
   (IRI/create 
     (clojure.java.io/resource "AnatomicalPartonomy.owl")
     )
   )
  )

(def medipro 
  (get-medipro-ontology)
  )

(println 
  (inprofile? medipro profile-owl2el)
  )

(defclass mikel
  :ontology medipro
  :label "mikel")

(save-ontology medipro "AnatomicalPartonomy2.owl" :owl)





;;;; Make loaded ontology the default (not recommended)

;(ontology-to-namespace 
;  (get-medipro-ontology)
;  )
;
;(declare-classes 
;  eh bizkaia gipuzkoa araba nafarroa zuberoa behenafarroa lapurdi
;  bilbo donosti berlin
;  )
;
;(save-ontology "AnatomicalPartonomy2.owl" :owl)



;;;; Create ontology from scratch

;(defontology medipro
;  :iri "http://mikeleganaaranguren.com/medipro"
;  :prefix "medipro:"
;  :comment "Testing medipro manipulation without loading an ontology"
;  :versioninfo "Unreleased Version")
;
;(as-inverse
; (defoproperty hasAnatomicalPart
;   :characteristics :transitive)
; (defoproperty isAnatomicalPartf
;   :characteristics :transitive
;   )
; )
;
;(defn generate-whole-parts [ & wholeparts]
;  (doseq
;      [[whole & parts] wholeparts]
;    (owl-class
;     whole
;     :super (some-only hasAnatomicalPart parts))))
;
;(declare-classes 
;  stomach pylorus body-of-stomach fundus antrum
;  eh bizkaia gipuzkoa araba nafarroa zuberoa behenafarroa lapurdi
;  )
;
;(generate-whole-parts 
;  [stomach pylorus body-of-stomach fundus antrum]
;  [eh bizkaia gipuzkoa araba nafarroa zuberoa behenafarroa lapurdi]
;  )
;
;(save-ontology "ontology.owl" :owl)
