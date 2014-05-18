package Solved_176_200;

/**
 * Right-angled triangles that share a cathetus
 * Problem 176
 * 
 * The four right-angled triangles with sides (9,12,15), (12,16,20), (5,12,13)
 * and (12,35,37) all have one of the shorter sides (catheti) equal to 12. It
 * can be shown that no other integer sided right-angled triangle exists with
 * one of the catheti equal to 12.
 * 
 * Find the smallest integer that can be the length of a cathetus of exactly
 * 47547 different integer sided right-angled triangles.
 */
public class PE176_Right_angled_triangles_that_share_a_cathetus {
	private static int[] c; // largest prime factor
	private static int[] x; // next prime
	private static double[] log; // natural log
	private static long min = Long.MAX_VALUE;
	private static double mlg = Math.log(min);

	public static void main(String[] args) {
		long start = System.nanoTime();

		int n = 47547;
		int m = 2 * n + 1; // factorize (2n + 1)
		c = new int[m + 1];
		x = new int[m + 1];
		log = new double[m + 1];

		for (int i = 1; i <= m; i++) {
			log[i] = Math.log(i); // cache logs
		}

		for (int i = 2; i <= m; i++) { // sieve
			if (c[i] == 0) {
				for (int j = i; j <= m; j += i) {
					c[j] = i;
				}
			}
		}

		c[m] = m + 5; // next prime is out of bounds

		for (int i = m - 1; i >= 2; i--) { // next prime
			if (c[i] == i) {
				x[i] = i;
			} else {
				x[i] = x[i + 1];
			}
		}

		for (int d = 1; d <= m; d += 2) {
			if (m % d == 0) {
				int e = (d - 1) >> 1;
		
				if (e > 0) {
					e++; // times 2
				}
				
				subok(m / d, 1L << e, e * log[2], 3, "2^" + e, m / d);
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(min);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static void subok(int m, long prod, double lg, int f, String ff,
			int max) {
		if (lg > mlg) {
			return;
		}

		if (m == 1) { // m is eradicated
			min = prod;
			mlg = lg;
		} else {
			for (int d = 3; d <= max; d += 2) {
				if (m % d == 0) {
					long p = 1;
					int e = (d - 1) >> 1;

					for (int i = 0; i < e; i++) {
						p *= f;
					}

					subok(m / d, prod * p, lg + e * log[f], x[f + 1], ff + " "
							+ f + "^" + e, d);
				}
			}
		}
	}
}
