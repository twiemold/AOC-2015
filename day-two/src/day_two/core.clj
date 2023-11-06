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
  (into [] (map #(map read-string %) (map #(str/split %1 #"x") lines))))

(defn calc_surface_area 
  [[l w h]] 
  (let [sides [(* l w) (* w h) (* h l)]]
    (+ (apply min sides) (reduce + (map (partial * 2) sides)))))


(defn -main
  [& args]
  (println "They should order this many sq ft:" (reduce + (map calc_surface_area (get-data "input.txt" process-func)))))
