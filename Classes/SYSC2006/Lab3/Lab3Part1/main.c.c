/* SYSC 2006 Lab 2, Part 1 Test Harness.
 *
 * DO NOT MODIFY THIS FILE!
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#include "sput.h.h"
#include "power.h.h"

/* By default, Pelles C generates "warning #2154: Unreachable code"
   and "warning #2130: Result of comparison is constant" when the 
   macros in sput.h are used. The following pragma directive disables the
   generation of these warnings.
 */
#pragma warn(disable: 2130 2154)

static void test_power1(void)
{
	sput_fail_unless(power1(2, 0) == 1, "power1(2, 0)");
	printf("Expected result: 1, actual result: %d\n", power1(2, 0));
	sput_fail_unless(power1(2, 1) == 2, "power1(2, 1)");
	printf("Expected result: 2, actual result: %d\n", power1(2, 1));
	sput_fail_unless(power1(2, 2) == 4, "power1(2, 2)");
	printf("Expected result: 4, actual result: %d\n", power1(2, 2));
	sput_fail_unless(power1(2, 3) == 8, "power1(2, 3)");
	printf("Expected result: 8, actual result: %d\n", power1(2, 3));
}

static void test_power2(void)
{
	sput_fail_unless(power2(2, 0) == 1, "power2(2, 0)");
	printf("Expected result: 1, actual result: %d\n", power2(2, 0));
	sput_fail_unless(power2(2, 1) == 2, "power2(2, 1)");
	printf("Expected result: 2, actual result: %d\n", power2(2, 1));
	sput_fail_unless(power2(2, 2) == 4, "power2(2, 2)");
	printf("Expected result: 4, actual result: %d\n", power2(2, 2));
	sput_fail_unless(power2(2, 3) == 8, "power2(2, 3)");
	printf("Expected result: 8, actual result: %d\n", power2(2, 3));
}

static void test_power3(void)
{
	sput_fail_unless(power3(2, 0) == 1, "power3(2, 0)");
	printf("Expected result: 1, actual result: %d\n", power3(2, 0));
	sput_fail_unless(power3(2, 1) == 2, "power3(2, 1)");
	printf("Expected result: 2, actual result: %d\n", power3(2, 1));
	sput_fail_unless(power3(2, 2) == 4, "power3(2, 2)");
	printf("Expected result: 4, actual result: %d\n", power3(2, 2));
	sput_fail_unless(power3(2, 3) == 8, "power3(2, 3)");
	printf("Expected result: 8, actual result: %d\n", power3(2, 3));
}

static void test_power4(void)
{
	sput_fail_unless(power4(2, 0) == 1, "power4(2, 0)");
	printf("Expected result: 1, actual result: %d\n", power4(2, 0));
	sput_fail_unless(power4(2, 1) == 2, "power4(2, 1)");
	printf("Expected result: 2, actual result: %d\n", power4(2, 1));
	sput_fail_unless(power4(2, 2) == 4, "power4(2, 2)");
	printf("Expected result: 4, actual result: %d\n", power4(2, 2));
	sput_fail_unless(power4(2, 3) == 8, "power4(2, 3)");
	printf("Expected result: 8, actual result: %d\n", power4(2, 3));
}

int main(void)
{
	printf("Running test harness for SYSC 2006 Lab 2, Part 1\n\n");
	sput_start_testing();

	sput_enter_suite("Testing power1()");
	sput_run_test(test_power1);
	sput_leave_suite();

	if (sput_get_return_value() == EXIT_FAILURE) {
		printf("Tests for other functions won't be run until power1 " "passes all tests.\n");
		sput_finish_testing();
		return sput_get_return_value();
	}

	sput_enter_suite("Testing power2()");
	sput_run_test(test_power2);
	sput_leave_suite();

	if (sput_get_return_value() == EXIT_FAILURE) {
		printf("Tests for other functions won't be run until power2 " "passes all tests.\n");
		sput_finish_testing();
		return sput_get_return_value();
	}

	sput_enter_suite("Testing power3()");
	sput_run_test(test_power3);
	sput_leave_suite();

	if (sput_get_return_value() == EXIT_FAILURE) {
		printf("Tests for other functions won't be run until power2 " "passes all tests.\n");
		sput_finish_testing();
		return sput_get_return_value();
	}

	sput_enter_suite("Testing power4()");
	sput_run_test(test_power4);
	sput_leave_suite();

	sput_finish_testing();
	return sput_get_return_value();
}
