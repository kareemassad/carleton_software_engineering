public class TanTest {

    public static double[] getTestValues() {
        // generate 200 test values
        double[] testValues = new double[100];
        for (int i = 0; i < testValues.length; i++) {
            // fill with random doubles
            testValues[i] = Math.random() * 100;
        }
        return testValues;
    }

    public static void runTest(int testID, double actual, double expected) {
        if (Math.abs(actual - expected) < 0.0001) {
            System.out.println("Test " + testID + " passed");
        } else {
            System.out.println("Test " + testID + " failed");
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
        }
    }

    public static void main(String[] args) {
        double[] testValues = getTestValues();

        // tan(pi-x) = -tan(x)
        System.out.println("tan(pi-x) = -tan(x)");
        for (int i = 0; i < testValues.length; i++) {
            runTest(i + 1, Math.tan(Math.PI - testValues[i]), -Math.tan(testValues[i]));
        }
        // tan(pi+x) = tan(x)
        System.out.println("tan(pi+x) = tan(x)");
        for (int i = 0; i < testValues.length; i++) {
            runTest(i + 1 + testValues.length, Math.tan(Math.PI + testValues[i]), Math.tan(testValues[i]));
        }
        // tan(pi/2-x) = 1/tan(x)
        System.out.println("tan(pi/2-x) = 1/tan(x)");
        for (int i = 0; i < testValues.length; i++) {
            runTest(i + 1 + 2 * testValues.length, Math.tan(Math.PI / 2 - testValues[i]), 1 / Math.tan(testValues[i]));
        }
        // tan(pi/2+x) = -1/tan(x)
        System.out.println("tan(pi/2+x) = 1/tan(x)");
        for (int i = 0; i < testValues.length; i++) {
            runTest(i + 1 + 3 * testValues.length, Math.tan(Math.PI / 2 + testValues[i]), -1 / Math.tan(testValues[i]));
        }

        // tan(-x) = -tan(x)
        System.out.println("tan(-x) = -tan(x)");
        for (int i = 0; i < testValues.length; i++) {
            runTest(i + 1 + 4 * testValues.length, Math.tan(-testValues[i]), -Math.tan(testValues[i]));
        }

        // bonus
        // tan(x) = sin(x)/cos(x)
        System.out.println("tan(x) = sin(x)/cos(x)");
        for (int i = 0; i < testValues.length; i++) {
            runTest(i + 1 + 5 * testValues.length, Math.tan(testValues[i]),
                    Math.sin(testValues[i]) / Math.cos(testValues[i]));
        }

    }
}
