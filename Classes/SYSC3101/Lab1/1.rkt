#lang racket

;; Excercise 1 
(define (sum-coins pennies nickels dimes quarters)
    (+ pennies (* 5 nickels)(* 10 dimes)(* 25 quarters)))

;; Excercise 2
(define (interest balance)
  ;; if balance <=1000, 4% interest
  ;; if balance > 1000 <=5000 , 4.5%
  ;; if balance > 5000, 5%

  ;;NOTE: 5000 doesnt output anything
  (cond
    [(<= balance  1000) (* balance 0.04) ]
    [(and (> balance  1000)(<= balance 5000)) (* balance 0.045) ]
    [(> balance  5000) (* balance 0.05) ]))
    
  
;; Excercise 3
(define (balance accBalance)
  (+ accBalance (interest accBalance)))

;; Excercise 4

(define (variable_Interest balance)
  (cond
    [(<= balance 1000)(* balance 0.04)]
    [(and (> balance  1000)(<= balance 5000)) (+ (* (- balance 1000) 0.045) 40)]
    [(> balance  5000) (+ (* (- balance 5000) 0.05) 220)]))