(ns picture-gallery.util
  (:require [noir.session :as session]
            [hiccup.util :refer  [url-encode]])
  (:import java.io.File))

(def thumb-prefix "thumb_")

(def galleries "galleries")

(defn gallery-path []
  (str galleries File/separator (session/get :user)))

(defn image-uri [userid filename]
  (str "/img/" userid "/" (url-encode filename)))

(defn thumb-uri [userid file-name]
  (image-uri userid (str thumb-prefix file-name)))
