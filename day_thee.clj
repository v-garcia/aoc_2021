
(def input
  (->>
   (slurp "day_three_input.txt")
   clojure.string/split-lines
   (map #(read-string (str "2r" %)))))

(defn bit-at
  [i val]
  (bit-and 1 (bit-shift-right val (- 11 i))))

(defn sum-bits [vals i]
  (->> vals (mapv (partial bit-at i)) (apply +)))

(defn get-rate
  [op]
  (->>
   (range 0 12)
   (map #(if (op (sum-bits input %) (/ (count input) 2)) 0 1))
   (apply str "2r")
   read-string))

(def gamma-rate (get-rate <))
(def epsilon-rate (get-rate >))
(def power-consumption (* gamma-rate epsilon-rate))

(defn get-rate'
  [op]
  (reduce (fn [acc i]
            (let [c              ({false 0 true 1} (op (sum-bits acc i) (/ (count acc) 2)))
                  [a & r :as l]  (filter #(= c (bit-at i %)) acc)]
              (if (empty r) (reduced a) l))) input (range 0 12)))


(def oxigen-rating (get-rate' <=))
(def co2-scrubber-rating (get-rate' >))
(def life-support-rating (* oxigen-rating co2-scrubber-rating))