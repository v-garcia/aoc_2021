
(require
 '[clojure.string :as str])

(def input (->> (slurp "day_five_input.clj") str/split-lines (map (fn [l] (->> (re-seq #"\d+" l) (map #(Integer. %)) (split-at 2))))))

(defn horizontal? [[[x] [x']]] (= x x'))
(defn vertical? [[[_ y] [_ y']]] (= y y'))
(defn diagonal? [[[x y] [x' y']]] (= (Math/abs (- x x')) (Math/abs (- y y'))))

(defn range'
  [l]
  (let [tr    (if (pos? (apply - l)) reverse identity)
        [x y] (tr l)]  (tr (range x (inc y)))))

(defn expand-line
  [[[x y] [x' y'] :as l]]
  (cond
    (horizontal? l) (mapv vector (repeat x) (range' [y y']))
    (vertical? l)   (mapv vector (range' [x x']) (repeat y))
    (diagonal? l)   (mapv vector (range' [x x']) (range' [y y']))))

(def q1
  (->>
   input
   (filter (some-fn horizontal? vertical?))
   (mapcat expand-line)
   frequencies
   vals
   (filter (partial < 1))
   count))

;; Question 2

(def q2
  (->>
   input
   (filter (some-fn horizontal? vertical? diagonal?))
   (mapcat expand-line)
   frequencies
   vals
   (filter (partial < 1))
   count))