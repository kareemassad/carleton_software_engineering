/* main.c - SYSC 2006 Fall 2019 Lab 7 Test Harness 
 *
 * Do not modify any of the code in this file.
 */

//#include <stdlib.h>  // exit, malloc, free
#include <stdio.h>   // printf
#include "sput.h"

#include "fraction.h"

/* By default, Pelles C generates "warning #2154: Unreachable code"
   and "warning #2130: Result of comparison is constant" when the 
   macros in sput.h are used. The following pragma directive disables the
   generation of these warnings.
 */
#pragma warn(disable: 2130 2154)

static void _print(fraction_t f)
{
	printf("%d/%d", f.num, f.den);
}

static void test_print_fraction(void)
{
	printf("\nTesting print_fraction\n\n");

	fraction_t fr;

	fr = (fraction_t) {1, 3};
	printf("Expected output: ");
    _print(fr);
    printf("\n");
    printf("Actual output:   ");
	print_fraction(&fr);
	printf("\n\n");

	fr = (fraction_t) {-1, 3};
	printf("Expected output: ");
    _print(fr);
    printf("\n");
    printf("Actual output:   ");
	print_fraction(&fr);
	printf("\n\n");
}

static void test_gcd(void)
{
    sput_fail_unless(gcd(42, 30) == 6, "gcd(42, 30)");
	printf("Expected result: 6, actual result: %d\n", gcd(42, 30));
    sput_fail_unless(gcd(30, 42) == 6, "gcd(30, 42)");
	printf("Expected result: 6, actual result: %d\n", gcd(30, 42));
    sput_fail_unless(gcd(-42, 30) == 6, "gcd(-42, 30)");
	printf("Expected result: 6, actual result: %d\n", gcd(-42, 30));
    sput_fail_unless(gcd(42, -30) == 6, "gcd(42, -30)");
	printf("Expected result: 6, actual result: %d\n", gcd(42, -30));
    sput_fail_unless(gcd(-42, -30) == 6, "gcd(-42, -30)");
	printf("Expected result: 6, actual result: %d\n", gcd(-42, -30));
    sput_fail_unless(gcd(144, -55) == 1, "gcd(144, -55)");
	printf("Expected result: 1, actual result: %d\n", gcd(144, -55));
}

static void test_reduce(void)
{
    fraction_t fr;

    // Test reduce(1/3)

	fr = (fraction_t) {1, 3}; // Initialize the function to reduce
    reduce(&fr);
    sput_fail_unless(fr.num == 1 && fr.den == 3,
                     "reduce(1/3)");
    printf("Expected result: 1/3, actual result: ");
	_print(fr);
	printf("\n");

    // Test reduce(6/8)

	fr = (fraction_t) {6, 8};
    reduce(&fr);
    sput_fail_unless(fr.num == 3 && fr.den == 4,
                     "reduce(6/8)");
    printf("Expected result: 3/4, actual result: ");
	_print(fr);
	printf("\n");
 
    // Test reduce(-2/3)

	fr = (fraction_t) {-2, 3};
    reduce(&fr);
    sput_fail_unless(fr.num == -2 && fr.den == 3,
                     "reduce(-2/3)");
    printf("Expected result: -2/3, actual result: ");
	_print(fr);
	printf("\n");

    // Test reduce(4/-5)

	fr = (fraction_t) {4, -5};
    reduce(&fr);
    sput_fail_unless(fr.num == -4 && fr.den == 5,
                     "reduce(4/-5)");
    printf("Expected result: -4/5, actual result: ");
	_print(fr);
	printf("\n");

    // Test reduce(-6/-8)

	fr = (fraction_t) {-6, -8};
    reduce(&fr);
    sput_fail_unless(fr.num == 3 && fr.den == 4,
                     "reduce(-6/-8)");
    printf("Expected result: 3/4, actual result: ");
	_print(fr);
	printf("\n");

    // Test reduce(0/7)

	fr = (fraction_t) {0, 7};
    reduce(&fr);
    sput_fail_unless(fr.num == 0 && fr.den == 1,
                     "reduce(0/7)");
    printf("Expected result: 0/1, actual result: ");
	_print(fr);
	printf("\n");
}

