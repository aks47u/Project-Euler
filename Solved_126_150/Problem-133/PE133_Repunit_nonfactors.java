package Solved_126_150;

import java.math.BigInteger;

/**
 * Repunit nonfactors
 * Problem 133
 * 
 * A number consisting entirely of ones is called a repunit. We shall define
 * R(k) to be a repunit of length k; for example, R(6) = 111111.
 * 
 * Let us consider repunits of the form R(10^n).
 * 
 * Although R(10), R(100), or R(1000) are not divisible by 17, R(10000) is
 * divisible by 17. Yet there is no value of n for which R(10^n) will divide by
 * 19. In fact, it is remarkable that 11, 17, 41, and 73 are the only four
 * primes below one-hundred that can be a factor of R(10^n).
 * 
 * Find the sum of all the primes below one-hundred thousand that will never be
 * a factor of R(10^n).
 */
public class PE133_Repunit_nonfactors {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long result = 3;

		for (int i = 2; i < 100000; i++) {
			if (isPrime(i) && !modPow(i)) {
				result += i;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static boolean isPrime(int n) {
		if (n <= 1) {
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

	private static boolean modPow(int d) {
		if (BigInteger.TEN.modPow(BigInteger.TEN.pow(100),
				BigInteger.valueOf(d)).compareTo(BigInteger.ONE) == 0) {
			return true;
		} else {
			return false;
		}
	}
}
