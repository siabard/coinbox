(ns coinbox.core
  (:require [coinbox.screen :as screen]
            [coinbox.gamestate :as gamestate])
  (:import [java.lang Exception]
           [com.badlogic.gdx Game Gdx Input$Keys Graphics Screen]
           [com.badlogic.gdx.graphics Color GL20]
           [com.badlogic.gdx.graphics.g2d BitmapFont]
           [com.badlogic.gdx.scenes.scene2d Stage]
           [com.badlogic.gdx.scenes.scene2d.ui Label Label$LabelStyle])
  
  (:gen-class
   :name coinbox.core.Game
   :extends com.badlogic.gdx.Game
   :exposes-methods {render parentRender}))

(defn -render [this]
  (when (.isKeyJustPressed (Gdx/input) Input$Keys/ESCAPE)
    (.exit (Gdx/app)))
  (try
    (.parentRender this)
    (catch Exception e
      (.printStackTrace e)
      (.setScreen this screen/paused))))

(defn -create [^Game this]
  (reset! gamestate/game this)
  (gamestate/load-resources)
  (.setScreen this screen/paused))










