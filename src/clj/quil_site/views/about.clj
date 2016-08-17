(ns quil-site.views.about
  (:require [quil-site.views.page :refer [page]]
            [hiccup.element :refer [link-to]]))

(defn about-page []
  (page {:tab :about
         :type :about
         :js-files ["/js/main.js"]
         :title "Quil: animation in Clojure"}))
