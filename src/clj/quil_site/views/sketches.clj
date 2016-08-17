(ns quil-site.views.sketches
  (:require [quil-site.views.page :refer [page]]
            [hiccup.page :as p]))

(defn sketch-page [id local?]
  (page {:tab :create
         :type :sketch
         :container-class "container-fluid"
         :title (str "Quil " id)
         :css-files ["/css/codemirror-5.12.css"
                     "/css/codemirror-5.12-lint.css"]
         :js-files ["/js/codemirror-5.12-clojure_lint_matchbrackets_closebrackets.js"
                    "/js/editor.js"]}

        [:div
         [:div.btn-group
          [:button#send.btn.btn-primary
           {:data-toggle "tooltip"
            :data-placement "bottom"
            :title "Run sketch. Use Ctrl+Enter to eval selected code or eval a form under cursor."}
           "Run"]
          [:button#reset.btn
           {:data-toggle "tooltip"
            :data-placement "bottom"
            :title "Reset drawing area. Doesn't affect code."}
           "Clear"]]
         [:dev#result-status.alert
          {:role "alert"}]]

        [:div#content
         [:div#source-content
          [:img#sketch-loading {:src "/img/sketch_loading.gif"}]
          [:div.hidden
           [:textarea#source
            {:data-sketch-id id
             :data-is-local (str local?)}]]]
         [:div#result-content
          {:style "width: 0px;"}
          [:iframe {:src "/iframe.html"
                    :width "0"
                    :height "0"}]]]))
