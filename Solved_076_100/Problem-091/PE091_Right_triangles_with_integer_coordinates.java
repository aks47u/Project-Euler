package Solved_076_100;

/**
 * Right triangles with integer coordinates
 * Problem 91
 * 
 * The points P (x1, y1) and Q (x2, y2) are plotted at integer co-ordinates and
 * are joined to the origin, O(0,0), to form TriangleOPQ.
 * 
 * There are exactly fourteen triangles containing a right angle that can be
 * formed when each co-ordinate lies between 0 and 2 inclusive; that is, 0 <= x1,
 * y1, x2, y2 <= 2.
 * 
 * Given that 0 <= x1, y1, x2, y2 <= 50, how many right triangles can be formed?
 */
public class PE091_Right_triangles_with_integer_coordinates {
	private static final int max = 50;

	public static void main(String[] args) {
		long start = System.nanoTime();

		int result = max * max;

		for (int i = 0; i <= max; i++) {
			for (int j = 0; j <= max; j++) {
				for (int k = 0; k <= max; k++) {
					for (int l = 0; l <= max; l++) {
						if (i * l - k * j != 0
								&& i * (k - i) + j * (l - j) == 0) {
							result++;
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
}
