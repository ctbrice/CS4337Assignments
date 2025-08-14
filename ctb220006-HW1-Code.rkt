;Cooper Brice ctb22000064337.007
#lang scheme
(define (pascal_row_sums n) ;3 (pascal_row_sums 3) returns (1 2 4)
  (define (helper row current_sum)
    (if (= row n)
        '()  ;when row equals n, return an empty list
        (cons current_sum (helper (+ row 1) (* current_sum 2)))))  ;increment row and multiply by 2 to get the next sum
 
  (helper 0 1))  ; start with row 0 and initial sum 1

(define (sumodd list) ;4 (sumodd '((2 6) 1 3 ( () 5) 8)) returns '9
  (cond
    ((null? list) 0) ;is list is null, return 0
    ((list? (car list)) (+ (sumodd (car list)) (sumodd (cdr list)))) ;if first_elem is list, return sumodd(first_elem) + sumodd(rest_of_list)
    ((odd? (car list)) (+ (car list) (sumodd (cdr list)))) ;if first_elem is odd, return first_elem + sumodd(rest_of_list)
    (else (sumodd (cdr list))))) ;else, sumodd(rest_of_list)

(define (deleteitem list) ;5 (deleteitem '(a (c d) (b) e)) returns ‘(a (c d) e)
  (cond
    ((null? list) list) ;return list if null
    ((null? (cdr list)) list) ;return list if only 1 elem
    ((null? (cddr list)) list) ;return list if only 2 elems
    (else (cons (car list) (cons (cadr list) (cdddr list)))))) ;returns list with 3rd elem removed

(define (newlist list) ;6 (newlist '( a b c (a b) (c d))) returns ‘( b a (b a) c (d c))
  (cond
    ((null? list) list) ;if list null, return list
    ((not (list? list)) list) ;if list is not list, return list
    ((null? (cdr list)) (cons (newlist (car list)) '())) ;if current elem is last elem in list, run newlist on it
    (else (cons (newlist (cadr list)) (cons (newlist (car list)) (newlist (cddr list))))))) ;newlist 2nd elem, newlist 1st elem, newlist rest of list

(define (last-element list) ;7 (last-element '(a (b) c (d e )) ) returns ‘(d e ).
  (cond
    ((null? (cdr list)) (car list)) ;if no elem after 1st elem, return 1st elem
    (else (last-element (cdr list))))) ;last-element without first elem

 (define (leaves tree) ;8 ( leaves '(((1 2) (3 4 5)) ((1) (3 4) (5))) )  returns  ‘(5 4 3 1 5 4 3 2 1)
     (cond
       ((null? tree) tree) ;if tree is null, return tree
       ((not (list? tree)) (list tree)) ;if tree is not list (is leaf) return tree
    (else (combine (leaves (cdr tree)) (leaves (car tree)))))) ;if tree is not on leaf, combine leaves right side with leaves left side 

(define (combine right left)
  (if (null? right) ; if right  is null, return left
      left 
      (cons (car right) (combine (cdr right) left)))) ; else add the first element of right to left.

(define (EXP-DEPTH list) ;9 (EXP-DEPTH '(A B ((C (D (E) F)) G) H))
  (define (helper current_depth max_depth list)
    (cond
      ((null? list) max_depth) ;if list is empty, return max_depth
      ((not (list? list)) max_depth) ;if list not a list, return max_depth
      ((not (list? (car list))) (helper current_depth max_depth (cdr list))) ;if the first elem is not a list, move to rest of list
      (else ;if first elem is a list, increment current and max depth and resursive call
       (max (helper (+ current_depth 1) (max (+ current_depth 1) max_depth) (car list)) 
            (helper current_depth (max current_depth max_depth) (cdr list)))))) ;recursive call for rest of list, take the higher #
  
    (if (list? list) (helper '1 '1 list)'0)) ;start max & current depth at 1, or return 0 if not a list

(define (subsets list) ;10 (subsets '(a b c))
  (cond
    ((null? list) '(())) ; if empty, return the empty set.
    (else
     (combine (subsets (cdr list)) ; subsets of the list minus first elem.
            (map (lambda (subset) (cons (car list) subset)) (subsets (cdr list))))))) ; add the current element to each subset.