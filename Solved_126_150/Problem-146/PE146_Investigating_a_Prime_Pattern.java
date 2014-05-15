package Solved_126_150;

import java.math.BigInteger;

/**
 * Investigating a Prime Pattern
 * Problem 146
 * 
 * The smallest positive integer n for which the numbers n^2+1, n^2+3, n^2+7,
 * n^2+9, n^2+13, and n^2+27 are consecutive primes is 10. The sum of all such
 * integers n below one-million is 1242490.
 * 
 * What is the sum of all such integers n below 150 million?
 */
public class PE146_Investigating_a_Prime_Pattern {
	private static int[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
		41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107,
		109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179,
		181, 191, 193, 197, 199, 211, 223, 227, 229 };
	private static int L = primes.length;
	private static boolean[][] modAllow = new boolean[L][];
	private static int[] add = { 1, 3, 7, 9, 13, 27 };

	public static void main(String[] args) {
		long start = System.nanoTime();

		for (int i = 0; i < L; ++i) {
			modAllow[i] = new boolean[primes[i]];

			for (int n = 0; n < primes[i]; ++n) {
				int n2 = n * n;
				modAllow[i][n] = true;

				for (int j = 0; j < add.length; ++j) {
					modAllow[i][n] &= (n2 + add[j]) % primes[i] != 0;
				}
			}
		}

		long result = 0;

		a_loop: for (int a = 0; a < 30030; ++a) {
			for (int i = 0; i < 6; ++i) {
				if (!modAllow[i][a % primes[i]]) {
					continue a_loop;
				}
			}

			c_loop: for (int c = a; c < 150000000; c += 30030) {
				for (int i = 6; i < L; ++i) {
					if (!modAllow[i][c % primes[i]])
						continue c_loop;
				}

				long c2 = (long) c * c;

				for (int j = 0; j < add.length; ++j) {
					BigInteger t = BigInteger.valueOf(c2 + add[j]);

					if (!t.isProbablePrime(10)) {
						continue c_loop;
					}
				}

				for (int j = 1; j < add.length; ++j) {
					for (int k = add[j - 1] + 1; k < add[j]; ++k) {
						BigInteger t = BigInteger.valueOf(c2 + k);

						if (t.isProbablePrime(10)) {
							continue c_loop;
						}
					}
				}

				result += c;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
