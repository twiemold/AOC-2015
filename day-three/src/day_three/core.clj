(ns day-three.core
  (:require [clojure.set :as set])
  (:gen-class))

;; part one
(defn unique-coords [[direction x y coords]]
  (if (empty? direction) 
    coords
    (case (first direction)
        \^ (recur [(rest direction) x (+ y 1) (conj coords [x (+ y 1)])])
        \> (recur [(rest direction) (- x 1) y (conj coords [(- x  1) y])])
        \< (recur [(rest direction) (+ x 1) y (conj coords [(+ x  1) y])])
        \v (recur [(rest direction) x (- y 1) (conj coords [x (- y 1)])]))))

;; part two
(defn merge-coords [instructions]
  (let [robo-santa (unique-coords [(take-nth 2 instructions) 0 0 #{}])
        santa (unique-coords [(take-nth 2 (rest instructions)) 0 0 #{}])]
    (set/union robo-santa santa)))

(defn -main
  []
  ;; part one
  (println "The count of houses visited at least once is:"
           (count (unique-coords [(slurp "input.txt") 0 0 #{}])))
  (println "The count of houses visited at least once is:"
           (count (merge-coords (slurp "input.txt")))))
