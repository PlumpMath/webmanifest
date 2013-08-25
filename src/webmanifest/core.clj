(ns webmanifest.core
  (:require [digest :as dig]
            [biscuit.core :as biscuit]))

(defn files-under-path
  "Given PATH, returns all files [and directories] underneath"
 [path]
 (let [file-path (java.io.File. path)
       files     (.listFiles file-path)
       helper    (fn [file] (if (.isDirectory file)
                              (files-under-path (.getAbsolutePath file))
                              [file]))]
   (mapcat helper files)))

(defn absolute-path->relative-path
  [prefix path]
  {:pre [(> (count path) (count prefix))]}
  (subs path (count prefix)))

(defn file->manifest-entry
  [prefix file]
  {(absolute-path->relative-path prefix (.getAbsolutePath file))
   (dig/md5 file)
   ;(biscuit/crc32 (slurp file))
   })

(defn files->manifest-entries
  [prefix files]
  (apply merge (pmap (partial file->manifest-entry prefix) files)))

(defn path->manifest
  [path]
  (files->manifest-entries path (files-under-path path)))

