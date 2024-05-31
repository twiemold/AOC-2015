(ns day-six.core)

(defn switchV2 [grid command interval]
  (let [op (case command
             "turn on" #(inc %)
             "turn off" #(if (> % 1) (dec %) 0)
             "toggle" #(+ 2 %))] 
   (loop [g grid 
          coords (for [x (range (dec (get-in interval [0 0])) (get-in interval [1 0])) 
                       y (range (dec (get-in interval [0 1])) (get-in interval [1 1]))] 
                   (seq [(inc x) (inc y)]))] 
     (if (empty? coords) 
       g 
       (recur (update-in g [(first (first coords)) 
                            (second (first coords))] op) 
              (rest coords))))))

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
      "The total brightness is:" 
             (reduce + (flatten 
                         (loop [g grid
                                i intervals
                                c commands]
                           (if (empty? c)
                             g
                             (recur (switchV2 g (first c) (first i)) (rest i) (rest c)))))))))

(comment
  (map vec (partition 2 (map vec (partition 2 (map parse-long (re-seq #"\d+" (slurp "input.txt") ) ) ) ) ) )
  (re-seq #"turn\soff|turn\son|toggle" (slurp "input.txt") )
  (reduce + (flatten (vec (repeat 10 (vec (repeat 10 0)))) ) )
  (map #(if (> % 1) (dec %) 0) [0 1 2 3])
  (-main)

  )
