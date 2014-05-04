package Solved_076_100;

import java.math.BigDecimal;

/**
 * Square root digital expansion
 * Problem 80
 * 
 * It is well known that if the square root of a natural number is not an
 * integer, then it is irrational. The decimal expansion of such square roots is
 * infinite without any repeating pattern at all.
 * 
 * The square root of two is 1.41421356237309504880..., and the digital sum of
 * the first one hundred decimal digits is 475.
 * 
 * For the first one hundred natural numbers, find the total of the digital sums
 * of the first one hundred decimal digits for all the irrational square roots.
 */
public class PE080_Square_root_digital_expansion {
	private static BigDecimal two = new BigDecimal("2");

	public static void main(String[] args) {
		long start = System.nanoTime();

		int result = 0;

		for (int i = 2; i < 100; i++) {
			String s1 = sqrt(i).toString();

			if (s1.substring(2, 7).equals("00000")) {
				continue;
			}

			String s = s1.substring(0, 1).concat(s1.substring(2, 101));

			for (int j = 0; j < 100; j++) {
				result += new Integer(s.substring(j, j + 1)).intValue();
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	public static BigDecimal sqrt(int n) {
		int round = BigDecimal.ROUND_HALF_EVEN;
		BigDecimal nn = new BigDecimal(new Integer(n).toString());
		BigDecimal x = BigDecimal.ONE;

		for (int i = 0; i < 200; i++) {
			x = (x.add(nn.divide(x, 200, round))).divide(two, 200, round);
		}

		return x;
	}
}
