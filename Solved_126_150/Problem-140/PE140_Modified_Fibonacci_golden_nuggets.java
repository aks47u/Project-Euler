package Solved_126_150;

/**
 * Modified Fibonacci golden nuggets
 * Problem 140
 * 
 * Consider the infinite polynomial series AG(x) = xG1 + x2G2 + x3G3 + ...,
 * where Gk is the kth term of the second order recurrence relation Gk = Gk-1 +
 * Gk-2, G1 = 1 and G2 = 4; that is, 1, 4, 5, 9, 14, 23, ... .
 * 
 * For this problem we shall be concerned with values of x for which AG(x) is a
 * positive integer.
 * 
 * The corresponding values of x for the first five natural numbers are shown
 * below.
 * x				AG(x)
 * (sqrt5-1)/4		1
 * 2/5				2
 * (sqrt22-2)/6		3
 * (sqrt137-5)/14	4
 * 1/2				5
 * 
 * We shall call AG(x) a golden nugget if x is rational, because they become
 * increasingly rarer; for example, the 20th golden nugget is 211345365.
 * 
 * Find the sum of the first thirty golden nuggets.
 */
public class PE140_Modified_Fibonacci_golden_nuggets {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long g, t, n;
		long[] p = { 7, 8, 13, 17, 32, 43 };
		long[] q = { 1, 2, 5, 7, 14, 19 };
		long[] r = { 9, 9, 9, 9, 9, 9 };
		long[] s = { 4, 4, 4, 4, 4, 4 };

		int count = 5;
		long result = 2 + 5 + 21 + 42 + 152;
		boolean b = true;

		while (b) {
			for (int k = 0; k < 6; k++) {
				g = r[k];
				r[k] = 9 * r[k] + 20 * s[k];
				s[k] = 9 * s[k] + 4 * g;
				t = p[k] * r[k] + 5 * q[k] * s[k];

				if (t % 5 == 2) {
					n = (t - 7) / 5;
					result += n;
					count++;

					if (count == 30) {
						b = false;
						break;
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
}
