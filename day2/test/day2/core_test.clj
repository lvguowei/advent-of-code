(ns day2.core-test
  (:require [clojure.test :refer :all]
            [day2.core :refer :all]))

(deftest follow-test
  (testing "Empty instruction should not move"
    (is (= [1 1] (follow [1 1] ""))))
  (testing "Single move"
    (testing "Move left"
      (is (= [1 0] (follow [1 1] "L"))))
    (testing "Move right"
      (is (= [1 2] (follow [1 1] "R"))))
    (testing "Move up"
      (is (= [0 1] (follow [1 1] "U"))))
    (testing "Move down"
      (is (= [2 1] (follow [1 1] "D"))))
    (testing "Move left cannot"
      (is (= [0 0] (follow [0 0] "L"))))
    (testing "Move right cannot"
      (is (= [0 2] (follow [0 2] "R"))))
    (testing "Move up cannot"
      (is (= [0 0] (follow [0 0] "U"))))
    (testing "Move down cannot"
      (is (= [2 0] (follow [2 0] "D")))))
  (testing "Multi move"
    (is (= [0 0] (follow [1 1] "UL")))
    (is (= [0 0] (follow [1 1] "ULLLLL")))))
