/* main.c - SYSC 2006 Winter 2019 Lab 7 Test Harness 
 *
 * Do not modify any code in this file.
 *
 * Tests last revised March 1, 2019.
 */

#include <stdlib.h>  // exit, malloc, free
#include <stdio.h>   // printf
#include "sput.h"

#include "array_list.h"

/* By default, Pelles C generates "warning #2154: Unreachable code"
   and "warning #2130: Result of comparison is constant" when the 
   macros in sput.h are used. The following pragma directive disables the
   generation of these warnings.
 */
#pragma warn(disable: 2130 2154)

static _Bool compare_arrays(int arr1[], int arr2[], int n)
{
    for (int i = 0; i < n; i = i + 1) {
        if (arr1[i] != arr2[i]) {
            return false;
        }
    }
    return true;
}

static void _print_bool(_Bool b)
{
	if (b) {
		printf("true");
	} else {
		printf("false");
    }
}

static void test_list_construct(void)
{
    #define CAPACITY 10

    list_t *list = list_construct(CAPACITY);
    sput_fail_unless(list != NULL && 
                     list->capacity == CAPACITY &&
                     list->size == 0 &&
                     list->elems != NULL,
                     "list_t *list = list_construct(10)");

    printf("Expected result: list = a non-NULL pointer, "
           "list->elems = a non-NULL pointer, "
           "list->capacity = %d, list->size = 0\n", CAPACITY);
    printf("Actual result: ");
    printf("list = %p ", list);
    if (list == NULL) {  	
        printf("(NULL pointer)\n"); 
    } else {
        printf("(non-NULL pointer)\n");
        printf("list->elems = %p ", list->elems);
        if (list->elems == NULL) {
            printf("(NULL pointer)\n");
        } else {
            printf("(non-NULL pointer)\n");
        }
        printf("list->capacity = %d, list->size = %d\n", list->capacity, list->size);
    }

    /* Don't free heap memory if the pointers into the heap are messed up. */
    if (list != NULL && list->elems != NULL) {
        list_destroy(list);
    }
}

static void test_list_append(void)
{
    #define CAPACITY 10

    _Bool appended;

    list_t ref_lst;
    int backing1[CAPACITY] = {1};      //{1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
    ref_lst.elems = backing1;
    ref_lst.capacity = 10;
    ref_lst.size = 1;

    /* Append 1 to an empty list. */

    list_t *list = list_construct(CAPACITY);
    appended = list_append(list, 1);
    sput_fail_unless(appended == true &&
                     list->capacity == CAPACITY &&
                     list->size == ref_lst.size &&
                     compare_arrays(list->elems, ref_lst.elems, 1),
                     "list = list_construct(10); "
                     "appended = list_append(list, 1);");
	printf("Expected: true, actual: ");
	_print_bool(appended);
	printf("\n");
    printf("Expected list: ");
    list_print(&ref_lst);
    printf("Actual list: ");
    list_print(list);
    printf("\n");

    /* Run the remaining tests only if the previous one passed. */
    if (__sput.suite.nok != 0) {
        return;
    }

    /* Append 3 to list [1] */

    int backing2[CAPACITY] = {1, 3};
    ref_lst.elems = backing2;
    ref_lst.size = 2;
    appended = list_append(list, 3);
    sput_fail_unless(appended == true &&
                     list->capacity == CAPACITY &&
                     list->size == 2  &&
                     compare_arrays(list->elems, ref_lst.elems, 2),
                     "appended = list_append(list, 3);");
	printf("Expected: true, actual: ");
	_print_bool(appended);
	printf("\n");
    printf("Expected list: ");
    list_print(&ref_lst);
    printf("Actual list: ");
    list_print(list);
    printf("\n");

    list_destroy(list);

    if (__sput.suite.nok != 0) {
        return;
    }

    /* Append 19 to list [1, 3, 5, ..., 17] size == 9, capacity == 10 */

	printf("Creating an empty list: list = list_construct(10);\n");
	list = list_construct(CAPACITY);
	printf("Initializing list to [1 3 5 7 9 11 13 15 17]\n");       
	for (int i = 0; i < CAPACITY-1; i++) {
        appended = list_append(list, 2 * i + 1);
    }

    int backing3[CAPACITY] = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
    ref_lst.elems = backing3;
    ref_lst.size = CAPACITY;
    appended = list_append(list, 19);
    sput_fail_unless(appended == true &&
                     list->capacity == CAPACITY &&
                     list->size == CAPACITY &&
                     compare_arrays(list->elems, ref_lst.elems, CAPACITY),
                     "appended = list_append(list, 19);");
	printf("Expected: true, actual: ");
	_print_bool(appended);
	printf("\n");
    printf("Expected list: ");
    list_print(&ref_lst);
    printf("Actual list: ");
    list_print(list);
    printf("\n");

    if (__sput.suite.nok != 0) {
        return;
    }

    /* Now attempt to append an integer to a full list.*/

    appended = list_append(list, 21);
    sput_fail_unless(appended == false &&
                     list->capacity == CAPACITY &&
                     list->size == CAPACITY &&
                     compare_arrays(list->elems, ref_lst.elems, CAPACITY),
                     "appended = list_append(list, 21);");
	printf("Expected: false, actual: ");
	_print_bool(appended);
	printf("\n");
    printf("Expected list: ");
    list_print(&ref_lst);
    printf("Actual list: ");
    list_print(list);
    printf("\n");

    list_destroy(list);
}

