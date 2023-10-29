(ns day-one.core
  (:gen-class))

(defn count_braces [func braces nums]
  (cond
    (empty? braces) nums
    (= (first braces) \() (recur func (rest braces) (cons 1 nums))
    (= (first braces) \)) (recur func (rest braces) (cons -1 nums))
    ))

(defn -main
  [& args]
  (println "The floor is: " (reduce + (count_braces count_braces (slurp "input.txt") '()))))
