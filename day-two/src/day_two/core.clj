(ns day-two.core
  (:require
    [clojure.string :as str])
  (:gen-class))


(defn calc_surface_area [[l w h]] 
  (let [sides [(* l w) (* w h) (* h l)]]
        (+ (apply min sides) (reduce + (map (partial * 2) sides)))))


(defn -main
  [& args]
  (println "They should order this many sq ft:" (reduce + (doall (map calc_surface_area (partition 3 (map read-string (str/split (slurp "input.txt") #"x|\n"))))))))
