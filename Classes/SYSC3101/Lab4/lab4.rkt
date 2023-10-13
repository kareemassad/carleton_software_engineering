#lang racket

;; SYSC 3101 A Winter 2022 Lab 4

;; Exercise 1
(display "exercise 1 \n")

(define (make-upcounter counter)
  (lambda ()
    (set! counter (+ counter 1))
    counter))

(define counter1 (make-upcounter 0))
(counter1)
(counter1)
(define counter2 (make-upcounter 10))
(counter2)
(counter2)

;; Exercise 2
(display "exercise 2 \n")

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

(define counter3 (make-counter 0 ))
counter3
(counter3 'inc)
(counter3 'inc)
(counter3 'dec)
(counter3 'dec)
(counter3 'dec)
;(counter3 'reset)



;; Exercise 3
(display "exercise 3 \n")
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

(define make-let (make-counter-with-let 0))
(make-let 'dec)

;; Exercise 4
(display "exercise 4 \n")
;; same as make-counter-with-let but using lambdas instead of the dispatch function
(define (make-counter-ex4 initial-count)
  (let ((counter initial-count))
    (define (count-up)
      (set! counter (+ counter 1))
      counter)

    (define (count-down)
      (if (> counter 0)
          (begin (set! counter (- counter 1))
                 counter)
          "Counter is 0"))
    ;; must return a procedure
    (lambda (cmd)
      (cond ((eq? cmd 'inc) count-up)
            ((eq? cmd 'dec) count-down)
            (else (error "Unknown command:" cmd))))))

(define make-let1 (make-counter-ex4 0))
((make-let1 'inc))
((make-let1 'inc))
((make-let1 'inc))
;(define (make-upcounter counter)
;  (lambda ()
;    (set! counter (+ counter 1))
;    counter))

;; Exercise 5
(display "exercise 5 \n")

;; Modify make-counter-ex4 so that counter objects recognize two additional commands: `get and `reset.
;; The `get command returns the count.
;; The `reset command sets the count to 0.

(define (make-counter-ex5 initial-count)
  (let ((counter initial-count))
    (define (count-up)
      (set! counter (+ counter 1))
      counter)

    (define (count-down)
      (if (> counter 0)
          (begin (set! counter (- counter 1))
                 counter)
          "Counter is 0"))
    (define (count-get)
      counter)
    (define (count-reset)
      (set! counter 0)
      counter)

    ;; must return a procedure
    (lambda (cmd)
      (cond ((eq? cmd 'inc) count-up)
            ((eq? cmd 'dec) count-down)
            ((eq? cmd 'get) count-get)
            ((eq? cmd 'reset) count-reset)
            (else (error "Unknown command:" cmd))))))

(define make-let2 (make-counter-ex5 12))
((make-let2 'get))
(make-let2 'reset)
((make-let2 'get))


;; exercise 6
(display "exercise 6 \n")

;; Modify make-counter-ex5 so that it is passed 2 arguments: initial-count and the step count for the 'inc command.

(define (make-counter-ex6 initial-count step-count)
  (let ((counter initial-count))
    (define (count-up)
      (set! counter (+ counter step-count))
      counter)

    (define (count-down)
      (if (> counter 0)
          (begin (set! counter (- counter step-count))
                 counter)
          "Counter is 0"))
    (define (count-get)
      counter)
    (define (count-reset)
      (set! counter 0)
      counter)

    ;; must return a procedure
    (lambda (cmd)
      (cond ((eq? cmd 'inc) count-up)
            ((eq? cmd 'dec) count-down)
            ((eq? cmd 'get) count-get)
            ((eq? cmd 'reset) count-reset)
            (else (error "Unknown command:" cmd))))))

(define make-let3 (make-counter-ex6 10 5))
((make-let3 'get))
((make-let3 'inc))
((make-let3 'inc))
((make-let3 'inc))
((make-let3 'get))
((make-let3 'reset))
((make-let3 'get))

;; exercise 7
(display "exercise 7 \n")

;; Modify make-counter-ex6 so that each counter maintains a the max counter value reached.
;; The counter will return this value when given 'max.
;; It is set to 0 once 'reset is called.

(define (make-counter-ex7 initial-count step-count)
  (let ((counter initial-count)(maximum initial-count))

    (define (count-up)
      (set! counter (+ counter step-count))
      (if (> counter maximum)
          (set! maximum counter)
          counter)
      counter)

    (define (count-down)
      (if (> counter 0)
          (begin (set! counter (- counter step-count))
                 counter)
          "Counter is 0"))
    (define (count-get)
      counter)
    (define (count-reset)
      (set! counter 0)
      (set! maximum 0)
      counter)

    (define (count-max)
      maximum)
    ;; must return a procedure
    (lambda (cmd)
      (cond ((eq? cmd 'inc) count-up)
            ((eq? cmd 'dec) count-down)
            ((eq? cmd 'get) count-get)
            ((eq? cmd 'reset) count-reset)
            ((eq? cmd 'max) count-max)
            (else (error "Unknown command:" cmd))))))

(define make-let4 (make-counter-ex7 0 2))
((make-let4 'inc))
((make-let4 'inc))
((make-let4 'max))
((make-let4 'inc))
((make-let4 'max))
((make-let4 'dec))
((make-let4 'dec))
((make-let4 'max))
((make-let4 'reset))
((make-let4 'max))