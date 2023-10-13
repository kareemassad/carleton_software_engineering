/* SYSC 2006 Fall 2017 Lab 5 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <stdbool.h>

#include "exercises.h"

/* Print the first n integers in array arr as a comma-separated list of ints
 * enclosed in braces.
 */
void print_array(int arr[], int n)
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

/* Exercise 1. */

void rotate_left(int arr[], int n)
{
	int temp = arr[0];
	int i;
	for(i = 0; i < n - 1; i++){
		arr[i] = arr[i + 1];
	} 
	arr[i] = temp;
}

/* Exercise 2. */

void reverse(int arr[], int n)
{
	/*
	int temp;
	
	int i = 0;
	for(i;i < arraySize;i++){
		temp = arr[i];
		arr[i] = arr[arraySize];
		arr[arraySize] = temp;
		arraySize--;
	}
	*/
	for(int i = 0; i < n/2; i++){
    	int temp = arr[i];
    	arr[i] = arr[n -i-1];
   		arr[n-i-1] = temp;
	}
}
/* Exercise 3. */

void ten_run(int arr[], int n)
{
	for(int i=0; i < n; i++)
	{
		if(arr[i] % 10 == 0 && arr[i+1] % 10 != 0){
			arr[i + 1] = arr[i];
		}
	}
}


/* Extra-practice exercise. */

/* Exercise 4. */

void without_tens(int arr[], int n)
{

}

