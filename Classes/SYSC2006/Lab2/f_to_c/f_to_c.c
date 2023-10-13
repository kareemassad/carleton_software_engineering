/* SYSC 2006 Fall 2019, Lab 1 */

#include <stdio.h>
#include <stdlib.h>

int main(void)
{
	int lower, upper, step;
	float fahr, celsius1, celsius2, celsius3, celsius4, celsius5;

	/* Set lower and upper limits of the
	   table, and step size. 
	 */

	lower = 0;
	upper = 0;
	step = 20;
	fahr = lower;
	int temp1, temp2;
	float temp3, temp4;
	
	temp1 = 5.0/9;
	temp3 = 5.0/9;
	printf("temp1=%d,temp3=%f\n",temp1,temp3); //%d is for integer
	temp2 = 5/9;
	temp4 = 5/9;
	printf("temp2=%d,temp4=%f\n",temp2, temp3);

	while (fahr <= upper) {
		celsius1 = 5.0 / 9 * (fahr - 32);
		celsius2 = 5 / 9.0 * (fahr - 32);
		celsius3 = (fahr - 32) * 5 / 9;
		celsius4 = 5 / 9 * (fahr - 32);
		celsius5 = (fahr - 32) * (5 / 9);

		printf("%4.0f %6.1f %4.0f %4.0f %4.0f\n", fahr, celsius1, celsius2, celsius3, celsius4, celsius5);
		fahr = fahr + step;
	}
	return EXIT_SUCCESS;
}
