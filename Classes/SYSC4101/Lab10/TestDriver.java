public class TestDriver {

    public static void main(String[] args) {
        // following the same schema from the labs
        testCase1();
    }

    private static void testCase1() {
        System.out.println("Test Case 1");
        System.out.println("-----------");
        Factorial.compute(new FeedStubOne());
        System.out.println("End of Test Case 1");

    }
}
