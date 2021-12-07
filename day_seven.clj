
(require
 '[clojure.string :as str]
 '[clojure.set :as set])

(def input (as-> (slurp "day_seven_input.txt") % (str/trim %) (str/split % #",") (mapv #(Integer. %) %)))

()

(defn get-pos->fuel
  [compare-fn]
  (->>
   (reduce
    (fn [acc b]
      (assoc acc b (->> input (map (partial compare-fn b)) (apply +))))
    {} (range 0 1001))))

;; Q1

(def pos->fuel (get-pos->fuel #(Math/abs (- %1 %2))))
(def min-fuel (apply min (vals pos->fuel)))

;; Q2