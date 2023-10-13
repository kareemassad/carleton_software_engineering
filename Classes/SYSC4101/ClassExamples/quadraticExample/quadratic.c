#include <stdio.h>
#include "root.h"

int main() {

    double a, b, c, root1, root2;
    int result;

    printf("Enter coefficients a, b and c: ");
    scanf("%lf %lf %lf",&a, &b, &c);

    result = root(a,b,c,&root1,&root2);
    if (result==1) {
       printf("root1 = %.2lf and root2 = %.2lf\n",root1 , root2);
    } else {
       printf("no solution\n");
    }
return 1;
}

