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

