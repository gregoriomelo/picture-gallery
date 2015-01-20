(ns picture-gallery.routes.gallery
  (:require [compojure.core :refer :all]
            [hiccup.element :refer :all]
            [picture-gallery.views.layout :as layout]
            [picture-gallery.util :refer :all]
            [picture-gallery.models.db :as db]
            [noir.session :as session]))

(defn thumbnail-link [{:keys [userid name]}]
  [:div.thumbnail
   [:a {:href (image-uri userid name)}
    (image (thumb-uri userid name))]])

(defn display-gallery [userid]
  (or
    (not-empty (map thumbnail-link (db/images-by-user userid)))
    [:p "The user " userid " does not have any galleries"]))

(defroutes gallery-routes
  (GET "/gallery/:userid" [userid] (layout/common (display-gallery userid))))
