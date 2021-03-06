(ns gravieproject.views.search
  (:require [reagent.core :as reagent :refer [atom]]
            [gravieproject.request :as http]
            [gravieproject.views.home :as home]
            [re-frame.core :as rf]
            [clojure.string :as str]))

(defn main []
  (let [search-text (atom "")
        records (rf/subscribe [:record-search])]
    (fn []
      [:<>
       [:main
        [:div.empty
         [:div
          [:input {:type "text"
                   :name "search"
                   :placeholder "Search your favorite game."
                   :value @search-text
                   :on-change #(reset! search-text (-> % .-target .-value))}]

          [:input.btn.btn--secondary {:type "button"
                   :value "Search"
                   :on-click #(http/search-game @search-text :record-search)}]]]
        [home/items @records]]
       [home/orders]])))