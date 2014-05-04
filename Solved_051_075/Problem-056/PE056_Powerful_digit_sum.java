package Solved_051_075;

import java.math.BigInteger;

/**
 * Powerful digit sum
 * Problem 56
 * 
 * A googol (10^100) is a massive number: one followed by one-hundred zeros;
 * 100^100 is almost unimaginably large: one followed by two-hundred zeros.
 * Despite their size, the sum of the digits in each number is only 1.
 * 
 * Considering natural numbers of the form, a^b, where a, b < 100, what is the
 * maximum digital sum?
 */
public class PE056_Powerful_digit_sum {
	public static void main(String[] args) {
		long start = System.nanoTime();

		BigInteger base, power;
		int digitSum, result = 0;

		for (int a = 1; a < 100; a++) {
			base = new BigInteger(String.valueOf(a));
			power = BigInteger.ONE;

			for (int b = 1; b < 100; b++) {
				power = power.multiply(base);
				digitSum = getDigitSum(power.toString());

				if (digitSum > result) {
					result = digitSum;
				}
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static int getDigitSum(String number) {
		int total = 0;

		for (int i = 0; i < number.length(); i++) {
			total += number.charAt(i) - 48;
		}

		return total;
	}
}
