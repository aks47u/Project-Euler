package Solved_101_125;

/**
 * Square remainders
 * Problem 120
 * 
 * Let r be the remainder when (a-1)^n + (a+1)^n is divided by a^2.
 * 
 * For example, if a = 7 and n = 3, then r = 42: 6^3 + 8^3 = 728 == 42 mod 49.
 * And as n varies, so too will r, but for a = 7 it turns out that rmax = 42.
 * 
 * For 3 <= a <= 1000, find SUM rmax.
 */
public class PE120_Square_remainders {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int result = 0;

		for (int a = 3; a <= 1000; a++) {
			result += (a * (a - ((a % 2 == 0) ? 2 : 1)));
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
