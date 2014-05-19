package Solved_176_200;

/**
 * Squarefree Numbers
 * Problem 193
 * 
 * A positive integer n is called squarefree, if no square of a prime divides n,
 * thus 1, 2, 3, 5, 6, 7, 10, 11 are squarefree, but not 4, 8, 9, 12.
 * 
 * How many squarefree numbers are there below 250?
 */
public class PE193_Squarefree_Numbers {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long result = 0;
		byte[] mobius = new byte[1 << 25];
		mobius[1] = 1;

		for (int p = 2; p < mobius.length; ++p) {
			if (mobius[p] == 0) {
				for (int i = p; i < mobius.length; i += p) {
					mobius[i] = (byte) -(mobius[i] | 1);
				}
			}
		}

		for (int p = 2, p2 = p * p; p2 < mobius.length; ++p, p2 = p * p) {
			if (mobius[p] != 0) {
				for (int i = p2; i < mobius.length; i += p2) {
					mobius[i] = 0;
				}
			}
		}

		for (int i = 1; i < (1 << 25); ++i) {
			result += (1L << 50) / ((long) i * i) * mobius[i];
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
