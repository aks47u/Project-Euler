package Solved_126_150;

/**
 * Singleton difference
 * Problem 136
 * 
 * The positive integers, x, y, and z, are consecutive terms of an arithmetic
 * progression. Given that n is a positive integer, the equation, x^2 - y^2 -
 * z^2 = n, has exactly one solution when n = 20:
 * 
 * 13^2 - 10^2 - 7^2 = 20
 * 
 * In fact there are twenty-five values of n below one hundred for which the
 * equation has a unique solution.
 * 
 * How many values of n less than fifty million have exactly one solution?
 */
public class PE136_Singleton_difference {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int result = 0;
		byte[] count = new byte[50000001];

		for (int i = 1; i <= 50000000; i++) {
			for (int j = i / 4 + 1; i - j > 0; j++) {
				int val = (4 * j - i) * i;

				if (val > 50000000) {
					break;
				}

				if (count[val] < 2) {
					count[val]++;
				}
			}
		}

		for (int i : count) {
			if (i == 1) {
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
