(ns day1.core
  (:gen-class))

(def steps "L4, L1, R4, R1, R1, L3, R5, L5, L2, L3, R2, R1, L4, R5, R4, L2, R1, R3, L5, R1, L3, L2, R5, L4, L5, R1, R2, L1, R5, L3, R2, R2, L1, R5, R2, L1, L1, R2, L1, R1, L2, L2, R4, R3, R2, L3, L188, L3, R2, R54, R1, R1, L2, L4, L3, L2, R3, L1, L1, R3, R5, L1, R5, L1, L1, R2, R4, R4, L5, L4, L1, R2, R4, R5, L2, L3, R5, L5, R1, R5, L2, R4, L2, L1, R4, R3, R4, L4, R3, L4, R78, R2, L3, R188, R2, R3, L2, R2, R3, R1, R5, R1, L1, L1, R4, R2, R1, R5, L1, R4, L4, R2, R5, L2, L5, R4, L3, L2, R1, R1, L5, L4, R1, L5, L1, L5, L1, L4, L3, L5, R4, R5, R2, L5, R5, R5, R4, R2, L1, L2, R3, R5, R5, R5, L2, L1, R4, R3, R1, L4, L2, L3, R2, L3, L5, L2, L2, L1, L2, R5, L2, L2, L3, L1, R1, L4, R2, L4, R3, R5, R3, R4, R1, R5, L3, L5, L5, L3, L2, L1, R3, L4, R3, R2, L1, R3, R1, L2, R4, L3, L3, L3, L1, L2")

(defn parse-steps [s]
  (map (fn [step]
         [(first step) (read-string (apply str (rest step)))])
       (clojure.string/split steps #", ")))

(def directions [[0, 1] [1, 0] [0, -1] [-1, 0]])

(defn calc-new-facing [facing turn]
  (mod (+ facing (if (= \L turn) -1 1)) 4))

(defn calc-new-position [position facing step-count]
  (mapv (fn [x delta]
          (+ x (* step-count delta)))
        position
        (directions facing)))

(defn calc-path [start end]
  (if (= (first start) (first end))
    (for [y (range (second start) (second end) (if (< (second start) (second end)) 1 -1))]
      [(first start) y])
    (for [x (range (first start) (first end) (if (< (first start) (first end)) 1 -1))]
      [x (second start)])))

(defn stepper [steps]
  (loop [position [0 0] facing 0 remaining-steps steps path []]
    (if (empty? remaining-steps)
      path
      (let [step (first remaining-steps)
            new-facing (calc-new-facing facing (first step))
            new-position (calc-new-position position new-facing (second step))]
        (recur new-position
               new-facing
               (rest remaining-steps)
               (concat path (calc-path position new-position)))))))

(defn first-revisit [positions]
  (loop [visited #{}
         ps positions]
    (if (empty? ps)
      nil
      (if (visited (first ps))
        (first ps)
        (recur (conj visited (first ps)) (rest ps))))))
