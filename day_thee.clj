
(def input
  (->>
   (slurp "day_three_input.txt")
   clojure.string/split-lines
   (map #(read-string (str "2r" %)))))


(defn sum-bits [i]
  (->>
   input
   (mapv #(->> (bit-shift-right % (- 11 i)) (bit-and 1)))
   (apply +)))

(defn get-rate
  [op]
  (->>
   (range 0 12)
   (map #(if (op (sum-bits %) (/ (count input) 2)) 0 1))
   (apply str "2r")
   read-string))

(def gamma-rate (get-rate <))
(def epsilon-rate (get-rate >))
(def power-consumption (* gamma-rate epsilon-rate))