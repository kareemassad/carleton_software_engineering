#lang racket
;; Exercise 1 (a,b)
(define (sum-numbers numbers)
  (if (empty? numbers) 0
      (+ (first numbers)
         (sum-numbers (rest numbers)))))

(define (average numbers)
  (cond
    [(empty? numbers) 0]
    ;;not returning correctly somehow
    ;;average should just be total/number of elements no?
    ;; couldnt replicate it using quotient. I think quotient does integer division
    [(/ (sum-numbers numbers)(length numbers))]))


(display "Testing sum-numbers")
(newline)
(display "Expected: 0, actual: ")
(sum-numbers empty)
(display "Expected: 21, actual: ")
(sum-numbers (list 1 2 3 4 5 6))
(newline)

(display "Testing average")
(newline)
(display "Expected: 3.5, actual: ")
(average (list 1 2 3 4 5 6))
(newline)