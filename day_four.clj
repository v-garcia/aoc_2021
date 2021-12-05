
(require
 '[clojure.string :as str]
 '[clojure.set :as set])

(def input (-> (slurp "day_four_input.txt")  str/split-lines))
(def nb-list (as-> input % (first %) (str/split % #",") (map #(Integer. %) %)))
(def boards (as-> input % (drop 1 %) (str/join " " %) (str/trim %) (str/split % #"( )+") (map #(Integer. %) %) (partition 25 %)))


(def bingos (mapcat (fn [i] [(set (range (* i 5) (+ (* i 5) 5))) (set (range i (+ i 21) 5))]) (range 0 5)))
(def complete-board? #(some (partial set/superset? %) bingos))

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

;; Question 2

(def unfinished-board
  (->>
   (map vector nb-list marked-boards)
   (reduce (fn [acc [nb marked-boards]]
             (let [finisheds (mapv complete-board? marked-boards)]
               (if (every? true? finisheds)
                 (let [idx-last (.indexOf acc nil)]
                   (reduced [nb (nth marked-boards idx-last) (nth boards idx-last)]))
                 finisheds)))
           (repeat false))))

(def question-2-result
  (let [[nb marks board] unfinished-board]
    (->> (keep-indexed #(when-not (marks %1) %2) board)
         (apply +) (* nb))))
