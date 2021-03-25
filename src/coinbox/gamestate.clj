(ns coinbox.gamestate
  (:import [com.badlogic.gdx.graphics Texture]
           [com.badlogic.gdx Gdx]))

(defonce state (atom nil))
(defonce game (atom nil))

(defonce resources (atom nil))

(def resource-names
  {:polka "resources/polka-stand-0.png"})

(defn load-tex
  [tname]

  (Texture. (.internal (Gdx/files) tname))
  )
  

(defmacro on-gl
  [form]
  `(.postRunnable Gdx/app (fn [] ~form)))

(defn load-resources
  []
  (->> (into [] resource-names)
       (map #(update % 1 load-tex))
       (into {})
       (reset! resources)))







