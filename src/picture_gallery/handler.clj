(ns picture-gallery.handler
  (:require [compojure.core :refer [defroutes routes]]
            [compojure.route :as route]
            [picture-gallery.routes.home :refer [home-routes]]
            [noir.util.middleware :as noir-middleware]
            [picture-gallery.routes.auth :refer [auth-routes]]
            [picture-gallery.routes.upload :refer [upload-routes]]
            [picture-gallery.routes.gallery :refer [gallery-routes]]
            [noir.session :as session]))

(defn user-page [_]
  (session/get :user))

(defn init []
  (println "picture-gallery is starting"))

(defn destroy []
  (println "picture-gallery is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (noir-middleware/app-handler
    [auth-routes
     home-routes
     upload-routes
     gallery-routes
     app-routes]
    :access-rules [user-page]))
