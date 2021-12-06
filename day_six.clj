
(require
 '[clojure.string :as str])

(def input (as-> (slurp "day_six_input.txt") % (str/split % #",") (map #(Integer. %) %)))

(def q1 (count (reduce
                (fn [acc _]
                  (mapcat
                   (fn [fish] (if (zero? fish) [6 8] [(dec fish)]))
                   acc))
                input (range 80))))

(def q2 (->>
         (reduce
          (fn [acc _]
            (let [zeros (get acc 0 0)]
              (into {8 zeros
                     6 (+ zeros (get acc 7 0))}
                    (mapv (fn [[idx v]] [(dec idx) v]) (dissoc acc 0 7)))))
          (frequencies input)
          (range 256)) vals (apply +)))