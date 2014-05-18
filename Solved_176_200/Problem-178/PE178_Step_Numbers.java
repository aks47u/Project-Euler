package Solved_176_200;

import java.util.Arrays;

/**
 * Step Numbers
 * Problem 178
 * 
 * Consider the number 45656. It can be seen that each pair of consecutive
 * digits of 45656 has a difference of one. A number for which every pair of
 * consecutive digits has a difference of one is called a step number. A
 * pandigital number contains every decimal digit from 0 to 9 at least once. How
 * many pandigital step numbers less than 10^40 are there?
 */
public class PE178_Step_Numbers {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int limit = 40;
		long[][][][] a = new long[limit][2][2][10]; // 10 final digits
		Arrays.fill(a[0][0][0], 1); // without 0 without 9
		Arrays.fill(a[0][0][1], 0); // without 0 with 9
		Arrays.fill(a[0][1][0], 0); // with 0 without 9
		Arrays.fill(a[0][1][1], 0); // with 0 with 9
		a[0][0][0][0] = 0;
		a[0][0][0][9] = 0;
		a[0][0][1][9] = 1;

		for (int n = 1; n < limit; n++) {
			for (int digit = 0; digit < 10; digit++) {
				if (digit == 0) {
					a[n][1][0][digit + 1] += a[n - 1][1][0][digit];
					a[n][1][1][digit + 1] += a[n - 1][1][1][digit];
				} else if (digit == 1) {
					a[n][1][0][digit - 1] += a[n - 1][0][0][digit];
					a[n][0][0][digit + 1] += a[n - 1][0][0][digit];
					a[n][1][1][digit - 1] += a[n - 1][0][1][digit];
					a[n][0][1][digit + 1] += a[n - 1][0][1][digit];
					a[n][1][0][digit - 1] += a[n - 1][1][0][digit];
					a[n][1][0][digit + 1] += a[n - 1][1][0][digit];
					a[n][1][1][digit - 1] += a[n - 1][1][1][digit];
					a[n][1][1][digit + 1] += a[n - 1][1][1][digit];
				} else if (digit == 8) {
					a[n][0][0][digit - 1] += a[n - 1][0][0][digit];
					a[n][0][1][digit + 1] += a[n - 1][0][0][digit];
					a[n][1][0][digit - 1] += a[n - 1][1][0][digit];
					a[n][1][1][digit + 1] += a[n - 1][1][0][digit];
					a[n][0][1][digit - 1] += a[n - 1][0][1][digit];
					a[n][0][1][digit + 1] += a[n - 1][0][1][digit];
					a[n][1][1][digit - 1] += a[n - 1][1][1][digit];
					a[n][1][1][digit + 1] += a[n - 1][1][1][digit];
				} else if (digit == 9) {
					a[n][0][1][digit - 1] += a[n - 1][0][1][digit];
					a[n][1][1][digit - 1] += a[n - 1][1][1][digit];
				} else {
					a[n][0][0][digit - 1] += a[n - 1][0][0][digit];
					a[n][0][0][digit + 1] += a[n - 1][0][0][digit];
					a[n][0][1][digit - 1] += a[n - 1][0][1][digit];
					a[n][0][1][digit + 1] += a[n - 1][0][1][digit];
					a[n][1][0][digit - 1] += a[n - 1][1][0][digit];
					a[n][1][0][digit + 1] += a[n - 1][1][0][digit];
					a[n][1][1][digit - 1] += a[n - 1][1][1][digit];
					a[n][1][1][digit + 1] += a[n - 1][1][1][digit];
				}
			}
		}

		long result = 0;

		for (int n = 0; n < limit; n++) {
			for (int digit = 0; digit < 10; digit++) {
				result += a[n][1][1][digit];
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
