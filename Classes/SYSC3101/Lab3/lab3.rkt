#lang racket

;; Exercise 1

(define (increment x) (+ 1 (- x 1)))
(define (incrementDouble x) (* x 2))
(define (incrementRationals x) (/ 1 (+ 1 x)))

(define (build-naturals n)
  (build-list n increment))

(define (build-rationals n)
  (build-list n incrementRationals))

(define (build-evens n)
  (build-list n incrementDouble))

(display "Exercise 1:")
(newline)
(build-naturals 5)
(build-rationals 5)
(build-evens 5)

;; Exercise 2
(define (cubic a b c)
  (lambda (x) (+ (expt x 3) (* a(expt x 2)) (* b x) c)))

(display "Exercise 2:")
(newline)
((cubic 1 2 3) 4)

;; Exercise 3
(define (twice x)
  (lambda (y) (x (x y))))

(display "Exercise 3:")
(newline)

;;testing clauses
(define (square x)(* x x))
(define (inc x)(+ x 1))

((twice square) 5)
((twice inc) 5)