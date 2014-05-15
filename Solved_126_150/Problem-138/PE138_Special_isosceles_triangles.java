package Solved_126_150;

/**
 * Special isosceles triangles
 * Problem 138
 * 
 * Consider the isosceles triangle with base length, b = 16, and legs, L = 17.
 * 
 * By using the Pythagorean theorem it can be seen that the height of the
 * triangle, h = sqrt(172 - 82) = 15, which is one less than the base length.
 * 
 * With b = 272 and L = 305, we get h = 273, which is one more than the base
 * length, and this is the second smallest isosceles triangle with the property
 * that h = b ± 1.
 * 
 * Find SUM L for the twelve smallest isosceles triangles for which h = b ± 1 and
 * b, L are positive integers.
 */
public class PE138_Special_isosceles_triangles {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long[] a = new long[27];
		a[0] = 0;
		a[1] = 1;
		long result = 0;

		for (int i = 2; i < a.length; i++) {
			a[i] = 4 * a[i - 1] + a[i - 2];
			
			if (i % 2 != 0) {
				result += +a[i];
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