static void test_list_capacity(void)
{
    #define CAPACITY 10

    list_t ref_lst;
    int backing[CAPACITY];  // backing array contents aren't important for this test
    ref_lst.elems = backing;
    ref_lst.capacity = CAPACITY;
    ref_lst.size = 0;

    list_t *list = list_construct(CAPACITY);
    int capacity = list_capacity(list);

    sput_fail_unless(capacity == ref_lst.capacity,
                     "list = list_construct(10); "
                     "capacity = list_capacity(list);");
	printf("Expected: %d, actual: %d\n", ref_lst.capacity, capacity);


	printf("Initializing list to [0 2 4 6 8]\n");
 
    for (int i = 0; i < 5; i++) {
        list_append(list, 2 * i);
    }

    capacity = list_capacity(list);
    sput_fail_unless(capacity == ref_lst.capacity,
                     "capacity = list_capacity(list);");
	printf("Expected: %d, actual: %d\n", ref_lst.capacity, capacity);

    list_destroy(list);
}

static void test_list_size(void)
{
    #define CAPACITY 10

    list_t ref_lst;
    int backing[CAPACITY];  // backing array contents aren't important for this test
    ref_lst.elems = backing;
    ref_lst.capacity = CAPACITY;
    ref_lst.size = 0;

    list_t *list = list_construct(CAPACITY);
    int size = list_size(list);

    sput_fail_unless(size == ref_lst.size,
                     "list = list_construct(10); "
                     "size = list_size(list);");
	printf("Expected: %d, actual: %d\n", ref_lst.size, size);

	printf("Initializing list to [0 2 4 6 8]\n");
 
    for (int i = 0; i < 5; i++) {
        list_append(list, 2 * i);
    }

    ref_lst.size = 5;
    size = list_size(list);
    sput_fail_unless(size == ref_lst.size,
                     "size = list_size(list);");
	printf("Expected: %d, actual: %d\n", ref_lst.size, size);
  
    list_destroy(list);
}

