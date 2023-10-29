(ns day-one.core
  (:gen-class))

;; part one
(defn count_braces [braces nums]
  (cond
    (empty? braces) nums
    (= (first braces) \() (recur (rest braces) (cons 1 nums))
    (= (first braces) \)) (recur (rest braces) (cons -1 nums))
    ))

;; part two
;;(defn find_basement [func position floor]
  ;;(case floor
    ;;-1

(defn -main
  [& args]
  ;; part one
  (println "The floor is: " (reduce + (count_braces (slurp "input.txt") '())))
  )
