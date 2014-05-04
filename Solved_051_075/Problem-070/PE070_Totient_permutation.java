package Solved_051_075;

import java.util.Arrays;

/**
 * Totient permutation
 * Problem 70
 * 
 * Euler's Totient function, phi(n) [sometimes called the phi function], is used
 * to determine the number of positive numbers less than or equal to n which are
 * relatively prime to n. For example, as 1, 2, 4, 5, 7, and 8, are all less
 * than nine and relatively prime to nine, phi(9)=6. The number 1 is considered to
 * be relatively prime to every positive number, so phi(1)=1.
 * 
 * Interestingly, phi(87109)=79180, and it can be seen that 87109 is a permutation
 * of 79180.
 * 
 * Find the value of n, 1 < n < 107, for which phi(n) is a permutation of n and
 * the ratio n/phi(n) produces a minimum.
 */
public class PE070_Totient_permutation {
	public static void main(String[] args) {
		long start = System.nanoTime();

		System.out.println(solve(10000000));

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	public static int solve(int n) {
		double[] phi = new double[n + 1];
		
		for (int i = 1; i <= n; ++i) {
			phi[i] = i;
		}

		for (int i = 2; i <= n; ++i) {
			if (phi[i] == i) {
				for (int j = i; j <= n; j += i) {
					phi[j] = phi[j] * (i - 1) / i;
				}
			}
		}

		double min = 79_180D;
		int k = 0;

		for (int i = 2; i <= n; ++i) {
			if (sort(i) == sort((int) phi[i]) && i / phi[i] < min) {
				min = i / phi[i];
				k = i;
			}
		}

		return k;
	}

	private static int sort(int n) {
		char[] c = Long.toString(n).toCharArray();
		Arrays.sort(c);
		
		return Integer.parseInt(new String(c));
	}
}
