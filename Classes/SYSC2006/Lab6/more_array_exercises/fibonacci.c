/* SYSC 2006 Fall 2017 Lab 5 */

#include <stdio.h>

int fibonacci(int);

int main(void)
{
    int result;
    result = fibonacci(5);
    printf("fib(%d) =  %d\n", 5, result);   /* Point C. */
    return 0;
}

/* Returns the (n+1)'th number in the Fibonacci sequence, for n >= 0.
   The Fibonacci sequence is defined as: 0, 1, 1, 2, 3, 5, 8, 13, 21, ...
   for n = 0, 1, 2, 3, 4, 5, 6, 7, 8, ...
*/
int fibonacci(int n)
{
    if (n == 0)                 // fib(0)
        return 0;

    if (n == 1)                 // fib(1)
        return 1;

    int temp1 = 0;
    int temp2 = 1;
    int nextfib;

    for (n = n - 2; n >= 0; n = n - 1) {
        nextfib = temp1 + temp2;
        temp1 = temp2;          /* Point A. */
        temp2 = nextfib;
    }
    return nextfib;             /* Point B. */
}
