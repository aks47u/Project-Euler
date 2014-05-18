package Solved_176_200;

/**
 * Maximising a weighted product
 * Problem 190
 * 
 * Let Sm = (x1, x2, ... , xm) be the m-tuple of positive real numbers with x1 +
 * x2 + ... + xm = m for which Pm = x1 * x2^2 * ... * xm^m is maximised.
 * 
 * For example, it can be verified that [P10] = 4112 ([ ] is the integer part
 * function).
 * 
 * Find SUM[Pm] for 2 <= m <= 15.
 */
public class PE190_Maximising_a_weighted_product {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int result = 0;

		for (int m = 2; m < 16; m++) {
			double[] x = new double[m];
			double[] test = new double[m];
			double product = 1.0;
			double test_product = 1.0;

			for (int i = 0; i < m; i++) {
				x[i] = 1.0;
				test[i] = 1.0;
			}

			for (int i = 0; i < m; i++) {
				product *= Math.pow(x[i], i + 1);
			}

			boolean hit = true;
			double val = 1.0;

			while (hit || val > 0.00001) {
				if (hit) {
					hit = false;
				} else {
					val *= 0.5;
				}

				int add = 0;
				int sub = 0;
				double max = product;

				for (int i = 0; i < m; i++) {
					for (int j = 0; j < m; j++) {
						if (i != j) {
							for (int k = 0; k < m; k++) {
								test[k] = x[k];
							}

							test_product = 1.0;
							test[i] += val;
							test[j] -= val;

							for (int k = 0; k < m; k++) {
								test_product *= Math.pow(test[k], k + 1);
							}

							if (test_product > max) {
								max = test_product;
								add = i;
								sub = j;
								hit = true;
							}
						}
					}
				}

				if (hit) {
					x[add] += val;
					x[sub] -= val;
					product = 1.0;

					for (int k = 0; k < m; k++) {
						product *= Math.pow(x[k], k + 1);
					}
				}
			}

			result += (int) product;
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
