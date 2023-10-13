#lang racket

;; SYSC 3101 Winter 2022 Lab 2 - Some Test Cases

;; Exercise 1 (a,b)
(define (sum-numbers numbers)
  (if (empty? numbers) 0
      (+ (first numbers)
         (sum-numbers (rest numbers)))))

(define (average numbers)
  (cond
    [(empty? numbers) 0]
    ;;not returning correctly somehow
    ;;average should just be total/number of elements no?
    ;; couldnt replicate it using quotient. I think quotient does integer division
    [(/ (sum-numbers numbers)(length numbers))]))

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

 ;; Exercise 4
(define (celsius-converter n)
  (/ (* (- n 32)5)9))
 
(define (convertFC temps)
  ;;check if empty list
  (cond
    [(empty? temps) (list)]
    ;;convert first item then pass rest
    [(cons (celsius-converter (first temps))(convertFC (rest temps)))]))

;;exercise 5
(define (eliminate-threshold numbers threshold)
   (cond
     ;;empty list return empty
     [(empty? numbers) (list)]
     ;;threshold <= (not <!!!) entire list return empty list
     [(<= (first numbers) threshold)(cons (first numbers)(eliminate-threshold (rest numbers) threshold))]
     [else (eliminate-threshold(rest numbers) threshold)]))

(display "Testing sum-numbers")
(newline)
(display "Expected: 0, actual: ")
(sum-numbers empty)
(display "Expected: 21, actual: ")
(sum-numbers (list 1 2 3 4 5 6))
(newline)

(display "Testing average")
(newline)
(display "Expected: 3.5, actual: ")
(average (list 1 2 3 4 5 6))
(newline)

(display "Testing occurrences")
(newline)
(display "Expected: 3, actual: ")
(occurrences '(1 3 5 2 7 5 8 9 5) 5)
(display "Expected: 0, actual: ")
(occurrences '(1 3 5 2 7 5 8 9 5) 6)
(display "Expected: 0, actual: ")
(occurrences empty 1)
(newline)

(display "Testing convert")
(newline)
(display "Expected: 0, actual: ")
(convert empty)
(display "Expected: 3, actual: ")
(convert (list 3))
(display "Expected: 543, actual: ")
(convert (list 3 4 5))
(newline)

(display "Testing convertFC")
(newline)
(display "Expected: '(), actual: ")
(convertFC empty)
(display "Expected: '(0 100 37.0), actual: ")
(convertFC (list 32 212 98.6))
(newline)

(display "Testing eliminate-threshold")
(newline)
(display "Expected: '(1 2 3 4 4 3 2 1), actual: ")
(eliminate-threshold (list 1 2 3 4 5 6 5 4 3 2 1 20) 4)
(display "Expected: '(), actual: ")
(eliminate-threshold (list 1 2 3 4 5 6 5 4 3 2 1 20) 0)
(display "Expected: '(1 2 3 4 5 6 5 4 3 2 1 20), actual: ")
(eliminate-threshold (list 1 2 3 4 5 6 5 4 3 2 1 20) 25)
(newline)
