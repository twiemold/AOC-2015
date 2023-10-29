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
(defn match_brace [brace]
  (case brace
    \( 1
    \) -1))

(defn find_basement [braces position floor]
  (let [x (+ (match_brace (first braces)) floor)]
  (case x
   ;; skipping base case since we know there is a solution
    -1 position
    (recur (rest braces) (+ 1 position) x))))



(defn -main
  [& args]
  ;; part one
  ;; (println "The floor is:" (reduce + (count_braces (slurp "input.txt") '())))
  ;; part two
  (println "The position when he reaches the basement is:" (find_basement (slurp "input.txt") 1 0))
  )
