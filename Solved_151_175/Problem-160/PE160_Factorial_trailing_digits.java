package Solved_151_175;

import java.math.BigInteger;

/**
 * Factorial trailing digits
 * Problem 160
 * 
 * For any N, let f(N) be the last five digits before the trailing zeroes in N!.
 * For example,
 * 
 * 9! = 362880 so f(9)=36288
 * 10! = 3628800 so f(10)=36288
 * 20! = 2432902008176640000 so f(20)=17664
 * 
 * Find f(1,000,000,000,000)
 */
public class PE160_Factorial_trailing_digits {
	private static final long N = 1000000000000L;
	private static final int MOD = 100000;

	public static void main(String[] args) {
		long start = System.nanoTime();

		long[] ds = new long[MOD];
		long toCancel = 0;

		for (long i = N / 5; i > 0; i /= 5) {
			toCancel += i;
		}

		for (long k = 1; k < N; k *= 5) {
			for (int i = 0; i < ds.length; ++i) {
				if (i % 5 != 0) {
					ds[i] += N / MOD / k;
				}
			}

			for (int i = 0; i <= (N / k) % MOD; ++i) {
				if (i % 5 != 0) {
					ds[i]++;
				}
			}
		}

		for (int i = ds.length - 1; i > 0 && toCancel != 0; --i) {
			if (i % 2 == 0) {
				long c2 = Math.min(toCancel, ds[i]);
				ds[i / 2] += c2;
				ds[i] -= c2;
				toCancel -= c2;
			}
		}

		BigInteger result = BigInteger.ONE;

		for (int i = 0; i < ds.length; ++i) {
			BigInteger temp = BigInteger.valueOf(i).modPow(
					BigInteger.valueOf(ds[i]), BigInteger.valueOf(MOD));
			result = result.multiply(temp).mod(BigInteger.valueOf(MOD));
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
