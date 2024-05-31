(ns day-six.core)

(defn switch [grid command interval]
  (let [op (case command
             "turn on" #(if (= % 0) 1 1)
             "turn off" #(if (= % 1) 0 0)
             "toggle" #(if (= % 1) 0 1))] 
   (loop [g grid 
          coords (for [x (range (dec (get-in interval [0 0])) (get-in interval [1 0])) 
                       y (range (dec (get-in interval [0 1])) (get-in interval [1 1]))] 
                   (seq [(inc x) (inc y)]))] 
     (if (empty? coords) 
       g 
       (recur (update-in g [(first (first coords)) 
                            (second (first coords))] op) 
              (rest coords))))))


(defn -main []
  (let [grid (vec (repeat 1000 (vec (repeat 1000 0))))
        f (slurp "input.txt")
        intervals (map vec (partition 2 (map vec (partition 2 (map parse-long (re-seq #"\d+" f))))))
        commands (re-seq #"turn\soff|turn\son|toggle" f)]
    (println 
      "The number of lights lit is:" 
             (reduce + (flatten 
                         (loop [g grid
                                i intervals
                                c commands]
                           (if (empty? c)
                             g
                             (recur (switch g (first c) (first i)) (rest i) (rest c)))))))))

(comment
  (map vec (partition 2 (map vec (partition 2 (map parse-long (re-seq #"\d+" (slurp "input.txt") ) ) ) ) ) )
  (re-seq #"turn\soff|turn\son|toggle" (slurp "input.txt") )
  (reduce + (flatten (vec (repeat 10 (vec (repeat 10 0)))) ) )
  (-main)

  )
