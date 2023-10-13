#lang racket
 ;; Exercise 4
(define (celsius-converter n)
  (/ (* (- n 32)5)9))
 
(define (convertFC temps)
  ;;check if empty list
  (cond
    [(empty? temps) (list)]
    ;;convert first item then pass rest
    [(cons (celsius-converter (first temps))(convertFC (rest temps)))]))

(display "Testing convertFC")
(newline)
(display "Expected: '(), actual: ")
(convertFC empty)
(display "Expected: '(0 100 37.0), actual: ")
(convertFC (list 32 212 98.6))
(newline)