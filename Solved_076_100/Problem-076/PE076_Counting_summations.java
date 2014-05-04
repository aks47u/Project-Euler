package Solved_076_100;

/**
 * Counting summations
 * Problem 76
 * 
 * It is possible to write five as a sum in exactly six different ways:
 * 
 * 4 + 1
 * 3 + 2
 * 3 + 1 + 1
 * 2 + 2 + 1
 * 2 + 1 + 1 + 1
 * 1 + 1 + 1 + 1 + 1
 * 
 * How many different ways can one hundred be written as a sum of at least two
 * positive integers?
 */
public class PE076_Counting_summations {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int result = 0;

		for (long k = 1; k < 100; k++) {
			result += tnk(100, k);
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long tnk(long n, long k) {
		if (k == 1 || n == k) {
			return 1;
		}

		if (k > n) {
			return 0;
		}

		return tnk(n - 1, k - 1) + tnk(n - k, k);
	}
}
