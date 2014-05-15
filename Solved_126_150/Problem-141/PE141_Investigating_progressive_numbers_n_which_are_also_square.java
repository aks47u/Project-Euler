package Solved_126_150;

/**
 * Investigating progressive numbers, n, which are also square
 * Problem 141
 * 
 * A positive integer, n, is divided by d and the quotient and remainder are q
 * and r respectively. In addition d, q, and r are consecutive positive integer
 * terms in a geometric sequence, but not necessarily in that order.
 * 
 * For example, 58 divided by 6 has quotient 9 and remainder 4. It can also be
 * seen that 4, 6, 9 are consecutive terms in a geometric sequence (common ratio
 * 3/2). We will call such numbers, n, progressive.
 * 
 * Some progressive numbers, such as 9 and 10404 = 102^2, happen to also be
 * perfect squares. The sum of all progressive perfect squares below one hundred
 * thousand is 124657.
 * 
 * Find the sum of all progressive perfect squares below one trillion (10^12).
 */
public class PE141_Investigating_progressive_numbers_n_which_are_also_square {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long result = 0;
		long max = (long) 1e12;
		long stop = (long) 1e4;

		for (long i = 2; i < stop; i++) {
			for (long j = 1; j < i; j++) {
				if (i * i * i * j + j * j >= max) {
					break;
				}

				if (gcd(i, j) > 1) {
					continue;
				}

				for (long c = 1;; c++) {
					long m2 = c * c * i * i * i * j + c * j * j;

					if (m2 >= max) {
						break;
					}

					if (isSquare(m2)) {
						result += m2;
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

	private static long gcd(long a, long b) {
		if (a < b) {
			return gcd(b, a);
		}

		return (a % b == 0) ? b : gcd(b, a % b);
	}

	private static boolean isSquare(long n) {
		long root = (long) Math.sqrt(n);

		return (root * root == n);
	}
}
