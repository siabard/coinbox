# Project.clj 를 만들기

`lein new app coinbox`를 해서 프로젝트를 구성한다.

source코드는 launcher와 실제 game 구조, 2개의 영역으로 구분된다.

`desktop/src`에는 main함수를 생성한다. 이 함수는 `desktop/src/coinbox/desktop/launcher.clj`에 넣게된다.

이 함수에서는 config를 생성하고 이를 `LwjglApplication`에 `Game` 객체가 parameter로 들어간다.

```
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
```

위의 예제를 본다면 만들게될 `Game` 클래스를 이용하여 Launcher를 올리는 것을 알 수 있다.

## gen-class 에 관한 것

`exposes-method`를 이용해 현재 클래스의 super method를 호출한다.

```
(ns coinbox.core
  ...
  (:gen-class
   :name coinbox.core.Game
   :extends com.badlogic.gdx.Game
   :exposes-methods {render parentRender}))

```

위의 `:gen-class`를 본다면 `:name`을 통해 Java용 클래스명을 만들고, `:extends`를 통해 어떤 클래스를 상속받을지 정하며, 이 중 `super.render()`를 호출하기위해 `:exposes-methods`를 이용하여 해당 메서드를 `parentRender`로 매핑해서 사용할 수 있게한다.

## postRunner

`Gfx/app` (이는 static member이다) 의 `.postRunner` 함수를 이용하여 게임 스레드에 영향을 줄 수 있다.

