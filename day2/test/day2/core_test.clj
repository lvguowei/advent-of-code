(ns day2.core-test
  (:require [clojure.test :refer :all]
            [day2.core :refer :all]))

(deftest follow-test
  (testing "Empty instruction should not move"
    (is (= 5 (follow 5 ""))))
  (testing "Single move"
    (testing "Move left"
      (is (= 4 (follow 5 "L"))))
    (testing "Move right"
      (is (= 6 (follow 5 "R"))))
    (testing "Move up"
      (is (= 2 (follow 5 "U"))))
    (testing "Move down"
      (is (= 8 (follow 5 "D"))))
    (testing "Move left cannot"
      (is (= 0 (follow 0 "L"))))
    (testing "Move right cannot"
      (is (= 3 (follow 3 "R"))))
    (testing "Move up cannot"
      (is (= 0 (follow 0 "U"))))
    (testing "Move down cannot"
      (is (= 7 (follow 7 "D")))))
  (testing "Multi move"
    (is (= 1 (follow 5 "UL")))
    (is (= 1 (follow 5 "ULLLLL")))))
