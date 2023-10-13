/* SYSC 2006 Fall 2019 Lab 9 Test Harness */

/* Test harness to exercise the functions in the linked_list.c module.
   Version 1.06 - dlb 
 */

#include <assert.h>  // assert
#include <stdlib.h>  // malloc, free
#include <stdbool.h>
#include <stdio.h>   // printf

#include "linked_list.h"

/* Returns a pointer to the head of the new linked list:
   1 -> 1 -> 2 -> 3 -> 3 -> 4 -> 5 -> 5 -> 5
 */
node_t *setup1(void)
{
    node_t *head = NULL;  // start with an empty list
    head = push(head, 5);
    head = push(head, 5);
    head = push(head, 5);
    head = push(head, 4);
    head = push(head, 3);
    head = push(head, 3);
    head = push(head, 2);
    head = push(head, 1);
    head = push(head, 1);
    return head;
}

/* Returns a pointer to the head of the new linked list:
   5 -> 5 -> 5 -> 4 -> 3 -> 3 -> 2 -> 1 -> 1
 */
node_t *setup2(void)
{
    node_t *head = NULL;  // start with an empty list
    head = push(head, 1);
    head = push(head, 1);
    head = push(head, 2);
    head = push(head, 3);
    head = push(head, 3);
    head = push(head, 4);
    head = push(head, 5);
    head = push(head, 5);
    head = push(head, 5);
    return head;
}

/* Returns a pointer to the head of the new linked list:
   1 -> 2 -> 3 -> 4 -> 5 -> 4 -> 3 -> 2 -> 1
 */
node_t *setup3(void)
{
    node_t *head = NULL;  // start with an empty list
    head = push(head, 1);
    head = push(head, 2);
    head = push(head, 3);
    head = push(head, 4);
    head = push(head, 5);
    head = push(head, 4);
    head = push(head, 3);
    head = push(head, 2);
    head = push(head, 1);
    return head;
}

/* Free all the nodes in the linked list pointed to by head.
 */
void teardown(node_t *head)
{
    node_t *node_to_delete;

    while (head != NULL) {
        node_to_delete = head;
        head = head->next;
        free(node_to_delete);
    }
}


/* Tests for Exercise 1. */
void test_count(void)
{
    printf("=== Exercise 1: Testing count ===\n\n");

    int occurrences;
    node_t *empty = NULL; // Empty list

    printf("Calling count with list: ");
    print_list(empty);
    printf("\nCounting 1's.\n");
    occurrences = count(empty, 1);
    printf("Expected result: 0\n");
    printf("Actual result:   %d\n\n", occurrences);

    printf("Calling count with list: ");
    print_list(empty);
    printf("\nCounting 7's.\n");
    occurrences = count(empty, 7);
    printf("Expected result: 0\n");
    printf("Actual result:   %d\n\n", occurrences);

    node_t *head = setup1();

    printf("Calling count with list: ");
    print_list(head);
    printf("\nCounting 1's.\n");
    occurrences = count(head, 1);
    printf("Expected result: 2\n");
    printf("Actual result:   %d\n\n", occurrences);

    printf("Calling count with list: ");
    print_list(head);
    printf("\nCounting 2's.\n");
    occurrences = count(head, 2);
    printf("Expected result: 1\n");
    printf("Actual result:   %d\n\n", occurrences);

    printf("Calling count with list: ");
    print_list(head);
    printf("\nCounting 3's.\n");
    occurrences = count(head, 3);
    printf("Expected result: 2\n");
    printf("Actual result:   %d\n\n", occurrences);

    printf("Calling count with list: ");
    print_list(head);
    printf("\nCounting 4's.\n");
    occurrences = count(head, 4);
    printf("Expected result: 1\n");
    printf("Actual result:   %d\n\n", occurrences);

    printf("Calling count with list: ");
    print_list(head);
    printf("\nCounting 5's.\n");
    occurrences = count(head, 5);
    printf("Expected result: 3\n");
    printf("Actual result:   %d\n\n", occurrences);

    printf("Calling count with list: ");
    print_list(head);
    printf("\nCounting 7's.\n");
    occurrences = count(head, 7);
    printf("Expected result: 0\n");
    printf("Actual result:   %d\n\n", occurrences);

    teardown(head);
}

/* Tests for Exercise 2. */
void test_max(void)
{
    printf("=== Exercise 2: Testing max ===\n\n");
    int largest;

    /* We can't test the empty-list case, because it should cause max to
     * terminate via assert.
     */

    node_t *head = setup3();
    printf("Calling max with list: ");
    print_list(head);
    largest = max(head);
    printf("\n");
    printf("Expected result: 5\n");
    printf("Actual result:   %d\n\n", largest);
    teardown(head);

    head = setup2();
    printf("Calling max with list: ");
    print_list(head);
    largest = max(head);
    printf("\n");
    printf("Expected result: 5\n");
    printf("Actual result:   %d\n\n", largest);
    teardown(head);

    head = setup3();
    printf("Calling max with list: ");
    print_list(head);
    largest = max(head);
    printf("\n");
    printf("Expected result: 5\n");
    printf("Actual result:   %d\n\n", largest);
    teardown(head);
}

