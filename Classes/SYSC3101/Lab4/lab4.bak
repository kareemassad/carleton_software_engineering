#lang racket

;; SYSC 3101 A Winter 2022 Lab 4

;; Exercise 1

(define (make-upcounter counter)
  (lambda () 
    (set! counter (+ counter 1))
    counter))


;; Exercise 2 

(define (make-counter counter)
  
  (define (count-up) 
    (set! counter (+ counter 1))
    counter)
  
  (define (count-down)
    (if (> counter 0)
        (begin (set! counter (- counter 1))
               counter)
        "Counter is 0"))

  (define (dispatch cmd)
    (cond ((eq? cmd 'inc) count-up)
          ((eq? cmd 'dec) count-down)
          (else (error "Unknown command:" cmd))))
  
  dispatch)


;; Exercise 3

(define (make-counter-with-let initial-count)

  (let ((counter initial-count))
 
    (define (count-up)
      (set! counter (+ counter 1))
      counter)
 
    (define (count-down)
      (if (> counter 0)
          (begin (set! counter (- counter 1))
                 counter)
          "Counter is 0"))

    (define (dispatch cmd)
      (cond ((eq? cmd 'inc) count-up)
            ((eq? cmd 'dec) count-down)
            (else (error "Unknown command:" cmd))))
 
    dispatch))
