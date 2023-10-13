#lang racket


;; Author: Kareem El Assad
;; Date: 2022/02/18
;; Student ID: 101107739

;; Question 1

; (2.5 marks) Define a recursive procedure count-multiples that takes two arguments, a list of
; integers and an integer n, n ≥ 1. The procedure counts the number of elements in the list that are
; a multiple of n, by means of a recursive process.
; Example:
; (count-multiples '(1 2 3 4 5 6) 1) ; returns 6
; (count-multiples '(1 2 3 4 5 6) 2) ; returns 3
; (count-multiples '(1 2 3 4 5 6) 3) ; returns 2

(define (count-multiples lst n)
  (if (null? lst) 0
      (if (= 0 (modulo (first lst) n))
          (+ 1 (count-multiples (rest lst) n))
          (count-multiples (rest lst) n))))

(display "Question 1:")
(newline)
(count-multiples '(1 2 3 4 5 6) 1)
(count-multiples '(1 2 3 4 5 6) 2)
(count-multiples '(1 2 3 4 5 6) 3)
(count-multiples '(1 2 3 4 5 6) 7)

; Question 2:
; (1.5 marks) Rewrite your solution to part (a) as procedure that generates an iterative process.
; Name this procedure count-multiples-iter

;; not sure what is meant by "iterative process"
;; TA recommended I use a count function to count the number of multiples
;; I think this is the same as the recursive solution

(define (count-helper lst x count)
  (if (null? lst) count
      (if (= 0 (modulo (first lst) x))
          (count-helper (rest lst) x (+ 1 count))
          (count-helper (rest lst) x count))))

(define (count-multiples-iter lst x)
  (count-helper lst x 0))

(display "Question 2:")
(newline)
(count-multiples-iter '(1 2 3 4 5 6) 1)
(count-multiples-iter '(1 2 3 4 5 6) 2)
(count-multiples-iter '(1 2 3 4 5 6) 3)
(count-multiples-iter '(1 2 3 4 5 6) 7)

; Question 3:
; (6 marks) Define a procedure deep-list-remove that takes two inputs: a test procedure and a
; List. As output, it produces a List that is a copy of the input List with all of the elements for
; which the test procedure evaluates to true removed. Note the input list could be deep list.
; Example: (deep-list-remove (lambda (x) (= x 0)) (list 0 1 2 3)) returns (1 2 3)
; (deep-list-remove (lambda (x) (< x 4)) '(7 2 (3 4 (5 6)))) ;returns '(7 ( 4 (5 6)))


;; helper function to remove curr from list
(define (remove-helper list curr)
  (if (null? list) '()
      (if (equal? curr (first list))
          (remove-helper (rest list) curr)
          (cons (first list) (remove-helper (rest list) curr)))))

;;(remove-helper '(0 1 2 3) 0)

;;use the helper function to remove all elements from list that satisfy the test
(define (deep-list-remove test list)
  (if (null? list) '()
      (remove-helper list (first list))))


(display "Question 3:")
(newline)

(deep-list-remove (lambda (x) (= x 0)) (list 0 1 2 3)) ;returns (1 2 3)