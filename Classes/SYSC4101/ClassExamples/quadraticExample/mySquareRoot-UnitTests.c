#include "mySquareRoot.h"
#include <stdio.h>
#include <math.h>

int main() {
  double input;
  double output;
  double expectedOutput;
  double epsilon;

  epsilon = 0.00000001;

  printf("\nUnit tests for mySquareRoot.\n");

  // test case 1
  input = 16;
  expectedOutput = 4;
  output = mySquareRoot(input);
  if (fabs(output-expectedOutput)<epsilon) printf("test case 1 passes.\n");
  else printf("test case 1 fails: getting %f while expecting %f.\n", output, expectedOutput);

  // test case 2
  input = 25;
  expectedOutput = 5;
  output = mySquareRoot(input);
  if (fabs(output-expectedOutput)<epsilon) printf("test case 2 passes.\n");
  else printf("test case 2 fails: getting %f while expecting %f.\n", output, expectedOutput);

  // test case 3
  input = 100;
  expectedOutput = 10;
  output = mySquareRoot(input);
  if (fabs(output-expectedOutput)<epsilon) printf("test case 3 passes.\n");
  else printf("test case 3 fails: getting %f while expecting %f.\n", output, expectedOutput);

  // test case 4
  input = 3;
  expectedOutput = 1.73205080;
  output = mySquareRoot(input);
  if (fabs(output-expectedOutput)<epsilon) printf("test case 4 passes.\n");
  else printf("test case 4 fails: getting %f while expecting %f.\n", output, expectedOutput);

  // test case 5 - is expecting to fail because of the precision of the comparison
  input = 3;
  expectedOutput = 1.732051;
  output = mySquareRoot(input);
  if (fabs(output-expectedOutput)<epsilon) printf("test case 5 passes.\n");
  else printf("test case 5 fails: getting %f while expecting %f.\n", output, expectedOutput);

}
