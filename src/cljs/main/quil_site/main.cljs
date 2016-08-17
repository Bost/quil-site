(ns quil-site.main
  (:require [goog.dom :as dom]
            [goog.dom.classes :as classes]
            [goog.events :as events]
            [goog.events.EventType :as EventType]
            [clojure.string :as cstr]
            [quil.core :as q :include-macros true]
            goog.Uri))

(enable-console-print!)

(defn query-selector
  ([element selector]
     (.querySelector element selector))
  ([selector]
     (query-selector js/document selector)))

(extend-type js/NodeList
  ISeqable
  (-seq [array] (array-seq array 0)))

(def examples (atom {}))

(def available-examples [])

(defn init-examples-page []
  (doseq [example-name available-examples]
    (let [host (.cloneNode (query-selector "#template") true)]
      (classes/remove host "hidden")
      (.appendChild (query-selector ".examples") host)
      (.removeAttribute host "id"))))

(defn init []
  (cond (query-selector ".container.examples-page")
        (init-examples-page)))

(events/listenOnce js/window EventType/LOAD init)

; hack to load examples with :optimizations :none
; note that this code should not run under :advanced optimizations
(when goog/DEBUG
  (doseq [example available-examples
          :let [namespace (str "quil_site.examples."(cstr/replace example " " "_"))]]
    ((aget js/goog "require") namespace)))

