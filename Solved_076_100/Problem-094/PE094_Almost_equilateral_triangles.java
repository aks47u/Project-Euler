package Solved_076_100;

/**
 * Almost equilateral triangles
 * Problem 94
 * 
 * It is easily proved that no equilateral triangle exists with integral length
 * sides and integral area. However, the almost equilateral triangle 5-5-6 has
 * an area of 12 square units.
 * 
 * We shall define an almost equilateral triangle to be a triangle for which two
 * sides are equal and the third differs by no more than one unit.
 * 
 * Find the sum of the perimeters of all almost equilateral triangles with
 * integral side lengths and area and whose perimeters do not exceed one billion
 * (1,000,000,000).
 */
public class PE094_Almost_equilateral_triangles {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int result = 0;

		for (int i = 3; i * i <= 333333334; i += 2) {
			for (int j = 1; 3 * (i * i + j * j) < 1000000010 && j < i; j += 2) {
				if (gcd(i, j) == 1) {
					int a = i * j;
					int b = (i * i - j * j) / 2;
					int lower = a > b ? b : a;
					int hypo = (i * i + j * j) / 2;

					if (lower * 2 == hypo - 1 || lower * 2 == hypo + 1
							|| lower * 2 == hypo) {
						if ((2 * lower + 2 * hypo) < 1000000000) {
							result += 2 * lower + 2 * hypo;
						}
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

	private static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
}
