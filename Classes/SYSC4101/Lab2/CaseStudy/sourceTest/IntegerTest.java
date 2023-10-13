public class IntegerTest {
      public static void main(String args[]) {
            System.out.println("square root of 9: " + Integer_SQRT.integer_sqrt_v1(9));
            // run test suite 1 and 2
            testSuite1();
            testSuite2();
            testSuite3();
      }

      // create TestSuite1 for integer_sqrt_v1
      public static void testSuite1() {
            System.out.println("Test Suite 1 for integer_sqrt_v1");
            // test the class
            Integer_SQRT t1 = new Integer_SQRT();
            System.out.println(t1);

            // test if n < 0
            System.out.println("square root of -1: " + Integer_SQRT.integer_sqrt_v1(-1));
            // test if n = 0
            System.out.println("square root of 0: " + Integer_SQRT.integer_sqrt_v1(0));
            // test if n > 0
            System.out.println("square root of 9: " + Integer_SQRT.integer_sqrt_v1(9));
            // test if large_candidate * large_candidate > n
            System.out.println("square root of 10: " + Integer_SQRT.integer_sqrt_v1(10));
            // test if large_candidate * large_candidate <= n
            System.out.println("square root of 11: " + Integer_SQRT.integer_sqrt_v1(11));
            // test class Integer_SQRT
            System.out.println("integer_sqrt class " + Integer_SQRT.class);
            // try big n
            System.out.println("square root of 500: " + Integer_SQRT.integer_sqrt_v1(500));
      }

      public static void testSuite2() {
            // test if n < 0
            System.out.println("square root of -1: " + Integer_SQRT.integer_sqrt_v2(-1));
            // test if n = 0
            System.out.println("square root of 0: " + Integer_SQRT.integer_sqrt_v2(0));
            // test if n = 1
            System.out.println("square root of 1: " + Integer_SQRT.integer_sqrt_v2(1));
            // test if n > 0
            System.out.println("square root of 9: " + Integer_SQRT.integer_sqrt_v2(9));
      }

      // public static void testSuite2() {
      // // test with n=2
      // System.out.println("square root of 2: " + Integer_SQRT.integer_sqrt_v2(2));
      // // test with n=3
      // System.out.println("square root of 3: " + Integer_SQRT.integer_sqrt_v2(3));
      // }

      public static void testSuite3() {
            // test if n < 0
            System.out.println("square root of -1: " + Integer_SQRT.integer_sqrt_v3(-1));
            // test if n = 0
            System.out.println("square root of 0: " + Integer_SQRT.integer_sqrt_v3(0));
            // test if n > 0
            System.out.println("square root of 9: " + Integer_SQRT.integer_sqrt_v3(9));
      }
}
