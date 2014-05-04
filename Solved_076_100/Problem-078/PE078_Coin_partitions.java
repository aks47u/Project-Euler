package Solved_076_100;

/**
 * Coin partitions
 * Problem 78
 * 
 * Let p(n) represent the number of different ways in which n coins can be
 * separated into piles. For example, five coins can separated into piles in
 * exactly seven different ways, so p(5)=7.
 * OOOOO
 * OOOO O
 * OOO OO
 * OOO O O
 * OO OO O
 * OO O O O
 * O O O O O
 * 
 * Find the least value of n for which p(n) is divisible by one million.
 */
public class PE078_Coin_partitions {
	private static long[] pp = new long[60001];
	private static long mln = 1000000;

	public static void main(String[] args) {
		long start = System.nanoTime();

		for (int k = 0; k < pp.length; k++) {
			pp[k] = -1;
		}

		int result = 1;

		while (true) {
			calc_p(result);

			if (pp[result] == 0) {
				break;
			}

			result++;
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long calc_p(long nn) {
		if (nn < 0) {
			return 0;
		}
		
		if (nn == 0) {
			return pp[0] = 1;
		}
		
		if (nn == 1) {
			return pp[1] = 1;
		}
		
		if (nn == 2) {
			return pp[2] = 2;
		}

		int n = (int) nn;
		
		if (pp[n] != -1) {
			return pp[n];
		}

		long sign = 1, sum = 0;
		long d1 = -1, d2 = -1;

		for (long k = 1; k <= n; k++) {
			sign = ((k & 1) == 1) ? 1 : -1;
			d1 = k * (3 * k - 1) / 2;
			d2 = k * (3 * k + 1) / 2;
			
			if (d1 == n) {
				sum += sign * 1;
				sum %= mln;
			} else if (d1 < n) {
				sum += sign * (pp[(int) (n - d1)]);
				sum %= mln;
			}
			
			if (d2 == n) {
				sum += sign * 1;
				sum %= mln;
			} else if (d2 < n) {
				sum += sign * (pp[(int) (n - d2)]);
				sum %= mln;
			}

			while (sum < 0) {
				sum += mln;
			}

			if (d1 > n) {
				break;
			}
		}

		pp[n] = sum;
		return pp[n];
	}
}