/* Tests for Exercise 3. */
void test_fetch(void)
{
    printf("=== Exercise 3: Testing fetch ===\n\n");
    int fetched;

    /* We can't test the empty-list or invaid index cases, because they
     * should cause fetch to terminate via assert.
     */

    node_t *head = setup3();
    printf("Calling fetch with list: ");
    print_list(head);
    printf("\nFetching data @ index 0.\n");
    fetched = fetch(head, 0);
    printf("Expected result: 1\n");
    printf("Actual result:   %d\n\n", fetched);
    teardown(head);

    head = setup3();
    printf("Calling fetch with list: ");
    print_list(head);
    printf("\nFetching data @ index 1.\n");
    fetched = fetch(head, 1);
    printf("Expected result: 2\n");
    printf("Actual result:   %d\n\n", fetched);
    teardown(head);

    head = setup3();
    printf("Calling fetch with list: ");
    print_list(head);
    printf("\nFetching data @ index 7.\n");
    fetched = fetch(head, 7);
    printf("Expected result: 2\n");
    printf("Actual result:   %d\n\n", fetched);
    teardown(head);

    head = setup3();
    printf("Calling fetch with list: ");
    print_list(head);
    printf("\nFetching data @ index 8.\n");
    fetched = fetch(head, 8);
    printf("Expected result: 1\n");
    printf("Actual result:   %d\n\n", fetched);
    teardown(head);
}

/* Tests for Exercise 4. */
void test_index(void)
{
    printf("=== Exercise 4: Testing index ===\n\n");

    int posn;
    node_t *empty = NULL; // Empty list

    printf("Calling index with list: ");
    print_list(empty);
    printf("\nSearching for 1.\n");
    posn = index(empty, 1);
    printf("Expected result: -1\n");
    printf("Actual result:   %d\n\n", posn);

    node_t *head = setup1();

    printf("Calling index with list: ");
    print_list(head);
    printf("\nSearching for 1.\n");
    posn = index(head, 1);
    printf("Expected result: 0\n");
    printf("Actual result:   %d\n\n", posn);

    printf("Calling index with list: ");
    print_list(head);
    printf("\nSearching for 2.\n");
    posn = index(head, 2);
    printf("Expected result: 2\n");
    printf("Actual result:   %d\n\n", posn);

    printf("Calling index with list: ");
    print_list(head);
    printf("\nSearching for 3.\n");
    posn = index(head, 3);
    printf("Expected result: 3\n");
    printf("Actual result:   %d\n\n", posn);

    printf("Calling index with list: ");
    print_list(head);
    printf("\nSearching for 4.\n");
    posn = index(head, 4);
    printf("Expected result: 5\n");
    printf("Actual result:   %d\n\n", posn);

    printf("Calling index with list: ");
    print_list(head);
    printf("\nSearching for 5.\n");
    posn = index(head, 5);
    printf("Expected result: 6\n");
    printf("Actual result:   %d\n\n", posn);

    printf("Calling index with list: ");
    print_list(head);
    printf("\nSearching for 7.\n");
    posn = index(head, 7);
    printf("Expected result: -1\n");
    printf("Actual result:   %d\n\n", posn);

    teardown(head);
}


/* Tests for Exercise 5. */
void test_extend(void)
{
    printf("=== Exercise 5: Testing extend ===\n\n");

    /* Build 1 -> 2 -> 3 -> 4 */
    node_t *first = NULL;
    first = push(first, 4);
    first = push(first, 3);
    first = push(first, 2);
    first = push(first, 1);

    node_t* second = NULL;

    printf("Calling extend with list: ");
    print_list(first);
    printf("; other list: ");
    print_list(second);
    printf("\n");
    extend(first, second);
    printf("Expected list after extend: 1 -> 2 -> 3 -> 4\n");
    printf("Actual list after extend:   ");
    print_list(first);
    printf("\n\n");

    teardown(first);

    /* Build 1 -> 2 -> 3 -> 4 */
    first = NULL;
    first = push(first, 4);
    first = push(first, 3);
    first = push(first, 2);
    first = push(first, 1);

    /* Build 5 -> 6 -> 7 -> 8 */
    second = push(second, 8);
    second = push(second, 7);
    second = push(second, 6);
    second = push(second, 5);

    printf("Calling extend with list: ");
    print_list(first);
    printf("; other list: ");
    print_list(second);
    printf("\n");
    extend(first, second);
    printf("Expected list after extend: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8\n");
    printf("Actual list after extend:   ");
    print_list(first);
    printf("\n\n");

    teardown(first);
    teardown(second);
}


int main(void)
{
    printf("Running test harness for SYSC 2006 W19 Lab 9\n\n");

    test_count();
    test_max();
    test_fetch();
    test_index();
    test_extend();
}
