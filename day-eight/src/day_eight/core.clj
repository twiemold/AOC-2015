(ns day-eight.core
  (:require [clojure.string :as str]))

(defn string-count [x]
  (case x
    "\\\\" 1
    "\\\"" 1
    "\\x" 3))

(defn code-string-count [x] 
  (let [code-count (count x)]
    (seq [code-count 
          (- code-count (+ 
                          (reduce + (map string-count (re-seq #"\\\"|\\\\|\\x" x))) 
                          2))])))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (->> (line-seq (clojure.java.io/reader "input.txt"))
       (map code-string-count)
       (map #(reduce - %))
       (reduce +)))

(comment 
  ;; 1366 is too high
  ;; 1219 is too low
  (code-string-count "\"\\x27\\x28\"")
  (code-string-count "\"\\\\abc\\\"\\x27\"")
  (count (re-seq #"\"|\\\\" "\"aaa\"aaa\""))
  (reduce - (seq [1 2]))
  (map partial - (seq [ (seq [1 2]) (seq [1 2])] ))
  (char (read-string "0x27") )
  (count (re-seq #"\\x" "\"\\x27\"") )
  (filter number? "word")
  (set/intersection #{"x"} #{\\})
  (special-char? "x")
  (count (filter special-char? "abc\\") )
  (code-string-count "abc")
  (into #{} (str/split "abc" #"") ))
