package General;

/**
 * Sphere Packing
 * Problem 222
 * 
 * What is the length of the shortest pipe, of internal radius 50mm, that can
 * fully contain 21 balls of radii 30mm, 31mm, ..., 50mm?
 * 
 * Give your answer in micrometres (10^-6 m) rounded to the nearest integer.
 */
public class PE222_Sphere_Packing {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int m = 21;
		double[] set = new double[m];

		for (int i = 0; i < m; ++i) {
			set[i] = i + 30;
		}

		double[][] d = new double[m][m];

		for (int i = 0; i < m; ++i) {
			for (int i2 = 0; i2 < m; ++i2) {
				d[i][i2] = Math.sqrt(200 * (set[i] + set[i2]) - 10000);
			}
		}

		int v = 1 << m;
		double[][] best = new double[v][m];

		for (int i = 0; i < m; ++i) {
			best[1 << i][i] = set[i];
		}

		boolean[] vb = new boolean[m];

		for (int j = 0; j < v; ++j) {
			for (int i = 0; i < m; ++i) {
				vb[i] = (j & (1 << i)) != 0;
			}

			for (int i = 0; i < m; ++i) {
				if (vb[i]) {
					if (best[j][i] != 0) {
						continue;
					}

					vb[i] = false;
					int j2 = j & ~(1 << i);
					double bl = 10000;

					for (int i2 = 0; i2 < m; ++i2) {
						if (vb[i2]) {
							double l = best[j2][i2] + d[i2][i];

							if (l < bl) {
								bl = l;
							}
						}
					}

					best[j][i] = bl;
					vb[i] = true;
				}
			}
		}

		double bl = 10000;

		for (int i = 0; i < m; ++i) {
			double l = best[v - 1][i] + set[i];

			if (l < bl) {
				bl = l;
			}
		}

		int result = (int) (bl * 1000);

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
