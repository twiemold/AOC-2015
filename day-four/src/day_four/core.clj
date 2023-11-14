(ns day-four.core
  (:import java.security.MessageDigest
          java.math.BigInteger)
  (:gen-class))

;; source: https://gist.github.com/jizhang/4325757
(defn md5 [^String s]
  (let [algorithm (MessageDigest/getInstance "MD5")
        raw (.digest algorithm (.getBytes s))]
    (format "%032x" (BigInteger. 1 raw))))

;; part one
(defn find-lowest-num [secret-key, num]
  (if (re-find #"^[0]{6}" (md5 (apply str [secret-key (str num)])))
    num
    (recur secret-key (+ num 1))))

;; input: ckczppom
(defn -main
  [& args]
  ;; test
  ;; (println "The number is:" (find-lowest-num "abcdef" 1000))
  ;; part one
  (println "The number is:" (find-lowest-num "ckczppom" 1000)))