static void test_list_get(void)
{
    list_t ref_lst;
    int backing[10] = {0, 2, 4, 6, 8};
    ref_lst.elems = backing;
    ref_lst.capacity = 10;
    ref_lst.size = 5;

	printf("Creating an empty list: list = list_construct(10);\n");
    list_t *list = list_construct(10);
	printf("Initializing list to [0 2 4 6 8]\n");

    for (int i = 0; i < 5; i++) {
        list_append(list, 2 * i);
    }

    /* In addition to verifying that get returns the expected value,
       we also verify that it doesn't modify the list's size,
       capacity or backing array.
     */
    int expected = ref_lst.elems[0];
    int elem = list_get(list, 0);
    sput_fail_unless(elem == expected &&
                     list->size == ref_lst.size &&
                     list->capacity == ref_lst.capacity &&
                     compare_arrays(list->elems, ref_lst.elems, 5),
                     "elem = list_get(list, 0);");
	printf("Expected: %d, actual: %d\n", expected, elem);
    printf("Expected list: ");
    list_print(&ref_lst);
    printf("Actual list: ");
    list_print(list);
    printf("\n");

    expected = ref_lst.elems[1];
    elem = list_get(list, 1);
    sput_fail_unless(elem == expected &&
                     list->size == ref_lst.size &&
                     list->capacity == ref_lst.capacity &&
                     compare_arrays(list->elems, ref_lst.elems, 5),
                     "elem = list_get(list, 1);");
	printf("Expected: %d, actual: %d\n", expected, elem);
    printf("Expected list: ");
    list_print(&ref_lst);
    printf("Actual list: ");
    list_print(list);
    printf("\n");

    expected = ref_lst.elems[2];
    elem = list_get(list, 2);
    sput_fail_unless(elem == expected &&
                     list->size == ref_lst.size &&
                     list->capacity == ref_lst.capacity &&
                     compare_arrays(list->elems, ref_lst.elems, 5),
                     "elem = list_get(list, 2);");
	printf("Expected: %d, actual: %d\n", expected, elem);
    printf("Expected list: ");
    list_print(&ref_lst);
    printf("Actual list: ");
    list_print(list);
    printf("\n");

    expected = ref_lst.elems[3];
    elem = list_get(list, 3);
    sput_fail_unless(elem == expected &&
                     list->size == ref_lst.size &&
                     list->capacity == ref_lst.capacity &&
                     compare_arrays(list->elems, ref_lst.elems, 5),
                     "elem = list_get(list, 3);");
	printf("Expected: %d, actual: %d\n", expected, elem);
    printf("Expected list: ");
    list_print(&ref_lst);
    printf("Actual list: ");
    list_print(list);
    printf("\n");

    expected = ref_lst.elems[4];
    elem = list_get(list, 4);
    sput_fail_unless(elem == expected &&
                     list->size == ref_lst.size &&
                     list->capacity == ref_lst.capacity &&
                     compare_arrays(list->elems, ref_lst.elems, 5),
                     "elem = list_get(list, 4);");
	printf("Expected: %d, actual: %d\n", expected, elem);
    printf("Expected list: ");
    list_print(&ref_lst);
    printf("Actual list: ");
    list_print(list);
    printf("\n");

    list_destroy(list);
}

static void test_list_set(void)
{
    list_t ref_lst;
    int backing[10] = {0, 2, 4, 6, 8};
    ref_lst.elems = backing;
    ref_lst.capacity = 10;
    ref_lst.size = 5;

	printf("Creating an empty list: list = list_construct(10);\n");
    list_t *list = list_construct(10);
	printf("Initializing list to [0 2 4 6 8]\n");

    for (int i = 0; i < 5; i++) {
        list_append(list, 2 * i);
    }

    /* In addition to verifying that set returns the expected value,
       we also verify that it modifies only the specified location in
       the backing array, and doesn't modify the list's size or capacity.
     */

	int previous;
    int expected;
    expected = ref_lst.elems[0];
    ref_lst.elems[0] = 1;
    previous = list_set(list, 0, 1);
    sput_fail_unless(previous == expected &&
                     list->size == ref_lst.size &&
                     list->capacity == ref_lst.capacity &&
                     compare_arrays(list->elems, ref_lst.elems, 5),
                     "previous = list_set(list, 0, 1);");
	printf("Expected: %d, actual: %d\n", expected, previous);
    printf("Expected list: ");
    list_print(&ref_lst);
    printf("Actual list: ");
    list_print(list);
    printf("\n");

    expected = ref_lst.elems[1];
    ref_lst.elems[1] = 3;
    previous = list_set(list, 1, 3);
    sput_fail_unless(previous == expected &&
                     list->size == ref_lst.size &&
                     list->capacity == ref_lst.capacity &&
                     compare_arrays(list->elems, ref_lst.elems, 5),
                     "previous = list_set(list, 1, 3);");
	printf("Expected: %d, actual: %d\n", expected, previous);
    printf("Expected list: ");
    list_print(&ref_lst);
    printf("Actual list: ");
    list_print(list);
    printf("\n");

    expected = ref_lst.elems[2];
    ref_lst.elems[2] = 5;
    previous = list_set(list, 2, 5);
    sput_fail_unless(previous == expected &&
                     list->size == ref_lst.size &&
                     list->capacity == ref_lst.capacity &&
                     compare_arrays(list->elems, ref_lst.elems, 5),
                     "previous = list_set(list, 2, 5);");
	printf("Expected: %d, actual: %d\n", expected, previous);
    printf("Expected list: ");
    list_print(&ref_lst);
    printf("Actual list: ");
    list_print(list);
    printf("\n");

    expected = ref_lst.elems[3];
    ref_lst.elems[3] = 7;
    previous = list_set(list, 3, 7);
    sput_fail_unless(previous == expected &&
                     list->size == ref_lst.size &&
                     list->capacity == ref_lst.capacity &&
                     compare_arrays(list->elems, ref_lst.elems, 5),
                     "previous = list_set(list, 3, 7);");
	printf("Expected: %d, actual: %d\n", expected, previous);
    printf("Expected list: ");
    list_print(&ref_lst);
    printf("Actual list: ");
    list_print(list);
    printf("\n");

    expected = ref_lst.elems[4];
    ref_lst.elems[4] = 9;
    previous = list_set(list, 4, 9);
    sput_fail_unless(previous == expected &&
                     list->size == ref_lst.size &&
                     list->capacity == ref_lst.capacity &&
                     compare_arrays(list->elems, ref_lst.elems, 5),
                     "previous = list_set(list, 4, 9);");
	printf("Expected: %d, actual: %d\n", expected, previous);
    printf("Expected list: ");
    list_print(&ref_lst);
    printf("Actual list: ");
    list_print(list);
    printf("\n");

    list_destroy(list);
}

