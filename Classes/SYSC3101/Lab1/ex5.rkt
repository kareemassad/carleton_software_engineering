#lang racket
;;exercise 5
(define (eliminate-threshold numbers threshold)
   (cond
     ;;empty list return empty
     [(empty? numbers) (list)]
     ;;threshold <= (not <!!!) entire list return empty list
     [(<= (first numbers) threshold)(cons (first numbers)(eliminate-threshold (rest numbers) threshold))]
     [else (eliminate-threshold(rest numbers) threshold)]))

(display "Testing eliminate-threshold")
(newline)
(display "Expected: '(1 2 3 4 4 3 2 1), actual: ")
(eliminate-threshold (list 1 2 3 4 5 6 5 4 3 2 1 20) 4)
(display "Expected: '(), actual: ")
(eliminate-threshold (list 1 2 3 4 5 6 5 4 3 2 1 20) 0)
(display "Expected: '(1 2 3 4 5 6 5 4 3 2 1 20), actual: ")
(eliminate-threshold (list 1 2 3 4 5 6 5 4 3 2 1 20) 25)
(newline)