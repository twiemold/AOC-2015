(ns day-one.core
  (:gen-class))

;; part one
(defn count_braces [braces nums]
  (if (empty? braces) nums
    (case (first braces)
      \( (recur (rest braces) (cons 1 nums))
      \) (recur (rest braces) (cons -1 nums)))))

;; part two
(defn find_basement [braces position floor]
  (let [x (+ (#(case % \( 1 \) -1) (first braces)) floor)]
  (case x
   ;; skipping base case since we know there is a solution
    -1 position
    (recur (rest braces) (+ 1 position) x))))



(defn -main
  [& args]
  ;; part one
  (println "The floor is:" (reduce + (count_braces (slurp "input.txt") '())))
  ;; part two
  (println "The position when he reaches the basement is:" (find_basement (slurp "input.txt") 1 0))
  )
