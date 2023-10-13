import java.util.Random;

public class SineTest {
    public static void runTest(int testID, double actual, double expected) {
        if (Math.abs(actual - expected) < 0.0001) {
            System.out.println("Test " + testID + " passed");
        } else {
            System.out.println("Test " + testID + " failed");
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
        }
    }

    public static int getRandomInt() {
        Random rand = new Random();
        return rand.nextInt(-100, 100);
    }

    public static void main(String[] args) {
        // create random number generator x
        int x = getRandomInt();

        // this is where the tests will be run using the runTest method
        // From the link provided we know that:
        // sin(0) = 0
        // sin(PI/2) = 1
        // sin(PI) = 0
        // sin(3PI/2) = -1
        // sin(2PI) = 0
        // sin(0) = cos(PI/2)

        // We know that:
        // sin(pi/2-x) = cos(x)
        // sin(pi-x) = -sin(x)
        // sin(3pi/2-x) = -cos(x)
        // cos(pi/2-x) = sin(x)
        // cos(pi-x) = -cos(x)
        // sin(pi/2+x) = cos(x)
        // cos(pi/2+x) = -sin(x)

        // change x value after each line
        runTest(1, Math.sin(0), Math.cos(Math.PI / 2));
        x = getRandomInt();
        runTest(2, Math.sin(Math.PI / 2 + x), Math.cos(x));
        x = getRandomInt();
        runTest(3, Math.sin(Math.PI + x), -Math.sin(x));
        x = getRandomInt();
        runTest(4, Math.sin(3 * Math.PI / 2 + x), -Math.cos(x));
        x = getRandomInt();
        runTest(5, Math.sin(2 * Math.PI), 0);
        x = getRandomInt();
        runTest(6, Math.cos(Math.PI / 2 - x), Math.sin(x));
        x = getRandomInt();
        runTest(7, Math.cos(Math.PI - x), -Math.cos(x));
        x = getRandomInt();
        runTest(8, Math.cos(3 * Math.PI / 2 - x), -Math.sin(x));
        x = getRandomInt();
        runTest(9, Math.cos(Math.PI / 2 + x), -Math.sin(x));
        x = getRandomInt();
        runTest(10, Math.cos(Math.PI + x), -Math.cos(x));
        x = getRandomInt();
        runTest(11, Math.cos(3 * Math.PI / 2 + x), Math.sin(x));
        x = getRandomInt();
        runTest(12, Math.cos(2 * Math.PI), 1);
    }
}
