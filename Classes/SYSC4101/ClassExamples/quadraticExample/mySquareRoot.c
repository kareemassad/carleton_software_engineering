// file mySquareRoot.c
#include <math.h>
#include <stdlib.h>
#include "mySquareRoot.h"

double mySquareRoot(double n) {
double x0 = n;
    double x1;
double delta;
    for ( ; ; ) {
        x1 = x0 - (x0*x0 - n)/(2*x0);
        delta = (x1-x0)/x0;
        if (delta < .000001 && delta > -.000001)
          return x1;
        x0 = x1;
    }
}   

