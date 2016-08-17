(ns quil-site.views.examples
  (:require [quil-site.views.page :refer [page]]
            [hiccup.element :refer [link-to]]))

(defn examples-page []
  (page {:tab :examples
         :type :examples-page
         :js-files ["/js/main.js"]
         :title "Quil Examples"}))
