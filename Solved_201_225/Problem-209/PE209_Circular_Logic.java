package Solved_201_225;

/**
 * Circular Logic
 * Problem 209
 * 
 * A k-input binary truth table is a map from k input bits (binary digits, 0
 * [false] or 1 [true]) to 1 output bit. For example, the 2-input binary truth
 * tables for the logical AND and XOR functions are:
 * x	y	x AND y			x	y	x XOR y
 * 0	0	0				0	0	0
 * 0	1	0				0	1	1
 * 1	0	0				1	0	1
 * 1	1	1				1	1	0
 * 
 * How many 6-input binary truth tables, tau, satisfy the formula tau(a, b, c, d, e,
 * f) AND tau(b, c, d, e, f, a XOR (b AND c)) = 0
 * 
 * for all 6-bit inputs (a, b, c, d, e, f)?
 */
public class PE209_Circular_Logic {
	private static long[] memo = new long[129];

	public static void main(String[] args) {
		long start = System.nanoTime();

		boolean[] seen = new boolean[64];
		int index = 0;
		long result = 1;

		while (index < 64) {
			while ((index < 64) && (seen[index] == true)) {
				index++;
			}

			if (index >= 64) {
				continue;
			}

			int first = index, n = 0;

			do {
				seen[index] = true;
				index = Perm(index);
				++n;
			} while (index != first);

			result *= Fib(2 * n) / Fib(n);
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static int Perm(int n) {
		int bit = n ^ ((n << 1) & (n << 2));
		bit = (bit >> 5) & 1;

		return ((n & 31) << 1) | bit;
	}

	private static long Fib(int n) {
		if ((n == 1) || (n == 2)) {
			return 1;
		}

		if (memo[n] == 0) {
			memo[n] = Fib(n - 1) + Fib(n - 2);
		}

		return memo[n];
	}
}
