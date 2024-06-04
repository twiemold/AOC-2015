(ns day-seven.core
  (:require [clojure.string :as str]))

(defn mask [x] bit-and 16rFFFF x)

(defn eval-wire [m k]
  (if-let [signal (parse-long k)] 
    signal 
    (cond 
      ;; values can be ints!
      (str/includes? k "NOT") (bit-and-not 16rFFFF (eval-wire m (get m (second (str/split k #"\s")) (second (str/split k #"\s"))))) 
      (str/includes? k "OR") (mask (bit-or (eval-wire m (get m (first (str/split k #"\s")) (first (str/split k #"\s")))) 
                                           (eval-wire m (get m (nth (str/split k #"\s") 2) (nth (str/split k #"\s") 2))))) 
      (str/includes? k "AND") (mask (bit-and (eval-wire m (get m (first (str/split k #"\s")) (first (str/split k #"\s")))) 
                                             (eval-wire m (get m (nth (str/split k #"\s") 2) (nth (str/split k #"\s") 2)) ))) 
      (str/includes? k "LSHIFT") (mask (bit-shift-left (eval-wire m (get m (first (str/split k #"\s")) (first (str/split k #"\s")))) 
                                                       (parse-long (nth (str/split k #"\s") 2)))) 
      (str/includes? k "RSHIFT") (mask (unsigned-bit-shift-right (eval-wire m (get m (first (str/split k #"\s")) (first (str/split k #"\s")))) 
                                                                 (parse-long (nth (str/split k #"\s") 2)))) 
      :else (eval-wire m (get m k)))))

(def memo-eval-wire (memoize eval-wire))

(defn -main
  [& args]
  (let [wire-map (->> (slurp "input.txt") 
                      (#(str/split % #"->|\n")) 
                      (partition 2) 
                      (map #(hash-map (str/trim (second %)) (str/trim (first %)))) 
                      (apply merge))] 
    (memo-eval-wire wire-map (get wire-map "a"))))


(comment
  (def my-map {"x" "123"
               "h" "NOT x"
               "y" "456"
               "e" "x OR y"
               "d" "x AND y"
               "f" "x LSHIFT 2"
               "g" "y RSHIFT 2" 
               "k" "1 AND y"}) 
  (eval-wire 0 "a") 
  (memo-eval-wire my-map "k"))
