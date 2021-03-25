(ns coinbox.screen
  (:require [coinbox.gamestate :as gamestate :refer [resources]]
            [coinbox.player :as player])
  (:import [com.badlogic.gdx Game Gdx Input Input$Keys Graphics Screen]
           [com.badlogic.gdx.graphics Color GL20 ]
           [com.badlogic.gdx.graphics.g2d BitmapFont SpriteBatch]
           [com.badlogic.gdx.scenes.scene2d Stage]
           [com.badlogic.gdx.scenes.scene2d.ui Label Label$LabelStyle]))

(declare main)
(declare paused)

(defn screen! [s]
  (.postRunner Gdx/app
   #(.setScreen @gamestate/game s)))

(def main
  (let [stage (atom nil)
        batch (atom nil)]
    (proxy [Screen] []
      (show []
        (reset! gamestate/state {:player {:x 0 :y 0}})
        (reset! stage (Stage.))
        (reset! batch (SpriteBatch.))
        (let [style (Label$LabelStyle. (BitmapFont. ) (Color. 1 1 1 1))
              label (Label. "MAIN SCREEN" style)]
          (.addActor @stage label)))
      (render [delta]
        (.glClearColor (Gdx/gl) 0.2 0.2 0.2 0)
        (.glClear (Gdx/gl) GL20/GL_COLOR_BUFFER_BIT)
        (when (.isKeyJustPressed Gdx/input Input$Keys/P)
          (screen! paused))
        (when (.isKeyJustPressed Gdx/input Input$Keys/R)
          (gamestate/load-resources))

        ;; update game
        (->> @gamestate/state
             (player/act)
             (reset! gamestate/state))

        ;; render
        (.begin @batch)
        (.draw @batch (:polka @resources)
               (float (-> @gamestate/state (:player) (:x)))
               (float (-> @gamestate/state (:player) (:y)))
               (float 100)
               (float 100))
        (.end @batch)
        (doto @stage
          (.act delta)
          (.draw)))
      (displose [])
      (hide [])
      (pause [])
      (resize [w h])
      (resume []))))

(def paused
  (let [stage (atom nil)]
    (proxy [Screen] []
      (show []
        (reset! stage (Stage.))
        (let [style (Label$LabelStyle. (BitmapFont. ) (Color. 1 1 1 1))
              label (Label. "PAUSE!!" style)]
          (.addActor @stage label)))
      (render [delta]
        (.glClearColor (Gdx/gl) 0 0 0 0)
        (.glClear (Gdx/gl) GL20/GL_COLOR_BUFFER_BIT)
        (when (.isKeyJustPressed Gdx/input Input$Keys/P)
          (screen! main))
        (doto @stage
          (.act delta)
          (.draw)))
      (displose [])
      (hide [])
      (pause [])
      (resize [w h])
      (resume []))))

