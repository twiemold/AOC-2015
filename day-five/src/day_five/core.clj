(ns day-five.core
  (:gen-class))

(defn count-nice-strings* [data]
  (->> (re-seq #"\w+" data)
       (filter #(re-seq #"^\w*[aeiou]\w*[aeiou]\w*[aeiou]\w*$" %))
       (filter #(if (re-seq #"ab|cd|pq|xy" %) false true))
       (filter #(re-seq #"(.)\1+" %))
       (count)))

(defn count-nice-strings-v2* [data]
  (->> (re-seq #"\w+" data)
       (filter #(re-seq #"(.).\1+" %))
       (filter #(re-seq #"(.{2})\w*\1+" %))
       (count)))

(defn -main
  [& args]
  (println (count-nice-strings-v2* (slurp "input.txt"))))
