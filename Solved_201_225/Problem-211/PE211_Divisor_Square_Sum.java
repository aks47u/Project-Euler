package Solved_201_225;

import java.util.Arrays;

/**
 * Divisor Square Sum
 * Problem 211
 * 
 * For a positive integer n, let phi2(n) be the sum of the squares of its
 * divisors. For example, phi2(10) = 1 + 4 + 25 + 100 = 130.
 * 
 * Find the sum of all n, 0 < n < 64,000,000 such that phi2(n) is a perfect
 * square.
 */
public class PE211_Divisor_Square_Sum {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int n = 64000000;
		long[] sums = getSumsOfSqDivs(n);
		long result = 0;

		for (int i = 1; i < n; i++) {
			if (isSquare(sums[i])) {
				result += i;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long[] getSumsOfSqDivs(int n) {
		long[] sums = new long[n];
		Arrays.fill(sums, 1);
		sums[0] = 0;

		for (int p = 2; p < n; p++) {
			if (sums[p] == 1) {
				long p2 = (long) p * p;

				for (int i = p; i < n; i += p) {
					long s = 1, powp = p, powp2 = p2;

					while (i % powp == 0) {
						s += powp2;
						powp *= p;
						powp2 *= p2;
					}

					sums[i] *= s;
				}
			}
		}

		return sums;
	}

	private static boolean isSquare(long x) {
		long y = Math.round(Math.sqrt(x));

		return y * y == x;
	}
}
