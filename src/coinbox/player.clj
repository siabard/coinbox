(ns coinbox.player
  (:import [com.badlogic.gdx Gdx Input Input$Keys])
  )

(defn act
  [state]
  (cond-> state
    (.isKeyPressed Gdx/input Input$Keys/L) (update-in [:player :x] inc)
    (.isKeyPressed Gdx/input Input$Keys/H) (update-in [:player :x] dec)
    (.isKeyPressed Gdx/input Input$Keys/K) (update-in [:player :y] inc)
    (.isKeyPressed Gdx/input Input$Keys/J) (update-in [:player :y] dec)))


    
