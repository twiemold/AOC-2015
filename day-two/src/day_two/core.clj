(ns day-two.core
  (:require
    [clojure.string :as str])
  (:gen-class))

(defn get-data 
  [filename process-func]
  (let [lines (line-seq (clojure.java.io/reader filename))]
    (process-func lines)))

(defn process-func 
  [lines]
  (map #(map read-string %) (map #(str/split %1 #"x") lines)))

;; part one
(defn calc_surface_area 
  [[l w h]] 
  (let [sides [(* l w) (* w h) (* h l)]]
    (+ (apply min sides) (reduce + (map (partial * 2) sides)))))

;; part two
(defn calc-ribbon-length
  [sides]
  (+ (reduce * sides) (reduce + (map (partial * 2) (take 2 (sort sides))))))


(defn -main
  [& args]
  ;; part one
  ;; (println "They should order this many sq ft:" (reduce + (map #(calc_surface_area %) (get-data "input.txt" process-func)))))
  ;; part two
  (println "They should order this many ft of ribbon:" (reduce + (map #(calc-ribbon-length %) (get-data "input.txt" process-func)))))
