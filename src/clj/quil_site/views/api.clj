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

(defn- link
  ([to]
    (link to to))
  ([text to]
     (link text to nil))
  ([text to anchor]
     (let [page (if to
                  (str "/api/" (as-url to))
                  "")]
      (e/link-to (if anchor
                   (str page "#" (as-url anchor))
                   page)
                 (u/escape-html text)))))

(defn- render-type-specific [host content type what]
  (if (and (= what :fn) (not= type :both))
    (let [tooltip (str "Available only in "
                       (if (= type :clj)
                         "Clojure"
                         "ClojureScript")
                       " version.")
          type (clojure.core/name type)]
      [host [:span {:title tooltip}
                    content
                    [:sup type]]])
    [host content]))

(defn- render-subcategory-index [cat subcat fns]
  (let [fns (map (fn [{:keys [name type what]}]
                   (let [fn-link (link name
                                       (if subcat
                                         (str cat "/" subcat)
                                         cat)
                                       name)]
                    (render-type-specific :p.function fn-link
                                          type what)))
                 fns)]
    (if subcat
      (cons [:h4.subcategory (link subcat (str cat "/" subcat))]
            fns)
      fns)))

(defn- render-category-index [cat subcats]
  [:div.category-div
   [:h3.category (link cat)]
   (for [[subcat fns] subcats]
     (render-subcategory-index cat subcat fns))])

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
