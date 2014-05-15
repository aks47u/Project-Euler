package Solved_126_150;

/**
 * Rectangles in cross-hatched grids
 * Problem 147
 * 
 * In a 3x2 cross-hatched grid, a total of 37 different rectangles could be
 * situated within that grid as indicated in the sketch.
 * 
 * There are 5 grids smaller than 3x2, vertical and horizontal dimensions being
 * important, i.e. 1x1, 2x1, 3x1, 1x2 and 2x2. If each of them is cross-hatched,
 * the following number of different rectangles could be situated within those
 * smaller grids:
 * 
 * 1x1: 1 2x1: 4 3x1: 8 1x2: 4 2x2: 18
 * 
 * Adding those to the 37 of the 3x2 grid, a total of 72 different rectangles
 * could be situated within 3x2 and smaller grids.
 * 
 * How many different rectangles could be situated within 47x43 and smaller
 * grids?
 */
public class PE147_Rectangles_in_cross_hatched_grids {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long m, n, x, y, z, w, sub, result;
		result = 0;

		for (m = 1; m <= 43; m++) {
			for (n = 1; n <= 47; n++) {
				sub = 0;

				for (x = 1; x <= m; x++) {
					for (y = 1; y <= n; y++) {
						sub += (m - x + 1) * (n - y + 1);
					}
				}

				result += sub;
				sub = 0;

				for (x = 1; x <= 2 * m; x++) {
					for (y = 1; y <= 2 * n; y++) {
						z = (x + y - 1) / 2;
						w = x + y - 1 - z;

						if (z > m) {
							continue;
						}

						if (w > m) {
							continue;
						}

						if (z > n) {
							continue;
						}

						if (w > n) {
							continue;
						}

						if (((x | y) & 1) == 0) {
							{
								sub += ((m - z) * (n - z) + (m - w) * (n - w));
							}
						} else {
							sub += ((m - z) * (n - w) + (m - w) * (n - z));
						}
					}
				}

				result += sub;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
