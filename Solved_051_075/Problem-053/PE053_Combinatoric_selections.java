package Solved_051_075;

/**
 * Combinatoric selections
 * Problem 53
 * 
 * There are exactly ten ways of selecting three from five, 12345:
 * 
 * 123, 124, 125, 134, 135, 145, 234, 235, 245, and 345
 * 
 * In combinatorics, we use the notation, 5C3 = 10.
 * 
 * In general,
 * n_C_r = n! / r!(n-r)!, where r <= n, n! = n×(n-1)×...×3×2×1, and 0! = 1.
 * 
 * It is not until n = 23, that a value exceeds one-million: 23C10 = 1144066.
 * 
 * How many, not necessarily distinct, values of n_C_r, for 1 <= n <= 100, are
 * greater than one-million?
 */
public class PE053_Combinatoric_selections {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int result = 0;
		int millionMinimum = 10;

		for (int n = 23; n < 101; n++) {
			while (nCr(n, --millionMinimum) > 1000000)
				;

			millionMinimum++;
			result += (n - millionMinimum) - millionMinimum + 1;
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long nCr(int n, int r) {
		if (r * 2 < n) {
			r = n - r;
		}

		long result = 1L;

		for (int i = r + 1; i < n + 1; i++) {
			result *= i;
			result /= (i - r);
		}

		return result;
	}
}
