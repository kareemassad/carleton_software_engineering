/* fraction.h - SYSC 2006 Winter 2019 Lab 5 */

typedef struct {
    int num;  // numerator
    int den;  // denominator
} fraction_t;


fraction_t make_fraction(int a, int b);
void print_fraction(fraction_t f);
fraction_t add_fractions(fraction_t f1, fraction_t f2);
fraction_t multiply_fractions(fraction_t f1, fraction_t f2);

/* Helper functions. */
int gcd(int a, int b);
fraction_t reduce(fraction_t f);
