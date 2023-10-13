/* SYSC 2006 Fall 2019 Lab 10
 *
 * main.c - test harness to exercise the functions in the circular_queue.c module.
 * Version 1.01 - dlb 
 */

#include <assert.h>  // assert
#include <stdlib.h>  // malloc, free
#include <stdbool.h>
#include <stdio.h>   // printf

#include "circular_queue.h"

void print_bool(_Bool value)
{
    if (value) {
        printf("true");
    } else {
        printf("false");
    }
}


/* Tests for Exercise 1. */

void test_enqueue(void)
{
    printf("=== Test 1: Testing enqueue ===\n\n");
    printf("Calling queue_construct to create an empty queue\n");
    queue_t *queue = queue_construct();

    printf("Calling enqueue to enqueue 40\n");
    enqueue(queue, 40);
    printf("Expected queue size: 1\n");
    printf("Actual queue size:   %d\n", queue_size(queue));
    printf("Expected queue: [40]\n");
    printf("Actual queue:   ");
    queue_print(queue);
    printf("\n\n");

    printf("Calling enqueue to enqueue 30\n");
    enqueue(queue, 30);
    printf("Expected queue size: 2\n");
    printf("Actual queue size:   %d\n", queue_size(queue));
    printf("Expected queue: [40, 30]\n");
    printf("Actual queue:   ");
    queue_print(queue);
    printf("\n\n");

    printf("Calling enqueue to enqueue 20\n");
    enqueue(queue, 20);
    printf("Expected queue size: 3\n");
    printf("Actual queue size:   %d\n", queue_size(queue));
    printf("Expected queue: [40, 30, 20]\n");
    printf("Actual queue:   ");
    queue_print(queue);
    printf("\n\n");
}

/* Tests for Exercise 2. */

void test_front(void)
{
    int elem;
    _Bool success;

    printf("=== Test 2: Testing front ===\n\n");
    printf("Calling queue_construct to create an empty queue\n");
    queue_t *queue = queue_construct();

    printf("Calling front\n");
    success = front(queue, &elem);

    printf("Expected return value: false\n");
    printf("Actual return value:   ");
    print_bool(success);
    printf("\n\n");

    printf("Calling enqueue to enqueue 40\n");
    enqueue(queue, 40);
    printf("Queue is: ");
    queue_print(queue);
    printf("\n");

    printf("Calling front\n");
    success = front(queue, &elem);
    printf("Expected return value: true\n");
    printf("Actual return value:   ");
    print_bool(success);
	printf("\n");
    printf("Value retrieved by front - expected value: 40\n");
    printf("Value retrieved by front - actual value:   ");
    printf("%d\n", elem);
    printf("Expected queue size: 1\n");
    printf("Actual queue size:   %d\n", queue_size(queue));
    printf("Expected queue: [40]\n");
    printf("Actual queue:   ");
    queue_print(queue);
    printf("\n\n");

    printf("Calling enqueue to enqueue 30\n");
    enqueue(queue, 30);
    printf("Queue is: ");
    queue_print(queue);
    printf("\n");

    printf("Calling front\n");
    success = front(queue, &elem);
    printf("Expected return value: true\n");
    printf("Actual return value:   ");
    print_bool(success);
    printf("\n");
    printf("Value retrieved by front - expected value: 40\n");
    printf("Value retrieved by front - actual value:   ");
    printf("%d\n", elem);
    printf("Expected queue size: 2\n");
    printf("Actual queue size:   %d\n", queue_size(queue));
    printf("Expected queue: [40, 30]\n");
    printf("Actual queue:   ");
    queue_print(queue);
    printf("\n\n");

    printf("Calling enqueue to enqueue 20\n");
    enqueue(queue, 20);
    printf("Queue is: ");
    queue_print(queue);
    printf("\n");

    printf("Calling front\n");
    success = front(queue, &elem);
    printf("Expected return value: true\n");
    printf("Actual return value:   ");
    print_bool(success);
    printf("\n");
    printf("Value retrieved by front - expected value: 40\n");
    printf("Value retrieved by front - actual value:   ");
    printf("%d\n", elem);
    printf("Expected queue size: 3\n");
    printf("Actual queue size:   %d\n", queue_size(queue));
    printf("Expected queue: [40, 30, 20]\n");
    printf("Actual queue:   ");
    queue_print(queue);
    printf("\n\n");
}

/* Tests for Exercise 3. */
void test_dequeue(void)
{
    int elem;
    _Bool success;

    printf("=== Test 3: Testing dequeue ===\n\n");
    printf("Calling queue_construct to create an empty queue\n");
    queue_t *queue = queue_construct();

    printf("Calling dequeue\n");
    success = dequeue(queue, &elem);
    printf("Expected return value: false\n");
    printf("Actual return value:   ");
    print_bool(success);
    printf("\n");
    printf("Expected queue size: 0\n");
    printf("Actual queue size:   %d\n", queue_size(queue));
    printf("Expected queue: []\n");
    printf("Actual queue:   ");
    queue_print(queue);
    printf("\n\n");

    printf("Calling enqueue to enqueue 40, 30, 20\n");
    enqueue(queue, 40);
    enqueue(queue, 30);
    enqueue(queue, 20);
    printf("Queue is: ");
    queue_print(queue);
    printf("\n");

    printf("Calling dequeue\n");
    success = dequeue(queue, &elem);
    printf("Expected return value: true\n");
    printf("Actual return value:   ");
    print_bool(success);
    printf("\n");
    printf("Value removed by dequeue - expected value: 40\n");
    printf("Value removed by dequeue - actual value:   ");
    printf("%d\n", elem);
    printf("Expected queue size: 2\n");
    printf("Actual queue size:   %d\n", queue_size(queue));
    printf("Expected queue: [30, 20]\n");
    printf("Actual queue:   ");
    queue_print(queue);
    printf("\n\n");

    printf("Calling dequeue\n");
    success = dequeue(queue, &elem);
    printf("Expected return value: true\n");
    printf("Actual return value:   ");
    print_bool(success);
    printf("\n");
    printf("Value removed by dequeue - expected value: 30\n");
    printf("Value removed by dequeue - actual value:   ");
    printf("%d\n", elem);
    printf("Expected queue size: 1\n");
    printf("Actual queue size:   %d\n", queue_size(queue));
    printf("Expected queue: [20]\n");
    printf("Actual queue:   ");
    queue_print(queue);
    printf("\n\n");

    printf("Calling dequeue\n");
    success = dequeue(queue, &elem);
    printf("Expected return value: true\n");
    printf("Actual return value:   ");
    print_bool(success);
    printf("\n");
    printf("Value removed by dequeue - expected value: 20\n");
    printf("Value removed by dequeue - actual value:   ");
    printf("%d\n", elem);
    printf("Expected queue size: 0\n");
    printf("Actual queue size:   %d\n", queue_size(queue));
    printf("Expected queue: []\n");
    printf("Actual queue:   ");
    queue_print(queue);
    printf("\n\n");

    printf("Calling dequeue\n");
    success = dequeue(queue, &elem);
    printf("Expected return value: false\n");
    printf("Actual return value:   ");
    print_bool(success);
    printf("\n");
    printf("Expected queue size: 0\n");
    printf("Actual queue size:   %d\n", queue_size(queue));
    printf("Expected queue: []\n");
    printf("Actual queue:   ");
    queue_print(queue);
    printf("\n\n");
}

int main(void)
{
    printf("Running test harness for SYSC 2006 W19 Lab 10\n\n");

    test_enqueue();
    test_front();
    test_dequeue();
}
