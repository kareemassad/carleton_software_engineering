/* fraction.h - SYSC 2006 Fall 2019 Lab 7 */

typedef struct {
    int num;  // numerator
    int den;  // denominator
} fraction_t;

void make_fraction(int a, int b, fraction_t *new_fraction);
void print_fraction(const fraction_t *pf);
void add_fractions(const fraction_t *pf1, const fraction_t *pf2, fraction_t *sum);
void multiply_fractions(const fraction_t *pf1, const fraction_t *pf2, fraction_t *product);

/* Helper functions. */
int gcd(int a, int b);
void reduce(fraction_t *pf);
