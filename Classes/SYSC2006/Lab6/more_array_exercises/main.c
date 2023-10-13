/* SYSC 2006 Fall 2017 Lab 5 Test Harness 
 *
 * Do not modify any of the code in this file.
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <stdbool.h>

#include "exercises.h"

#include "sput.h"
/* By default, Pelles C generates "warning #2154: Unreachable code"
   and "warning #2130: Result of comparison is constant" when the 
   macros in sput.h are used. The following pragma directive disables the
   generation of these warnings.
 */
#pragma warn(disable: 2130 2154)


/* Print the first n integers in array arr as a comma-separated list of ints
 * enclosed in braces.
 */
static void _print_array(int arr[], int n)
{
    printf("{");
    for (int i = 0; i < n; i++) {
        printf("%d", arr[i]);

        /* Print a comma and a space after all but the last element. */
        if (i < n - 1) {
            printf(", ");
        } else {
            printf("}");
        }
    }
}

/* Return true if the first n elements in arrays arr1 and arr2
 * are equal, otherwise return false.
 */
static _Bool compare_arrays(int arr1[], int arr2[], int n)
{
    for (int i = 0; i < n; i = i + 1) {
        if (arr1[i] != arr2[i]) {
            return false;
        }
    }
    return true;
}

static void test_rotate_left(void)
{
    int a1[] = {6, 2, 5, 3};
    int expected1[] = {2, 5, 3, 6};

    int a2[] = {1, 2};
    int expected2[] = {2, 1};

    int a3[] = {1};
    int expected3[] = {1};

    int a4[] = {1, 1, 2, 2, 4};
    int expected4[] = {1, 2, 2, 4, 1};

    int a5[] = {1, 1, 1};
    int expected5[] = {1, 1, 1};

    int a6[] = {1, 2, 3};
    int expected6[] = {2, 3, 1};

    rotate_left(a1, 4);
    sput_fail_unless(compare_arrays(a1, expected1, 4), "rotate_left({6, 2, 5, 3}, 4)");
    printf("Expected result: {2, 5, 3, 6}, actual result: ");
	_print_array(a1, 4);
	printf("\n");

    rotate_left(a2, 2);
    sput_fail_unless(compare_arrays(a2, expected2, 2), "rotate_left({1, 2}, 2)");
    printf("Expected result: {2, 1}, actual result: ");
	_print_array(a2, 2);
	printf("\n");

    rotate_left(a3, 1);
    sput_fail_unless(compare_arrays(a3, expected3, 1), "rotate_left({1}, 1)");
    printf("Expected result: {1}, actual result: ");
	_print_array(a3, 1);
	printf("\n");

    rotate_left(a4, 5);
    sput_fail_unless(compare_arrays(a4, expected4, 5), "rotate_left({1, 1, 2, 2, 4}, 5)");
    printf("Expected result: {1, 2, 2, 4, 1}, actual result: ");
	_print_array(a4, 5);
	printf("\n");

    rotate_left(a5, 3);
    sput_fail_unless(compare_arrays(a5, expected5, 3), "rotate_left({1, 1, 1}, 3)");
    printf("Expected result: {1, 1, 1}, actual result: ");
	_print_array(a5, 3);
	printf("\n");

    rotate_left(a6, 3);
    sput_fail_unless(compare_arrays(a6, expected6, 3), "rotate_left({1, 2, 3}, 3)");
    printf("Expected result: {2, 3, 1}, actual result: ");
	_print_array(a6, 3);
	printf("\n");

	}

static void test_reverse(void)
{
    int a1[] = {1, 2, 3, 4, 5, 6};
    int expected1[] = {6, 5, 4, 3, 2, 1};

    int a2[] = {1, 2, 3, 4, 5, 6, 7};
    int expected2[] = {7, 6, 5, 4, 3, 2, 1};

    int a3[] = {1, 2};
    int expected3[] = {2, 1};

    int a4[] = {1};
    int expected4[] = {1};

    reverse(a1, 6);
    sput_fail_unless(compare_arrays(a1, expected1, 6), "reverse({1, 2, 3, 4, 5, 6}, 6)");
    printf("Expected result: {6, 5, 4, 3, 2, 1}, actual result: ");
	_print_array(a1, 6);
	printf("\n");

    reverse(a2, 7);
    sput_fail_unless(compare_arrays(a2, expected2, 7), "reverse({1, 2, 3, 4, 5, 6, 7}, 7)");
    printf("Expected result: {7, 6, 5, 4, 3, 2, 1}, actual result: ");
	_print_array(a2, 7);
	printf("\n");

    reverse(a3, 2);
    sput_fail_unless(compare_arrays(a3, expected3, 2), "reverse({1, 2}, 2)");
    printf("Expected result: {2, 1}, actual result: ");
	_print_array(a3, 2);
	printf("\n");

    reverse(a4, 1);
    sput_fail_unless(compare_arrays(a4, expected4, 1), "reverse({1}, 1)");
    printf("Expected result: {1}, actual result: ");
	_print_array(a4, 1);
	printf("\n");
}

