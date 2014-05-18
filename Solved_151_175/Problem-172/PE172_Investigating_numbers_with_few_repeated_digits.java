package Solved_151_175;

/**
 * Investigating numbers with few repeated digits
 * Problem 172
 * 
 * How many 18-digit numbers n (without leading zeros) are there such that no
 * digit occurs more than three times in n?
 */
public class PE172_Investigating_numbers_with_few_repeated_digits {
	private static long[][] cache = new long[18][1 << 20];

	public static void main(String[] args) {
		long start = System.nanoTime();

		long result = rec(0, 0);

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long rec(int bits, int pos) {
		if (pos == 18) {
			return 1;
		}

		if (cache[pos][bits] > 0) {
			return cache[pos][bits] - 1;
		}

		int start = pos == 0 ? 1 : 0;
		long sum = 0;

		for (int d = start; d <= 9; d++) {
			if (((bits >> (d + d)) & 3) != 3) {
				sum += rec(bits + (1 << (d + d)), pos + 1);
			}
		}

		cache[pos][bits] = sum + 1;

		return sum;
	}
}
