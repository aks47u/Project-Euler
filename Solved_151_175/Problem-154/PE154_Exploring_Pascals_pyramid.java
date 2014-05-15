package Solved_151_175;

/**
 * Exploring Pascal's pyramid
 * Problem 154
 * 
 * A triangular pyramid is constructed using spherical balls so that each ball
 * rests on exactly three balls of the next lower level.
 * 
 * Then, we calculate the number of paths leading from the apex to each
 * position:
 * 
 * A path starts at the apex and progresses downwards to any of the three
 * spheres directly below the current position.
 * 
 * Consequently, the number of paths to reach a certain position is the sum of
 * the numbers immediately above it (depending on the position, there are up to
 * three numbers above it).
 * 
 * The result is Pascal's pyramid and the numbers at each level n are the
 * coefficients of the trinomial expansion (x + y + z)^n.
 * 
 * How many coefficients in the expansion of (x + y + z)^200000 are multiples of
 * 10^12?
 */
public class PE154_Exploring_Pascals_pyramid {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int lim = 200000;
		int v = 12;
		int[] c2 = new int[lim + 1];
		int[] c5 = new int[lim + 1];

		for (int i = 0; i <= lim; i++) {
			c2[i] = c2[i >> 1] + (i >> 1);
			c5[i] = c5[i / 5] + i / 5;
		}

		long result = 0;
		int t2 = c2[lim] - v;
		int t5 = c5[lim] - v;
		int L3 = lim / 3;

		for (int i = 0; i <= L3; i++) {
			int u2 = t2 - c2[i];
			int u5 = t5 - c5[i];
			int L2 = (lim - i) >> 1;

			for (int j = i; j <= L2; j++) {
				int c = lim - i - j;

				if (u5 >= c5[j] + c5[c] && u2 >= c2[j] + c2[c]) {
					result += i == j ? (j == c ? 1 : 3) : j == c ? 3 : 6;
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
