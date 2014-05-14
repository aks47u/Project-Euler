package Solved_126_150;

/**
 * abc-hits
 * Problem 127
 * 
 * The radical of n, rad(n), is the product of distinct prime factors of n. For
 * example, 504 = 2^3 × 3^2 × 7, so rad(504) = 2 × 3 × 7 = 42.
 * 
 * We shall define the triplet of positive integers (a, b, c) to be an abc-hit
 * if:
 * 
 * 1.	GCD(a, b) = GCD(a, c) = GCD(b, c) = 1
 * 2.	a < b
 * 3.	a + b = c
 * 4.	rad(abc) < c
 * 
 * For example, (5, 27, 32) is an abc-hit, because:
 * 
 * 1.	GCD(5, 27) = GCD(5, 32) = GCD(27, 32) = 1
 * 2.	5 < 27
 * 3.	5 + 27 = 32
 * 4.	rad(4320) = 30 < 32
 * 
 * It turns out that abc-hits are quite rare and there are only thirty-one
 * abc-hits for c < 1000, with SUMc = 12523.
 * 
 * Find SUMc for c < 120000.
 */
public class PE127_abc_hits {
	private static boolean[] notprime;
	private static int[] eadj, eprev, elast, radList, rad;
	private static int eidx, ridx, lim = 120000;

	public static void main(String[] args) {
		long start = System.nanoTime();

		init();
		int result = 0;

		for (int c = 1; c < lim; c++) {
			for (int i = 0; i < ridx
					&& ((c % 2 == 0) ? 3 : 2) * radList[i] * rad[c] < c; i++) {
				int n = radList[i] * rad[c];

				if (rad[n] != n) {
					continue;
				}

				for (int e = elast[radList[i]]; e != -1; e = eprev[e]) {
					int j = eadj[e];

					if (2 * j > c) {
						break;
					}

					if ((long) rad[c - j] * n < c) {
						result += c;
					}
				}
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static void init() {
		rad = new int[lim + 1];
		notprime = new boolean[lim + 1];

		for (int i = 1; i <= lim; i++) {
			rad[i] = 1;
		}

		for (int i = 2; i <= lim; i++) {
			if (notprime[i]) {
				continue;
			}

			for (int j = i; j < rad.length; j += i) {
				notprime[j] = true;
				rad[j] *= i;
			}
		}

		int lim_2 = lim >> 1;
			eadj = new int[lim_2 + 1];
			eprev = new int[lim_2 + 1];
			elast = new int[lim_2 + 1];
			eidx = 0;

			for (int i = lim_2; i >= 1; i--) {
				addEdge(rad[i], i);
			}

			radList = new int[lim_2 + 1];
			ridx = 0;

			for (int i = 1; i < lim_2; i++) {
				if (elast[i] != 0) {
					radList[ridx++] = rad[i];
				}
			}
	}

	private static void addEdge(int a, int b) {
		eadj[eidx] = b;
		eprev[eidx] = elast[a];
		elast[a] = eidx++;
	}
}