static void test_ten_run(void)
{
    int a1[] = {2, 10, 3, 4, 20, 5};
    int expected1[] = {2, 10, 10, 10, 20, 20};

    ten_run(a1, 6);
    sput_fail_unless(compare_arrays(a1, expected1, 6), "ten_run({2, 10, 3, 4, 20, 5}, 6)");
    printf("Expected result: {2, 10, 10, 10, 20, 20}, actual result: ");
	_print_array(a1, 6);
	printf("\n");

    int a2[] = {10, 1, 20, 2};
    int expected2[] = {10, 10, 20, 20};
 
    ten_run(a2, 4);
    sput_fail_unless(compare_arrays(a2, expected2, 4), "ten_run({10, 1, 20, 2}, 4)");
    printf("Expected result: {10, 10, 20, 20}, actual result: ");
	_print_array(a2, 4);
	printf("\n");

    int a3[] = {10, 1, 9, 20};
    int expected3[] = {10, 10, 10, 20};

    ten_run(a3, 4);
    sput_fail_unless(compare_arrays(a3, expected3, 4), "ten_run({10, 1, 9, 20}, 4)");
    printf("Expected result: {10, 10, 10, 20}, actual result: ");
	_print_array(a3, 4);
	printf("\n");

    int a4[] = {1, 2, 50, 1};
    int expected4[] = {1, 2, 50, 50};

    ten_run(a4, 4);
    sput_fail_unless(compare_arrays(a4, expected4, 4), "ten_run({1, 2, 50, 1}, 4)");
    printf("Expected result: {1, 2, 50, 50}, actual result: ");
	_print_array(a4, 4);
	printf("\n");

    int a5[] = {1, 20, 50, 1};
    int expected5[] = {1, 20, 50, 50};

    ten_run(a5, 4);
    sput_fail_unless(compare_arrays(a5, expected5, 4), "ten_run({1, 20, 50, 1}, 4)");
    printf("Expected result: {1, 20, 50, 50}, actual result: ");
	_print_array(a5, 4);
	printf("\n");

    int a6[] = {10, 10};
    int expected6[] = {10, 10};

    ten_run(a6, 2);
    sput_fail_unless(compare_arrays(a6, expected6, 2), "ten_run({10, 10}, 2)");
    printf("Expected result: {10, 10}, actual result: ");
	_print_array(a6, 2);
	printf("\n");

    int a7[] = {10, 2};
    int expected7[] = {10, 10};

    ten_run(a7, 2);
    sput_fail_unless(compare_arrays(a7, expected7, 2), "ten_run({10, 2}, 2)");
    printf("Expected result: {10, 10}, actual result: ");
	_print_array(a7, 2);
	printf("\n");

    int a8[] = {0, 2};
    int expected8[] = {0, 0};

    ten_run(a8, 2);
    sput_fail_unless(compare_arrays(a8, expected8, 2), "ten_run({0, 2}, 2)");
    printf("Expected result: {0, 0}, actual result: ");
	_print_array(a8, 2);
	printf("\n");

    int a9[] = {1, 2};
    int expected9[] = {1, 2};

    ten_run(a9, 2);
    sput_fail_unless(compare_arrays(a9, expected9, 2), "ten_run({1, 2}, 2)");
    printf("Expected result: {1, 2}, actual result: ");
	_print_array(a9, 2);
	printf("\n");

    int a10[] = {1};
    int expected10[] = {1};

    ten_run(a10, 1);
    sput_fail_unless(compare_arrays(a10, expected10, 1), "ten_run({1}, 1)");
    printf("Expected result: {1}, actual result: ");
	_print_array(a10, 1);
	printf("\n");
}

