(defproject coinbox "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [com.badlogicgames.gdx/gdx "1.9.3"]
                 [com.badlogicgames.gdx/gdx-backend-lwjgl "1.9.3"]
                 [com.badlogicgames.gdx/gdx-platform "1.9.3"
                  :classifier "natives-desktop"]
                 ]
  :plugins [[cider/cider-nrepl "0.25.2"]]
  :source-paths ["desktop/src" "src"]
  :aot [coinbox.desktop.launcher]
  :main coinbox.desktop.launcher
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
