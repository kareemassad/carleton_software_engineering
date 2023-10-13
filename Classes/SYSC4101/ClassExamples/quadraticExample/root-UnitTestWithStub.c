#include "root.h"
#include "stubFormySquareRoot.h"
#include <math.h>
#include <stdio.h>

int main() {

  double a, b, c, root1, root2;
  int result;
  double expectedRoot1, expectedRoot2;
  double epsilon = 0.000001;

  printf("\nUnit tests for root, while stubbing mySquareRoot.\n");

  // test case 1
  setReturnValue(5);
  a = -2; b = 1; c = 3;
  expectedRoot1 = -1; expectedRoot2 = 1.5;
  result = root(a, b, c, &root1, &root2);
  if ( (result==1) && (fabs(expectedRoot1-root1)<epsilon) && (fabs(expectedRoot2-root2)<epsilon) ) printf("test case 1 passes.\n");
  else printf("test case 1 fails.\n");

  // test case 2
  setReturnValue(10);
  a = -4; b = 2; c = 6;
  expectedRoot1 = -1; expectedRoot2 = 1.5;
  result = root(a, b, c, &root1, &root2);
  if ( (result==1) && (fabs(expectedRoot1-root1)<epsilon) && (fabs(expectedRoot2-root2)<epsilon) ) printf("test case 2 passes.\n");
  else printf("test case 2 fails.\n");

  // test case 3
  setReturnValue(6);
  a = 4; b = -2; c = -2;
  expectedRoot1 = 1; expectedRoot2 = -0.5;
  result = root(a, b, c, &root1, &root2);
  if ( (result==1) && (fabs(expectedRoot1-root1)<epsilon) && (fabs(expectedRoot2-root2)<epsilon) ) printf("test case 3 passes.\n");
  else printf("test case 3 fails.\n");

  // test case 4
  setReturnValue(8);
  a = 4; b = 0; c = -4;
  expectedRoot1 = 1; expectedRoot2 = -1;
  result = root(a, b, c, &root1, &root2);
  if ( (result==1) && (fabs(expectedRoot1-root1)<epsilon) && (fabs(expectedRoot2-root2)<epsilon) ) printf("test case 4 passes.\n");
  else printf("test case 4 fails.\n");

  // test case 5
  setReturnValue(3.4641016115);
  a = 1; b = -2; c = -2;
  expectedRoot1 = 2.73205080; expectedRoot2 = -0.73205080;
  result = root(a, b, c, &root1, &root2);
  if ( (result==1) && (fabs(expectedRoot1-root1)<epsilon) && (fabs(expectedRoot2-root2)<epsilon) ) printf("test case 5 passes.\n");
  else printf("test case 5 fails.\n");

  // test case 6
  setReturnValue(0);
  a = 1; b = 1; c = 1;
  expectedRoot1 = 0; expectedRoot2 = 0;
  result = root(a, b, c, &root1, &root2);
  if (result==0) printf("test case 6 passes.\n");
  else printf("test case 6 fails.\n");

  // test case 7
  setReturnValue(0);
  a = 1; b = 2; c = 1;
  expectedRoot1 = -1; expectedRoot2 = -1;
  result = root(a, b, c, &root1, &root2);
  if ( (result==1) && (fabs(expectedRoot1-root1)<epsilon) && (fabs(expectedRoot2-root2)<epsilon) ) printf("test case 7 passes.\n");
  else printf("test case 7 fails.\n");

}
