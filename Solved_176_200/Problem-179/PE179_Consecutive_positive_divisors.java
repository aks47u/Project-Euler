package Solved_176_200;

import java.util.Arrays;

/**
 * Consecutive positive divisors
 * Problem 179
 * 
 * Find the number of integers 1 < n < 10^7, for which n and n + 1 have the same
 * number of positive divisors. For example, 14 has the positive divisors 1, 2,
 * 7, 14 while 15 has 1, 3, 5, 15.
 */
public class PE179_Consecutive_positive_divisors {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int limit = 10000001;
		int[] sieve = new int[limit];
		Arrays.fill(sieve, 1);
		sieve[0] = 0;

		for (int p = 2; p < limit; p++) {
			if (sieve[p] == 1) {
				for (int j = 1; j * p < limit; j++) {
					int count = 2;
					int test = j;

					while (test % p == 0) {
						count++;
						test = test / p;
					}

					sieve[j * p] = sieve[j * p] * count;
				}
			}
		}

		int result = 0;

		for (int i = 2; i < limit - 1; i++) {
			if (sieve[i] == sieve[i + 1]) {
				result++;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
