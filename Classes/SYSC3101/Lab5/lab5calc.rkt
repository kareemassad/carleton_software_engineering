#lang racket

;; SYSC 3101 Winter 2022 Lab 5

;; Calculator language interpreter

; The read-eval-print loop:

(define (calc)
  (display "calc: ")
  (flush-output)
  (print (calc-eval (read)))
  (newline)
  (calc))

; Evaluate an expression:

(define (calc-eval exp)
  (cond ((number? exp) exp)
        ((list? exp) (calc-apply (car exp) (map calc-eval (cdr exp))))
        (else (error "Calc: bad expression: " exp))))

; Apply a function to arguments:

(define (calc-apply fn args)
  (cond ((eq? fn '+) (foldr + 0 args))
        ((eq? fn '-) (cond ((null? args) (error "Calc: no args to -"))
                           ((= (length args) 1) (- (car args)))
                           (else (- (car args) (foldr + 0 (cdr args))))))
        ((eq? fn '*) (foldr * 1 args))
        ((eq? fn '/) (cond ((null? args) (error "Calc: no args to /"))
                           ((= (length args) 1) (/ (car args)))
                           (else (/ (car args) (foldr * 1 (cdr args))))))
        ;; Exercise 1
        ((eq? fn 'sqrt) (cond ((null? args) (error "Calc: sqrt requires exactly one arg"))
                              ((= (length args) 1) (sqrt (first args)))
                              (else (error "Calc: too many args to sqrt"))))
        ;; Exercise 2
        ;add power function for (**)
        ((eq? fn '**) (cond ((null? args) (error "Calc: ** requires exactly two args"))
                            ((= (length args) 2) (expt (first args) (first (rest args))))
                            (else (error "Calc: too many args to **"))))
        ;; Exercise 3
        ;; add min function that accepts 1 or more
        ((eq? fn 'min) (cond ((null? args) (error "Calc: min requires 1 or more args"))
                             (else (foldr min (first args) (rest args)))))


        (else (error "Calc: bad operator:" fn))))

;; Exercise 1
;; Exercise 2
;; Exercise 3
