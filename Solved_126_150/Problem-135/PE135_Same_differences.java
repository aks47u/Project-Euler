package Solved_126_150;

/**
 * Same differences
 * Problem 135
 * 
 * Given the positive integers, x, y, and z, are consecutive terms of an
 * arithmetic progression, the least value of the positive integer, n, for which
 * the equation, x^2 - y^2 - z^2 = n, has exactly two solutions is n = 27:
 * 
 * 34^2 - 27^2 - 20^2 = 12^2 - 9^2 - 6^2 = 27
 * 
 * It turns out that n = 1155 is the least value which has exactly ten
 * solutions.
 * 
 * How many values of n less than one million have exactly ten distinct
 * solutions?
 */
public class PE135_Same_differences {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long y = 2, r = 1, n, result = 0;
		int[] solutions = new int[1000000];

		do {
			do {
				n = getN(y, r);

				if ((n > 0) && (n < 1000000) && (y > r)) {
					solutions[(int) n]++;
				}

				r++;
			} while (n < 1000000);

			y++;
		} while ((getN(y, r = (y / 4)) < 1000000) && (y < 1000000));

		for (int i = 0; i < solutions.length; i++) {
			if (solutions[i] == 10) {
				result++;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long getN(long y, long r) {
		return (4 * r * y) - (y * y);
	}
}
