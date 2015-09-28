package Solved_126_150;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Investigating the Torricelli point of a triangle
 * Problem 143
 * 
 * Let ABC be a triangle with all interior angles being less than 120 degrees.
 * Let X be any point inside the triangle and let XA = p, XC = q, and XB = r.
 * 
 * Fermat challenged Torricelli to find the position of X such that p + q + r
 * was minimised.
 * 
 * Torricelli was able to prove that if equilateral triangles AOB, BNC and AMC
 * are constructed on each side of triangle ABC, the circumscribed circles of
 * AOB, BNC, and AMC will intersect at a single point, T, inside the triangle.
 * Moreover he proved that T, called the Torricelli/Fermat point, minimises p +
 * q + r. Even more remarkable, it can be shown that when the sum is minimised,
 * AN = BM = CO = p + q + r and that AN, BM and CO also intersect at T.
 * 
 * If the sum is minimised and a, b, c, p, q and r are all positive integers we
 * shall call triangle ABC a Torricelli triangle. For example, a = 399, b = 455,
 * c = 511 is an example of a Torricelli triangle, with p + q + r = 784.
 * 
 * Find the sum of all distinct values of p + q + r <= 120000 for Torricelli
 * triangles.
 */
public class PE143_Investigating_the_Torricelli_point_of_a_triangle {
	public static void main(String[] args) {
		long start = System.nanoTime();

		HashMap<Long, HashSet<Long>> map = new HashMap<Long, HashSet<Long>>();

		for (long m = 1; m < 119999; m++) {
			for (long n = 1; m * n < 119999
					&& (3 * m * m - n * n - 2 * m * n) > 0; n++) {
				if (mcd(m, n) == 1) {
					long p, q;

					for (int k = 1; m * n * k < 119999; k++) {
						p = m * n * k;
						q = ((3 * m * m - n * n - 2 * m * n) * k) / 4;

						if (p + q < 120000
								&& ((3 * m * m - n * n - 2 * m * n) * k) % 4 == 0) {
							if (map.containsKey(p)) {
								map.get(p).add(q);
							} else {
								HashSet<Long> newset = new HashSet<Long>();
								newset.add(q);
								map.put(p, newset);
							}

							if (map.containsKey(q)) {
								map.get(q).add(p);
							} else {
								HashSet<Long> newset = new HashSet<Long>();
								newset.add(p);
								map.put(q, newset);
							}
						}
					}
				}
			}
		}

		long[] arr = new long[120001];

		for (Map.Entry<Long, HashSet<Long>> entry : map.entrySet()) {
			long p = entry.getKey();

			for (long q : entry.getValue()) {
				HashSet<Long> commons = new HashSet<Long>(map.get(p));
				commons.retainAll(map.get(q));

				for (long r : commons) {
					if (p + q + r <= 120000) {
						arr[(int) (p + q + r)] = 1;
					}
				}
			}
		}

		long result = 0;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 1) {
				result += i;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long mcd(long x, long y) {
		while (y != 0) {
			long tmp = y;
			y = x % y;
			x = tmp;
		}

		return x;
	}
}
