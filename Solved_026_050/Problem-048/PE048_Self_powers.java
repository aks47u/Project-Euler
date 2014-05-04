package Solved_026_050;

import java.math.BigInteger;

/**
 * Self powers
 * Problem 48
 * 
 * The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317. Find the last ten
 * digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.
 */
public class PE048_Self_powers {
	public static void main(String[] args) {
		long start = System.nanoTime();

		BigInteger result = BigInteger.ZERO;
		for (int i = 1; i <= 1000; i++) {
			result = result.add(new BigInteger(i + "").pow(i));
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result.mod(new BigInteger("10000000000")));
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
