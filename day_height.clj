
(require
 '[clojure.string :as str])

(def input (->> (slurp "day_height_input.txt") str/split-lines (mapv #(->> (str/split % #"( \| | )") (mapv set) (split-at 10)))))

(def all-segments #{\a \b \c \d \e \f \g})

(def digit->segment [#{\a \b \c \e \f \g}
                     #{\c \f}
                     #{\a \c \d \e \g}
                     #{\a \c \d \f \g}
                     #{\b \c \d \f}
                     #{\a \b \d \f \g}
                     #{\a \b \d \e \f \g}
                     #{\a \c \f}
                     all-segments
                     #{\a \c \d \f \g}])

(def unique-segments-nb (->> (select-keys digit->segment [1 4 7 8]) vals (mapv count)))
(def q1 (as-> input % (mapcat second %) (mapv count %) (frequencies %) (select-keys % unique-segments-nb) (vals %) (apply + %)))