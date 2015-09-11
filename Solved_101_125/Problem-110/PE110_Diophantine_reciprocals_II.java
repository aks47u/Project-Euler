package Solved_101_125;

import java.math.BigInteger;

/**
 * Diophantine reciprocals II
 * Problem 110
 * 
 * In the following equation x, y, and n are positive integers.
 * 1   1   1
 * - + - = -
 * x   y   n
 * 
 * It can be verified that when n = 1260 there are 113 distinct solutions and
 * this is the least value of n for which the total number of distinct solutions
 * exceeds one hundred.
 * 
 * What is the least value of n for which the number of distinct solutions
 * exceeds four million?
 * 
 * NOTE: This problem is a much more difficult version of problem 108 and as it
 * is well beyond the limitations of a brute force approach it requires a clever
 * implementation.
 */
public class PE110_Diophantine_reciprocals_II {
	private static BigInteger minN = BigInteger.valueOf(Long.MAX_VALUE);
	private static int[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
		41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107,
		109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179,
		181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251,
		257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331,
		337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409,
		419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487,
		491, 499, 503, 509, 521, 523, 541 };
	private static int limit2 = (int) 8e6;
	private static String result;

	public static void main(String[] args) {
		long start = System.nanoTime();

		int size = (int) Math.ceil(Math.log(limit2) / Math.log(3));
		int[] alpha = new int[size];
		for_loop(alpha, 0);

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static void for_loop(int[] alpha, int depth) {
		if (depth == alpha.length) {
			int mul = 1;

			for (int i = 0; i < alpha.length; i++) {
				mul *= 2 * alpha[i] + 1;
			}

			if (mul > limit2 && mul < limit2 + limit2) {
				BigInteger n;
				n = BigInteger.valueOf(1);

				for (int i = 0; i < alpha.length; i++) {
					for (int j = 0; j < alpha[i]; j++) {
						n = n.multiply(BigInteger.valueOf(primes[i]));
					}
				}

				if (n.compareTo(minN) < 0) {
					minN = n;
					result = n.toString();
				}
			}

			return;
		}

		int i, l;

		if (depth == 0) {
			l = 3;
		} else {
			l = alpha[depth - 1];
		}

		for (i = 0; i <= l; i++) {
			alpha[depth] = i;
			for_loop(alpha, depth + 1);
		}
	}
}