static void test_list_removeall(void)
{
    #define CAPACITY 10

 	printf("Creating an empty list: list = list_construct(10);\n");
    list_t *list = list_construct(CAPACITY);
	printf("Initializing list to [0 2 4 6 8]\n");

    for (int i = 0; i < 5; i++) {
        list_append(list, 2 * i);
    }

    list_removeall(list);
    sput_fail_unless(list->elems!= NULL &&
                     list->size == 0 &&
                     list->capacity == CAPACITY,
                     "list_removeall(list);");

    printf("Expected result: list->elems = a non-NULL pointer, "
           "list->capacity = %d, list->size = 0\n", CAPACITY);
    printf("Actual result: ");
    printf("list->elems = %p ", list->elems);
    if (list->elems == NULL) {
        printf("(NULL pointer)\n");
    } else {
        printf("(non-NULL pointer)\n");
    }
    printf("list->capacity = %d, list->size = %d\n", list->capacity, list->size);

    list_destroy(list);
}

int main(void)
{
	printf("Running test harness for SYSC 2006 Winter 2019 Lab 7\n");

    sput_start_testing();

    sput_enter_suite("Exercise 1: list_construct()");
    sput_run_test(test_list_construct);
    sput_leave_suite();

    if (sput_get_return_value() == EXIT_FAILURE) {
        printf("Remaining tests won't be run until list_construct "
               "passes all tests.\n");
        sput_finish_testing();
        return sput_get_return_value();
    }

    sput_enter_suite("Exercise 2: list_append()");
    sput_run_test(test_list_append);
    sput_leave_suite();

    if (sput_get_return_value() == EXIT_FAILURE) {
        printf("Remaining tests won't be run until list_append "
               "passes all tests.\n");
        sput_finish_testing();
        return sput_get_return_value();
    }

    sput_enter_suite("Exercise 3: list_capacity()");
    sput_run_test(test_list_capacity);
    sput_leave_suite();

    if (sput_get_return_value() == EXIT_FAILURE) {
        printf("Remaining tests won't be run until list_capacity "
               "passes all tests.\n");
        sput_finish_testing();
        return sput_get_return_value();
    }

    sput_enter_suite("Exercise 4: list_size()");
    sput_run_test(test_list_size);
    sput_leave_suite();

    if (sput_get_return_value() == EXIT_FAILURE) {
        printf("Remaining tests won't be run until list_size "
               "passes all tests.\n");
        sput_finish_testing();
        return sput_get_return_value();
    }

    sput_enter_suite("Exercise 5: list_get()");
    sput_run_test(test_list_get);
    sput_leave_suite();

    if (sput_get_return_value() == EXIT_FAILURE) {
        printf("Remaining tests won't be run until list_get "
               "passes all tests.\n");
        sput_finish_testing();
        return sput_get_return_value();
    }

    sput_enter_suite("Exercise 6: list_set()");
    sput_run_test(test_list_set);
    sput_leave_suite();

    if (sput_get_return_value() == EXIT_FAILURE) {
        printf("Remaining tests won't be run until list_set "
               "passes all tests.\n");
        sput_finish_testing();
        return sput_get_return_value();
    }

    sput_enter_suite("Exercise 7: list_removeall()");
    sput_run_test(test_list_removeall);

    sput_finish_testing();
    return sput_get_return_value();
}
