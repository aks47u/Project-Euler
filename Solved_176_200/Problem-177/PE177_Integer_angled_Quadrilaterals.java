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
	private static double[] sintable; // sine look-up table
	private static double[][] tantable; // tan upper- and lower- value look-up
	// table
	private static double tolerance; // tolerance for integer angles
	private static int lastArcTan; // last arctan value

	public static void main(String[] args) {
		long start = System.nanoTime();

		tolerance = 0.000000001; // set tolerance
		lastArcTan = 0; // init lastArcTan found
		initTrigTables(); // set up look-up tables
		int result = 0; // init count

		for (int p = 2; p <= 90; p++) { // loop through angle p
			for (int q = 1; q < (180 - p); q++) { // loop through angle q
				for (int r = 1; r < p; r++) { // loop through angle r
					for (int s = 1; s < p; s++) { // loop through angle s
						int t = calcT(p, q, r, s); // calculate angle t

						if (t > Integer.MIN_VALUE) { // if integer angle found
							// if first instance of
							// this quadrilateral
							if (firstInstance(p, q, r, s, t)) {
								result++; // increment count
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

	/*
	 * Determines if these angles define an integer quadrilateral and returns
	 * the derived integer angle t in degrees, or if t is not an integer, flags
	 * with MIN_VALUE
	 */
	private static int calcT(int p, int q, int r, int s) {
		// build denominator
		double denom = quickSine(q + r) * quickSine(p - s);
		denom = denom / (quickSine(q) * quickSine(p - r));
		denom -= quickCos(s);

		// catch tan(T) == infinity (ie t == 90 degrees)
		if (denom == 0) {
			return 90;
		}

		double tanT = quickSine(s) / denom; // get tan(t)
		int t = quickArcTan(tanT); // get integer arctan, or MIN_VALUE

		return t; // return angle t or MIN_VALUE
	}

	/*
	 * Determines whether this angle configuration (p, q, r, s, t) is the first
	 * instance of its kind (i.e. no previous discoveries could be similar)
	 */
	private static boolean firstInstance(int p, int q, int r, int s, int t) {
		int rows = (p == 90) ? 7 : 3; // need more translations if p == 90
		int[][] tr = new int[rows][3]; // array of b, c, d values for
		// translations

		tr[0][0] = 180 - p - t; // q, r, s values if r180 CDAB
		tr[0][1] = p - s;
		tr[0][2] = p - r;
		tr[1][0] = t; // q, r, s values flipped BADC
		tr[1][1] = p - r;
		tr[1][2] = p - s;
		tr[2][0] = 180 - p - q; // q, r, s values fr180 DCBA
		tr[2][1] = s;
		tr[2][2] = r;

		if (p == 90) {
			tr[3][0] = p - r; // q, r, s values r90 BCDA
			tr[3][1] = t;
			tr[3][2] = q;
			tr[4][0] = r; // q, r, s values r270 DABC
			tr[4][1] = 180 - p - q;
			tr[4][2] = 180 - p - t;
			tr[5][0] = r; // q, r, s values fr90 ADCB
			tr[5][1] = q;
			tr[5][2] = t;
			tr[6][0] = p - s; // q, r, s values fr270 CBAD
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
		// build sine table (0-90 degrees)
		sintable = new double[91];

		for (int n = 0; n <= 90; n++) {
			sintable[n] = Math.sin(Math.toRadians(n));
		}

		// build tan table (0-89 degrees)
		// gives a min and max acceptable tan for integer angle
		tantable = new double[90][2];

		for (int n = 0; n < 90; n++) {
			tantable[n][0] = Math.tan(Math.toRadians((double) n - tolerance));
			tantable[n][1] = Math.tan(Math.toRadians((double) n + tolerance));
		}
	}

	/*
	 * Gets sine of the integer angle in degrees using look-up
	 */
	private static double quickSine(int angleDeg) {
		if (angleDeg < 0) {
			return -quickSine(-angleDeg);
		}

		if (angleDeg > 90) {
			angleDeg = 180 - angleDeg; // handle obtuse angles
		}

		return sintable[angleDeg]; // return sine
	}

	/*
	 * Gets cosine of the integer angle in degrees using look-up
	 */
	private static double quickCos(int angleDeg) {
		if (angleDeg < 0) {
			return quickCos(-angleDeg);
		}

		if (angleDeg > 90) {
			return -(quickCos(180 - angleDeg)); // handle obtuse
		}

		return sintable[90 - angleDeg]; // return cosine
	}

	/*
	 * Gets the integer angle in degrees corresponding to the specified tan
	 * value or returns Integer.MIN_VALUE if not an integer angle
	 */
	private static int quickArcTan(double tanval) {
		// handle negative tanval
		if (tanval < 0) {
			int val = quickArcTan(-tanval); // get positive value

			if (val == Integer.MIN_VALUE) {
				return val; // return if MIN_VALUE
			}

			return (180 - val); // return neg equivalent
		}

		// get first guess (using last approximation found)
		// return if correct
		if ((tantable[lastArcTan][0] < tanval)
				&& (tantable[lastArcTan][1] > tanval)) {
			return lastArcTan;
		}

		// else get search direction
		boolean forwards = (tantable[lastArcTan][1] <= tanval);

		if (forwards) { // search forwards from first guess
			for (int n = lastArcTan + 1; n < 90; n++) { // loop through look-up
				if ((tantable[n][0] < tanval) && // if in range
						(tantable[n][1] > tanval)) {
					lastArcTan = n; // save angle/index
					return n; // return angle
				}

				if (tantable[n][1] > tanval) { // if range exceeded
					lastArcTan = n; // save angle/index
					break; // quit loop
				}
			}

			// no matching integer angle found; flag with MIN_VALUE
			return Integer.MIN_VALUE;
		} else { // search backwards from first guess
			for (int n = lastArcTan - 1; n >= 0; n--) { // loop through look-up
				if ((tantable[n][0] < tanval) && // if in range
						(tantable[n][1] > tanval)) {
					lastArcTan = n; // save angle/index
					return n; // return angle
				}

				if (tantable[n][0] < tanval) { // if range exceeded
					lastArcTan = n; // save angle/index
					break; // quit loop
				}
			}

			// no matching integer angle found; flag with MIN_VALUE
			return Integer.MIN_VALUE;
		}
	}
}
