(ns coinbox.desktop.launcher
  (:require [coinbox.core :refer :all])
  (:import [com.badlogic.gdx.backends.lwjgl LwjglApplication LwjglApplicationConfiguration]
           [org.lwjgl.input Keyboard])
  (:gen-class))

(defn -main
  []
  (let [config (LwjglApplicationConfiguration.)]
        (set! (.forceExit config) false)
        (set! (.title config) "Coinbox Here Rebooted")
        (LwjglApplication. (coinbox.core.Game.) config)
        (Keyboard/enableRepeatEvents true)))
