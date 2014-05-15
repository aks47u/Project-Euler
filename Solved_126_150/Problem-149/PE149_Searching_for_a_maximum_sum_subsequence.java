package Solved_126_150;

/**
 * Searching for a maximum-sum subsequence
 * Problem 149
 * 
 * Looking at the table below, it is easy to verify that the maximum possible
 * sum of adjacent numbers in any direction (horizontal, vertical, diagonal or
 * anti-diagonal) is 16 (= 8 + 7 + 1).
 * -2  5  3  2
 *  9 -6  5  1
 *  3  2  7  3
 * -1  8 -4  8
 * 
 * Now, let us repeat the search, but on a much larger scale:
 * 
 * First, generate four million pseudo-random numbers using a specific form of
 * what is known as a "Lagged Fibonacci Generator":
 * 
 * For 1 <= k <= 55, sk = [100003 - 200003k + 300007k^3] (modulo 1000000) -
 * 500000. For 56 <= k <= 4000000, sk = [sk-24 + sk-55 + 1000000] (modulo
 * 1000000) - 500000.
 * 
 * Thus, s10 = -393027 and s100 = 86613.
 * 
 * The terms of s are then arranged in a 2000×2000 table, using the first 2000
 * numbers to fill the first row (sequentially), the next 2000 numbers to fill
 * the second row, and so on.
 * 
 * Finally, find the greatest sum of (any number of) adjacent entries in any
 * direction (horizontal, vertical, diagonal or anti-diagonal).
 */
public class PE149_Searching_for_a_maximum_sum_subsequence {
	public static void main(String[] args) {
		long start = System.nanoTime();
		
		int[] list = new int[4000001];
		int[][] table = new int[2000][2000];

		for (long i = 1; i <= 4000000; i++) {
			if (i <= 55) {
				list[(int) i] = (int) ((100003 + 300007 * i * i * i - 200003 * i) % 1000000) - 500000;
			} else {
				list[(int) i] = (list[(int) i - 24] + list[(int) i - 55] + 1000000) % 1000000 - 500000;
			}
		}

		for (int i = 0; i < 2000; i++) {
			for (int j = 0; j < 2000; j++) {
				table[i][j] = list[2000 * i + j + 1];
			}
		}

		System.out.println(CountHighest(table));
		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long CountHighest(int[][] a) {
		long max = 0, Tsum, Nsum;

		for (int i = 0; i < 2000; i++) {
			Tsum = Nsum = 0;

			for (int j = 0; j < 2000; j++) {
				Nsum += a[i][j];
				Tsum += a[j][i];

				if (Nsum < a[i][j]) {
					Nsum = a[i][j];
				}

				if (Tsum < a[j][i]) {
					Tsum = a[j][i];
				}

				if (max < Nsum) {
					max = Nsum;

				}

				if (max < Tsum) {
					max = Tsum;
				}
			}
		}

		return max;
	}
}
