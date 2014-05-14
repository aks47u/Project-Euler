package Solved_126_150;

import java.math.BigInteger;

/**
 * Large repunit factors
 * Problem 132
 * 
 * A number consisting entirely of ones is called a repunit. We shall define
 * R(k) to be a repunit of length k.
 * 
 * For example, R(10) = 1111111111 = 11×41×271×9091, and the sum of these prime
 * factors is 9414.
 * 
 * Find the sum of the first forty prime factors of R(10^9).
 */
public class PE132_Large_repunit_factors {
	public static void main(String[] args) {
		long start = System.nanoTime();
		
		BigInteger ten = BigInteger.valueOf(10);
		int result = 0, count = 0;

		for (int i = 5; count < 40; i++) {
			if (!isPrime(i)) {
				continue;
			}

			int r = ten.modPow(BigInteger.valueOf(1000000000),
					BigInteger.valueOf(i)).intValue();

			if (r == 1) {
				result += i;
				count++;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	public static boolean isPrime(int n) {
		if (n < 2) {
			return false;
		}

		if (n == 2) {
			return true;
		}

		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}
}
