public class Factorial {
	public static void compute(FeedOfIntValues f) {
		int i;
		while (f.hasNext()) {
			i = f.getNextIntValue();
			System.out.println("Factorial of "+i+" = "+factorial(i));
		}
	}
	public static long factorial(int num) {
		if (num>=1)
			return num * factorial(num-1);
		else
			return 1;

	}
}
