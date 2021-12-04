
(require
 '[clojure.string :as str])

(def nb-list (as-> (slurp "day_four_input.txt") % (str/split-lines %) (first %) (str/split % #",") (map #(Integer. %) %)))

(def boards (as-> (slurp "day_four_input.txt") % (str/split-lines %) (drop 1 %) (str/join " " %)
                  (str/trim %) (str/split % #"( )+") (map #(Integer. %) %) (partition 25 %)))

(defn complete-board?
  [xs]
  (not-empty
   (for [a xs b xs c xs d xs e xs
         :when (or
                (and (zero? (mod a 5)) (= [a b c d e] (range a (inc e)))) ; row
                (= [a b c d e] (range a (inc e) 5)))] true))) ; column

(def marked-boards
  (rest
   (reduce
    (fn [marked-boards nb]
      (conj marked-boards
            (mapv (fn [marked-board board]
                    (into marked-board (keep-indexed #(when (= nb %2) %1) board))) (last marked-boards) boards)))
    [(repeat #{})] nb-list)))


(def finished-board
  (->>
   (map vector nb-list marked-boards)
   (some (fn [[nb marked-boards]]
           (some (fn [[marked-board board]]
                   (when (complete-board? marked-board) [nb marked-board board])) (map vector marked-boards boards))))))


(def question-1-result
  (let [[nb marks board] finished-board]
    (->> (keep-indexed #(when-not (marks %1) %2) board)
         (apply +) (* nb))))
