// Compile using $ javac Q4.java
// Run using $ java Q4

public class Q4
{
	public static void main(String[] args) {
		double mouse = -1000;
        double owl = 100;

        for (int i = 0; i < 55; i++) {
            System.out.println("mouse#" + i + ": " + mouse + " // owl#" + i + ": " + owl);
            double mouseNew = 1.3 * mouse - 0.002 * owl * mouse;
            double owlNew = 0.6 * owl + 0.0004 * owl * mouse;

            mouse = Math.max(0, mouseNew);
            owl = Math.max(0, owlNew);
        }
	}
}
