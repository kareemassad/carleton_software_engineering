/* SYSC 2006 Fall 2019 Lab 4. 

 * Incomplete implementations of the functions that will be coded during the lab.
 */

#include <stdlib.h>
#include <math.h>
#include <stdbool.h>

#include "exercises.h"

/* Exercise 1. */

/* Return the average magnitude of the signal represented by
 * the n doubles in x[]. 
 * This function assumes that parameter n is >= 1.
 */
double avg_magnitude(double x[], double n)
{	
	double sum = 0.0;
	for(int i = 0; i < n; i ++){
		sum = sum + fabs(x[i]);
	}
    return sum/n;
}

/* Exercise 2. */

/* Return the average power of the signal represented by
 * the n doubles in x[].
 * This function assumes that parameter n is >= 1.
 */
double avg_power(double x[], double n)
{
	double sum = 0.0;
	for(int i = 0; i < n; i ++){
		sum = sum + x[i] * x[i];//pow(x[i],2)
	}
    return sum/n;
}

/* Exercise 3. */

/* Return the largest of the n doubles in arr[]. 
 * This function assumes that parameter n is >= 1.
 */
double max(double arr[], int n)
{
	double temp = arr[0];
	for (int i = 1; i < n; i++){
		if (temp < arr[i]){ 
			temp = arr[i];
		}
	}
    return temp;
}

/* Exercise 4. */

/* Return the smallest of the n doubles in arr[]. 
 * This function assumes that parameter n is >= 1.
 */
double min(double arr[], int n)
{
    double temp = arr[0];
	for (int i = 1; i < n; i++){
		if (temp > arr[i]){ 
			temp = arr[i];
		}
	}
    return temp;
}

/* Exercise 5. */

/* Normalize the n doubles in x[]. 
 * This function assumes that parameter n is >= 2, and that at least
 * two of the values in x[] are different.
 */
void normalize(double x[], int n)
{
	double maxVal = max(x,n);
	double minVal = min(x,n);
	double range = (maxVal - minVal);

	for (int i = 0; i < n; i++){
		x[i] = (x[i] - minVal) / range;
	}

}
