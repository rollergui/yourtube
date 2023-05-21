(ns build
  (:require [clojure.tools.build.api :as b]))

(def build-dir "target")
(def basis (b/create-basis {:project "deps.edn"}))
(def class-dir (str build-dir "/classes"))

(defn clean [_]
  (b/delete {:path build-dir}))

(defn uber [_]
  (clean nil)
  (b/compile-clj {:basis basis
                  :src-dirs ["src"]
                  :class-dir class-dir})
  (b/uber {:class-dir class-dir
           :uber-file (str build-dir "/yourtube.jar")
           :basis basis
           :main 'yourtube})
  )