import java.io.*;

public class Integer_SQRT {

	public static int integer_sqrt_v1(int n) {
		int small_candidate, large_candidate;
		if (n < 0) {
			System.out.println("Input should be positive or null.");
			return -1;
		}
		if (n < 2)
			return n;
		small_candidate = integer_sqrt_v1(n >> 2) << 1;
		large_candidate = small_candidate + 1;
		if (large_candidate * large_candidate > n)
			return small_candidate;
		else
			return large_candidate;
	}

	public static int integer_sqrt_v2(int n) {
		System.out.print("Trace: 2 ");
		int shift, result, large_candidate;
		System.out.print("3 ");
		if (n < 0) {
			System.out.print("4");
			System.out.println("Input should be positive or null.");
			return -1;
		}
		System.out.print("7 ");
		if ((n == 0) || (n == 1)) {
			System.out.print("8");
			return n;
		}
		System.out.print("9 10 ");
		shift = 2;
		while ((n >> shift) != 0) {
			System.out.print("11 ");
			shift += 2;
		}
		System.out.print("13 14 ");
		result = 0;
		while (shift >= 0) {
			System.out.print("15 16 17 ");
			result = result << 1;
			large_candidate = result + 1;
			if (large_candidate * large_candidate <= (n >> shift)) {
				System.out.print("18 ");
				result = large_candidate;
			}
			System.out.print("19 ");
			shift -= 2;
		}
		System.out.print("21");
		return result;
	}

	public static int integer_sqrt_v3(int n) {
		if (n < 0) {
			System.out.println("Input should be positive or null.");
			return -1;
		}
		int x0 = n >> 1;
		if (x0 >= 1) {
			int x1 = (x0 + n / x0) >> 1;
			while (x1 < x0) {
				x0 = x1;
				x1 = (x0 + n / x0) >> 1;
			}
			return x0;
		} else
			return n;
	}

}
