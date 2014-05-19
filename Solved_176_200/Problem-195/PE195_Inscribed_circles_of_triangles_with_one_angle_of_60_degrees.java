package Solved_176_200;

/**
 * Inscribed circles of triangles with one angle of 60 degrees
 * Problem 195
 * 
 * Let's call an integer sided triangle with exactly one angle of 60 degrees a
 * 60-degree triangle. Let r be the radius of the inscribed circle of such a
 * 60-degree triangle.
 * 
 * There are 1234 60-degree triangles for which r <= 100. Let T(n) be the number
 * of 60-degree triangles for which r <= n, so T(100) = 1234, T(1000) = 22767,
 * and T(10000) = 359912.
 * 
 * Find T(1053779).
 */
public class PE195_Inscribed_circles_of_triangles_with_one_angle_of_60_degrees {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long maxr = 1053779;
		long maxr1 = (long) (maxr * 2 / Math.sqrt(3.));
		long maxr2 = (long) (maxr * 6 / Math.sqrt(3.));
		long result = 0;

		for (long q = 1; q * q <= maxr1; q++) {
			for (long p = q + 1; q * p <= maxr1; p++) {
				if ((p - q) % 3 != 0 && gcd(p, q) == 1) {
					result += maxr1 / (p * q);
				}
			}
		}

		for (long q = 1; q * q <= maxr2; q++) {
			for (long p = q + 3; p * q <= maxr2; p += 3) {
				if (gcd(p, q) == 1) {
					result += maxr2 / (p * q);
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
		return (a % b == 0) ? b : gcd(b, a % b);
	}
}
