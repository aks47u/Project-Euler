package Solved_176_200;

/**
 * The hyperexponentiation of a number
 * Problem 188
 * 
 * The hyperexponentiation or tetration of a number a by a positive integer b,
 * denoted by a^^b or ba, is recursively defined by:
 * 
 * a^^1 = a, a^^(k+1) = a(a^^k).
 * 
 * Thus we have e.g. 3^^2 = 3^3 = 27, hence 3^^3 = 3^27 = 7625597484987 and 3^^4
 * is roughly 10^3.6383346400240996*10^12.
 * 
 * Find the last 8 digits of 1777^^1855.
 */
public class PE188_The_hyperexponentiation_of_a_number {
	private static final long N = 1777;
	private static final long POW = 1855;
	private static final long MOD = 100000000;

	public static void main(String[] args) {
		long start = System.nanoTime();

		long result = hexp(N, POW, MOD);

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long cycleSize(long n, long m) {
		long num = n;
		long i = 1;

		for (; num != 1L; i++) {
			num *= n;
			num %= m;
		}

		return i;
	}

	private static long hexp(long n, long hexp, long mod) {
		if (hexp == 1) {
			return n % mod;
		}

		long mod1 = cycleSize(n, mod);
		long exp = hexp(n, hexp - 1, mod1);
		long num = 1;

		for (long i = 0; i < exp; i++) {
			num *= n;
			num %= mod;
		}

		return num;
	}
}
