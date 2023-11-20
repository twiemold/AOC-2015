(ns day-five.core
  (:gen-class))

(defn count-nice-strings* [data]
  (->> (re-seq #"\w+" data)
       (filter #(re-seq #"^\w*[aeiou]\w*[aeiou]\w*[aeiou]\w*$" %))
       (filter #(if (re-seq #"ab|cd|pq|xy" %) false true))
       (filter #(re-seq #"(.)\1+" %))
       (count)))

(defn -main
  [& args]
  (println (count-nice-strings* (slurp "input.txt"))))
