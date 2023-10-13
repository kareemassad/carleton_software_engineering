#lang racket
;; Exercise 2
;; numbers is list, n is int
;; check with TA
(define (occurrences numbers n)
  (cond
    ;;base case
    [(empty? numbers) 0]
    ;;couldnt get this to work using the same logic but using equal? 
    [(= (first numbers) n) (+ 1 (occurrences (rest numbers) n))]
    [else (occurrences (rest numbers) n)]))

(display "Testing occurrences")
(newline)
(display "Expected: 3, actual: ")
(occurrences '(1 3 5 2 7 5 8 9 5) 5)
(display "Expected: 0, actual: ")
(occurrences '(1 3 5 2 7 5 8 9 5) 6)
(display "Expected: 0, actual: ")
(occurrences empty 1)
(newline)