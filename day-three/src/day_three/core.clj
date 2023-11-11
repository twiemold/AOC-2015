(ns day-three.core
  (:require [clojure.set :as set])
  (:gen-class))

;; part one
(defn unique-coords
  [direction curr coords]
  (if (empty? direction) 
    coords
    (let [[x y] curr]
      (case (first direction)
        \^ (recur (rest direction) (list x (+ y 1)) (conj coords (list x (+ y 1))))
        \> (recur (rest direction) (list (+ x 1) y) (conj coords (list (+ x 1) y)))
        \< (recur (rest direction) (list (- x 1) y) (conj coords (list (- x 1) y)))
        \v (recur (rest direction) (list x (- y 1)) (conj coords (list x (- y 1))))))))

;; part two
(defn merge-coords [instructions]
  (let [robo-santa (unique-coords (take-nth 2 instructions) '(0 0) (set '(0 0)))]
    (let [santa (unique-coords (take-nth 2 (rest instructions)) '(0 0) (set '(0 0)))]
      (set/union robo-santa santa))))

(defn -main
  [& args]
  ;; part one
  ;; (println "The count of houses visited at least once is:" (count (unique-coords (into [] (slurp "input.txt")) '(0 0) (set '(0 0)))))
  (println "The count of houses visited at least once is:" (count (merge-coords (into [] (slurp "input.txt"))))))
