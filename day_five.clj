
(require
 '[clojure.string :as str])

(def input (->> (slurp "day_five_input.clj") str/split-lines (map (fn [l] (->> (re-seq #"\d+" l) (map #(Integer. %)) (split-at 2))))))

(defn expand-line
  [[[x y] [x' y']]]
  ((comp distinct concat)
   (mapv vector (repeat x) (range (min y y') (inc (max y y'))))
   (mapv vector (range (min x x') (inc (max x x'))) (repeat y))))

(def q1
  (->>
   input
   (filter (fn [[[x y] [x' y']]] (or (= x x') (= y y'))))
   (mapcat expand-line)
   (reduce (fn [acc v] (update acc v (fnil inc 0))) {})
   vals
   (filter (partial < 1))
   count))