(ns quil-site.views.api
  (:require [quil-site.views.page :refer [page]]
            [hiccup.page :as p]
            [hiccup.element :as e]
            [hiccup.util :as u]
            [clojure.string :as string]))

(defn as-url [str]
  (-> str
      (string/lower-case)
      (string/replace  #"[ &,?]" {" " "-"
                                  "," ""
                                  "&" "and"
                                  "?" "_q"})))

(defn api-index [functions]
  (page {:type :api
         :tab :api
         :title "Quil API"
         :js-files ["/js/api.js"]}))

(defn api-category [cat subcats]
  (page {:type :api
         :tab :api
         :title (str "Quil API " cat)
         :js-files ["/js/api.js"]}))

(defn api-subcategory [cat subcat fns]
  (page {:type :api
         :tab :api
         :title (str "Quil API " subcat)
         :js-files ["/js/api.js"]}))
