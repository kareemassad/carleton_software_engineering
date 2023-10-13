// file root.c
#include "root.h"
#include "mySquareRoot.h"

int root(double a, double b, double c, double *root1, double *root2) {
    int result;
    double determinant = b*b-4*a*c;

    if (determinant > 0) {
        *root1 = (-b+mySquareRoot(determinant))/(2*a);
        *root2 = (-b-mySquareRoot(determinant))/(2*a);
        result = 1;
    }
    else if (determinant == 0) {
        *root1 = *root2 = -b/(2*a);
        result = 1;
    }
    else {
        result = 0;
    }
    return result;
}   
