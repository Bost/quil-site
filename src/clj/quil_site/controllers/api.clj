(ns quil-site.controllers.api
  (:require [compojure.core :refer :all :exclude [routes]]
            [compojure.route :refer [not-found]]
            [ring.util.response :as resp]
            [clojure.tools.reader.edn :as edn]
            [clojure.tools.reader.reader-types :refer [string-push-back-reader]]))

(defn map-map [f map]
  (into {} (for [[k v] map] [k (f v)])))

(defn split-into-categories [functions]
  (let [categories (->> functions
                        vals
                        (group-by :category))]
    (map-map #(->> %
                   (sort-by :name)
                   (group-by :subcategory)
                   (into (sorted-map)))
             categories)))

(def all-fns (edn/read-string (slurp "api-2.4.0.clj")))

(def fns-by-categories (split-into-categories all-fns))


