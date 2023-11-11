(ns day-three.core
  (:gen-class))

;; part one
(defn count-unique-coords
  [direction curr coords]
  (if (empty? direction) 
    coords
    (let [[x y] curr]
      (case (first direction)
        \^ (recur (rest direction) (list x (+ y 1)) (conj coords (list x (+ y 1))))
        \> (recur (rest direction) (list (+ x 1) y) (conj coords (list (+ x 1) y)))
        \< (recur (rest direction) (list (- x 1) y) (conj coords (list (- x 1) y)))
        \v (recur (rest direction) (list x (- y 1)) (conj coords (list x (- y 1))))))))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "The count of houses visited at least once is:" (count (count-unique-coords (into [] (slurp "input.txt")) '(0 0) (set '())))))
