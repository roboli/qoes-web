(require '[cljs.build.api :as b])

(b/build "src"
         {:main 'qoes.core
          :output-to "out/main.js"
          :optimizations :advanced
          :verbose true
          :infer-externs true
          :externs ["externs/theme.js"]})
