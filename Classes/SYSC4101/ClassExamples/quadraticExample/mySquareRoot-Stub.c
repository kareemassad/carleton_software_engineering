#include "mySquareRoot.h"
#include "stubFormySquareRoot.h"

static double valueToReturn;

void setReturnValue(double r) {
  valueToReturn = r;
}

double mySquareRoot(double n) {
 return valueToReturn;
}