void test_make_fraction(void)
{
    fraction_t result;

    // Test make_fraction(1, 3)

    result = (fraction_t) {0, 0};
    make_fraction(1, 3, &result);
    sput_fail_unless(result.num == 1 && result.den == 3,
                     "make_fraction(1, 3, &fr)");
    printf("Expected result: 1/3, actual result: ");
	_print(result);
	printf("\n");


    // Test make_fraction(6, 8)

    result = (fraction_t) {0, 0};
    make_fraction(6, 8, &result);
    sput_fail_unless(result.num == 3 && result.den == 4,
                     "make_fraction(6, 8, &fr)");
    printf("Expected result: 3/4, actual result: ");
	_print(result);
	printf("\n");

    // Test make_fraction(-2, 3)

    result = (fraction_t) {0, 0};
    make_fraction(-2, 3, &result);
    sput_fail_unless(result.num == -2 && result.den == 3,
                     "make_fraction(-2, 3, &fr)");
    printf("Expected result: -2/3, actual result: ");
	_print(result);
	printf("\n");

    // Test make_fraction(4, -5)

    result = (fraction_t) {0, 0};
    make_fraction(4, -5, &result);
    sput_fail_unless(result.num == -4 && result.den == 5,
                     "make_fraction(4, -5, &fr)");
    printf("Expected result: -4/5, actual result: ");
	_print(result);
	printf("\n");

    // Test make_fraction(-6, -8)

    result = (fraction_t) {0, 0};
    make_fraction(-6, -8, &result);
    sput_fail_unless(result.num == 3 && result.den == 4,
                     "make_fraction(-6, -8, &fr)");
    printf("Expected result: 3/4, actual result: ");
	_print(result);
	printf("\n");

    // Test make_fraction(0, 7)

    result = (fraction_t) {0, 0};
    make_fraction(0, 7, &result);
    sput_fail_unless(result.num == 0 && result.den == 1,
                     "make_fraction(0, 7, &fr)");
    printf("Expected result: 0/1, actual result: ");
	_print(result);
	printf("\n");
}

void test_add_fractions(void)
{
   fraction_t fr1, fr2, sum;

    // Test add_fractions: 2/3 + 1/2

    make_fraction(2, 3, &fr1);
    make_fraction(1, 2, &fr2);
    sum = (fraction_t) {0, 0};
    add_fractions(&fr1, &fr2, &sum);
    sput_fail_unless(sum.num == 7 && sum.den == 6,
                     "add_fractions(2/3, 1/2, &sum)");
    printf("Expected result: 7/6, actual result: ");
	_print(sum);
	printf("\n");
   

    // Test add_fractions: 1/2 + 2/1

    make_fraction(1, 2, &fr1);
    make_fraction(2, 1, &fr2);
    sum = (fraction_t) {0, 0};
    add_fractions(&fr1, &fr2, &sum);
    sput_fail_unless(sum.num == 5 && sum.den == 2,
                     "add_fractions(1/2, 2/1, &sum)");
    printf("Expected result: 5/2, actual result: ");
	_print(sum);
	printf("\n");
}


void test_multiply_fractions(void)
{

    fraction_t fr1, fr2, product;

    // Test multiply_fractions: 2/3 * 1/2

    make_fraction(2, 3, &fr1);
    make_fraction(1, 2, &fr2);
    product = (fraction_t) {0, 0};
    multiply_fractions(&fr1, &fr2, &product);
    sput_fail_unless(product.num == 1 && product.den == 3,
                     "multiply_fractions(2/3, 1/2, &product)");
    printf("Expected result: 1/3, actual result: ");
	_print(product);
	printf("\n");
 
    // Test multiply_fractions: 1/2 * 2/1

    make_fraction(1, 2, &fr1);
    make_fraction(2, 1, &fr2);
    product = (fraction_t) {0, 0};
    multiply_fractions(&fr1, &fr2, &product);
    sput_fail_unless(product.num == 1 && product.den == 1,
                     "multiply_fractions(1/2, 2/1, &product)");
    printf("Expected result: 1/1, actual result: ");
	_print(product);
	printf("\n"); 
}

int main(void)
{
    printf("Running test harness for SYSC 2006 Fall 2017 Lab 6\n");

    sput_start_testing();

    sput_enter_suite("Exercise 1: print_fraction()");
    sput_run_test(test_print_fraction);

    sput_enter_suite("Exercise 2: gcd()");
    sput_run_test(test_gcd);
    sput_leave_suite();

    if (sput_get_return_value() == EXIT_FAILURE) {
        printf("Tests for remaining exercises won't be run until gcd "
               "passes all tests.\n");
        sput_finish_testing();
        return sput_get_return_value();
    }

    sput_enter_suite("Exercise 3: reduce()");
    sput_run_test(test_reduce);
    sput_leave_suite();

    if (sput_get_return_value() == EXIT_FAILURE) {
        printf("Tests for remaining exercises won't be run until reduce "
               "passes all tests.\n");
        sput_finish_testing();
        return sput_get_return_value();
    }

    sput_enter_suite("Exercise 4: make_fraction()");
    sput_run_test(test_make_fraction);
    sput_leave_suite();

    if (sput_get_return_value() == EXIT_FAILURE) {
        printf("Tests for remaining exercises won't be run until make_fraction "
               "passes all tests.\n");
        sput_finish_testing();
        return sput_get_return_value();
    }

    sput_enter_suite("Exercise 5: add_fractions()");
    sput_run_test(test_add_fractions);
    sput_leave_suite();

    if (sput_get_return_value() == EXIT_FAILURE) {
        printf("Tests for remaining exercises won't be run until add_fractions "
               "passes all tests.\n");
        sput_finish_testing();
        return sput_get_return_value();
    }

    sput_enter_suite("Exercise 6: multiply_fractions()");
    sput_run_test(test_multiply_fractions); 

    sput_finish_testing();
    return sput_get_return_value();
}



