package Solved_176_200;

/**
 * Integer angled Quadrilaterals
 * Problem 177
 * 
 * Let ABCD be a convex quadrilateral, with diagonals AC and BD. At each vertex
 * the diagonal makes an angle with each of the two sides, creating eight corner
 * angles.
 * 
 * For example, at vertex A, the two angles are CAD, CAB.
 * 
 * We call such a quadrilateral for which all eight corner angles have integer
 * values when measured in degrees an "integer angled quadrilateral". An example
 * of an integer angled quadrilateral is a square, where all eight corner angles
 * are 45°. Another example is given by DAC = 20°, BAC = 60°, ABD = 50°, CBD =
 * 30°, BCA = 40°, DCA = 30°, CDB = 80°, ADB = 50°.
 * 
 * What is the total number of non-similar integer angled quadrilaterals?
 * 
 * Note: In your calculations you may assume that a calculated angle is integral
 * if it is within a tolerance of 10^-9 of an integer value.
 */
public class PE177_Integer_angled_Quadrilaterals {
	private static double[] sintable;
	private static double[][] tantable;
	private static double tolerance;
	private static int lastArcTan;

	public static void main(String[] args) {
		long start = System.nanoTime();

		tolerance = 0.000000001;
		lastArcTan = 0;
		initTrigTables();
		int result = 0;

		for (int p = 2; p <= 90; p++) {
			for (int q = 1; q < (180 - p); q++) {
				for (int r = 1; r < p; r++) {
					for (int s = 1; s < p; s++) {
						int t = calcT(p, q, r, s);

						if (t > Integer.MIN_VALUE) {
							if (firstInstance(p, q, r, s, t)) {
								result++;
							}
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

	private static int calcT(int p, int q, int r, int s) {
		double denom = quickSine(q + r) * quickSine(p - s);
		denom = denom / (quickSine(q) * quickSine(p - r));
		denom -= quickCos(s);

		if (denom == 0) {
			return 90;
		}

		double tanT = quickSine(s) / denom;
		int t = quickArcTan(tanT);

		return t;
	}

	private static boolean firstInstance(int p, int q, int r, int s, int t) {
		int rows = (p == 90) ? 7 : 3;
		int[][] tr = new int[rows][3];
		tr[0][0] = 180 - p - t;
		tr[0][1] = p - s;
		tr[0][2] = p - r;
		tr[1][0] = t;
		tr[1][1] = p - r;
		tr[1][2] = p - s;
		tr[2][0] = 180 - p - q;
		tr[2][1] = s;
		tr[2][2] = r;

		if (p == 90) {
			tr[3][0] = p - r;
			tr[3][1] = t;
			tr[3][2] = q;
			tr[4][0] = r;
			tr[4][1] = 180 - p - q;
			tr[4][2] = 180 - p - t;
			tr[5][0] = r;
			tr[5][1] = q;
			tr[5][2] = t;
			tr[6][0] = p - s;
			tr[6][1] = 180 - p - t;
			tr[6][2] = 180 - p - q;
		}

		for (int n = 0; n < tr.length; n++) {
			if (tr[n][0] < q) {
				return false;
			}

			if (tr[n][0] > q) {
				continue;
			}

			if (tr[n][1] < r) {
				return false;
			}

			if (tr[n][1] > r) {
				continue;
			}

			if (tr[n][2] < s) {
				return false;
			}

			if (tr[n][2] > s) {
				continue;
			}
		}

		return true;
	}

	private static void initTrigTables() {
		sintable = new double[91];

		for (int n = 0; n <= 90; n++) {
			sintable[n] = Math.sin(Math.toRadians(n));
		}

		tantable = new double[90][2];

		for (int n = 0; n < 90; n++) {
			tantable[n][0] = Math.tan(Math.toRadians((double) n - tolerance));
			tantable[n][1] = Math.tan(Math.toRadians((double) n + tolerance));
		}
	}

	private static double quickSine(int angleDeg) {
		if (angleDeg < 0) {
			return -quickSine(-angleDeg);
		}

		if (angleDeg > 90) {
			angleDeg = 180 - angleDeg;
		}

		return sintable[angleDeg];
	}

	private static double quickCos(int angleDeg) {
		if (angleDeg < 0) {
			return quickCos(-angleDeg);
		}

		if (angleDeg > 90) {
			return -(quickCos(180 - angleDeg));
		}

		return sintable[90 - angleDeg];
	}

	private static int quickArcTan(double tanval) {
		if (tanval < 0) {
			int val = quickArcTan(-tanval);

			if (val == Integer.MIN_VALUE) {
				return val;
			}

			return (180 - val);
		}

		if ((tantable[lastArcTan][0] < tanval)
				&& (tantable[lastArcTan][1] > tanval)) {
			return lastArcTan;
		}

		boolean forwards = (tantable[lastArcTan][1] <= tanval);

		if (forwards) {
			for (int n = lastArcTan + 1; n < 90; n++) {
				if ((tantable[n][0] < tanval) &&
						(tantable[n][1] > tanval)) {
					lastArcTan = n;
					return n;
				}

				if (tantable[n][1] > tanval) {
					lastArcTan = n;
					break;
				}
			}

			return Integer.MIN_VALUE;
		} else {
			for (int n = lastArcTan - 1; n >= 0; n--) {
				if ((tantable[n][0] < tanval) &&
						(tantable[n][1] > tanval)) {
					lastArcTan = n;
					return n;
				}

				if (tantable[n][0] < tanval) {
					lastArcTan = n;
					break;
				}
			}

			return Integer.MIN_VALUE;
		}
	}
}