void test_without_tens(void)
{
    int a1[] = {1, 10, 10, 2};
    int expected1[] = {1, 2, 0, 0};

    without_tens(a1, 4);
    sput_fail_unless(compare_arrays(a1, expected1, 4), "without_tens({1, 10, 10, 2}, 4)");
    printf("Expected result: {1, 2, 0, 0}, actual result: ");
	_print_array(a1, 4);
	printf("\n");

    int a2[] = {10, 2, 10};
    int expected2[] = {2, 0, 0};

    without_tens(a2, 3);
    sput_fail_unless(compare_arrays(a2, expected2, 3), "without_tens({10, 2, 10}, 3)");
    printf("Expected result: {2, 0, 0}, actual result: ");
	_print_array(a2, 3);
	printf("\n");

    int a3[] = {1, 99, 10};
    int expected3[] = {1, 99, 0};

    without_tens(a3, 3);
    sput_fail_unless(compare_arrays(a3, expected3, 3), "without_tens({1, 99, 10}, 3)");
    printf("Expected result: {1, 99, 0}, actual result: ");
	_print_array(a3, 3);
	printf("\n");

    int a4[] = {10, 13, 10, 14};
    int expected4[] = {13, 14, 0, 0};

    without_tens(a4, 4);
    sput_fail_unless(compare_arrays(a4, expected4, 4), "without_tens({10, 13, 10, 14}, 4)");
    printf("Expected result: {13, 14, 0, 0}, actual result: ");
	_print_array(a4, 4);
	printf("\n");

    int a5[] = {10, 13, 10, 14, 10};
    int expected5[] = {13, 14, 0, 0, 0};

    without_tens(a5, 5);
    sput_fail_unless(compare_arrays(a5, expected5, 5), "without_tens({10, 13, 10, 14, 10}, 5)");
    printf("Expected result: {13, 14, 0, 0, 0}, actual result: ");
	_print_array(a5, 5);
	printf("\n");

    int a6[] = {10, 10, 3};
    int expected6[] = {3, 0, 0};

    without_tens(a6, 3);
    sput_fail_unless(compare_arrays(a6, expected6, 3), "without_tens({10, 10, 3}, 3)");
    printf("Expected result: {3, 0, 0}, actual result: ");
	_print_array(a6, 3);
	printf("\n");

    int a7[] = {1};
    int expected7[] = {1};

    without_tens(a7, 1);
    sput_fail_unless(compare_arrays(a7, expected7, 1), "without_tens({1}, 1)");
    printf("Expected result: {1}, actual result: ");
	_print_array(a7, 1);
	printf("\n");

    int a8[] = {13, 1};
    int expected8[] = {13, 1};

    without_tens(a8, 2);
    sput_fail_unless(compare_arrays(a8, expected8, 2), "without_tens({13, 1}, 2)");
    printf("Expected result: {13, 1}, actual result: ");
	_print_array(a8, 2);
	printf("\n");

    int a9[] = {10};
    int expected9[] = {0};

    without_tens(a9, 1);
    sput_fail_unless(compare_arrays(a9, expected9, 1), "\nwithout_tens({10}, 1)");
    printf("Expected result: {0}, actual result: ");
	_print_array(a9, 1);
	printf("\n");
}

int main(void)
{
	printf("Running test harness for SYSC 2006 Fall 2017 Lab 5\n\n");
    sput_start_testing();

    sput_enter_suite("Exercise 1: rotate_left()");
    sput_run_test(test_rotate_left);
    sput_leave_suite();

    if (sput_get_return_value() == EXIT_FAILURE) {
        printf("Tests for remaining exercises won't be run until rotate_left "
               "passes all tests.\n");
        sput_finish_testing();
        return sput_get_return_value();
    }
 
    sput_enter_suite("Exercise 2: reverse()");
    sput_run_test(test_reverse);
    sput_leave_suite();

    if (sput_get_return_value() == EXIT_FAILURE) {
        printf("Tests for remaining exercises won't be run until reverse "
               "passes all tests.\n");
        sput_finish_testing();
        return sput_get_return_value();
	}

    sput_enter_suite("Exercise 3: ten_run()");
    sput_run_test(test_ten_run);
    sput_leave_suite();

    if (sput_get_return_value() == EXIT_FAILURE) {
        printf("Tests for remaining exercises won't be run until ten_run "
               "passes all tests.\n");
        sput_finish_testing();
        return sput_get_return_value();
	}

    sput_enter_suite("Exercise 4: without_tens()");
    sput_run_test(test_without_tens);

    sput_finish_testing();
    return sput_get_return_value();
}
