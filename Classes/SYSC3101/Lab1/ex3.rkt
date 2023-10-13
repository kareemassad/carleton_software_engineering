#lang racket
;;exercise 3

;; Exercise 3
(define (convert decimals)
  (cond
    ;; base case if empty 
    [(empty? decimals)0]
    ;; given 3 4 5,
    ;; > 3
    ;; > 43
    ;; > 543
    ;; essentially we want 3 + 4(10) + 5(10)(10)
    ;; so recursion needs to be multiplied by 10 each time
    [else (+ (first decimals)(* 10 (convert(rest decimals))))]))


(display "Testing convert")
(newline)
(display "Expected: 0, actual: ")
(convert empty)
(display "Expected: 3, actual: ")
(convert (list 3))
(display "Expected: 543, actual: ")
(convert (list 3 4 5))
(newline)