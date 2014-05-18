package Solved_151_175;

/**
 * Finding numbers for which the sum of the squares of the digits is a square
 * Problem 171
 * 
 * For a positive integer n, let f(n) be the sum of the squares of the digits
 * (in base 10) of n, e.g.
 * 
 * f(3) = 3^2 = 9,
 * f(25) = 2^2 + 5^2 = 4 + 25 = 29,
 * f(442) = 4^2 + 4^2 + 2^2 = 16 + 16 + 4 = 36
 * 
 * Find the last nine digits of the sum of all n, 0 < n < 10^20, such that f(n)
 * is a perfect square.
 */
public class PE171_Finding_numbers_for_which_the_sum_of_the_squares_of_the_digits_is_a_square {
	private static final int[] SQ_DIG = { 0, 1, 4, 9, 16, 25, 36, 49, 64, 81 };
	private static long[] ways = { 1 };
	private static long[] sums = { 0 };

	public static void main(String[] args) {
		long start = System.nanoTime();

		solve(0);
		long result = 0;

		for (int i = 1; i * i < sums.length; i++) {
			result += sums[i * i];
		}

		result %= 1000000000;

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static void solve(int digits) {
		if (digits == 20) {
			return;
		}

		long[] nextWays = (digits > 8) ? null : new long[ways.length + 81];
		long[] nextSums = new long[sums.length + 81];

		for (int i = 0; i < sums.length; i++) {
			if (digits > 8) {
				for (int j = 0; j < 10; j++) {
					nextSums[i + SQ_DIG[j]] += sums[i];
				}
			} else if (ways[i] != 0) {
				for (int j = 0; j < 10; j++) {
					nextWays[i + SQ_DIG[j]] += ways[i];
					nextSums[i + SQ_DIG[j]] += sums[i] + ways[i] * j
							* (long) Math.pow(10, digits);
				}
			}
		}

		for (int i = 0; i < nextSums.length; i++) {
			nextSums[i] %= 1000000000;
		}

		ways = nextWays;
		sums = nextSums;
		solve(digits + 1);
	}
}
