package Solved_151_175;

/**
 * Investigating Ulam sequences
 * Problem 167
 * 
 * For two positive integers a and b, the Ulam sequence U(a,b) is defined by
 * U(a,b)1 = a, U(a,b)2 = b and for k > 2, U(a,b)k is the smallest integer
 * greater than U(a,b)(k-1) which can be written in exactly one way as the sum
 * of two distinct previous members of U(a,b).
 * 
 * For example, the sequence U(1,2) begins with 1, 2, 3 = 1 + 2, 4 = 1 + 3, 6 =
 * 2 + 4, 8 = 2 + 6, 11 = 3 + 8; 5 does not belong to it because 5 = 1 + 4 = 2 +
 * 3 has two representations as the sum of two previous members, likewise 7 = 1
 * + 6 = 3 + 4.
 * 
 * Find SUMU(2,2n+1)k for 2 <= n <= 10, where k = 10^11.
 */
public class PE167_Investigating_Ulam_sequences {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long result = 0;

		for (int n = 2; n <= 10; n++) {
			long u = calcUlam(2 * n + 1, 100000000000L);
			result += u;
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long calcUlam(int b, long k) {
		int len = 5000, period = 0, startPeriod = 1, startPos = 0;
		int[] v = null;

		while (period <= 0) {
			v = new int[len];
			startPos = ulam(b, v);
			period = findPeriod(v, startPos, startPeriod);
			len *= 4;
			startPeriod = (v.length - startPos - 1) / 2;
		}

		long dist = k - startPos - 1;

		return (dist / period) * (v[startPos + period] - v[startPos])
				+ v[startPos + (int) (dist % period)];
	}

	private static int ulam(int b, int[] list) {
		list[0] = 2;
		list[1] = b;
		int p = 2, testVal = b + 1, secondEven = -1, length = list.length, posAfter2ndEven = 0;
		boolean[] used = new boolean[length * 10];
		used[2] = used[b] = true;

		while (true) {
			int hits = 0;

			if (secondEven < 0) {
				for (int i = 0, x = list[0], y = testVal - x; x < y; i++, x = list[i], y = testVal
						- x) {
					if (used[y]) {
						hits++;
					}
				}
			} else {
				if (used[testVal - 2]) {
					hits++;

				}

				if (used[testVal - secondEven]) {
					hits++;
				}
			}

			if (hits == 1) {
				list[p++] = testVal;
				used[testVal] = true;

				if ((testVal & 1) == 0 && secondEven < 0) {
					secondEven = testVal--;
					posAfter2ndEven = p;
				}

				if (p == length) {
					return posAfter2ndEven;
				}
			}

			testVal += secondEven > 0 ? 2 : 1;
		}
	}

	private static int findPeriod(int[] list, int startPos, int startPeriod) {
		int[] diffs = new int[list.length - startPos - 1];

		for (int i = startPos; i < list.length - 1; i++) {
			diffs[i - startPos] = list[i + 1] - list[i];
		}

		for (int p = startPeriod; p < diffs.length / 2; p++) {
			int x;

			for (x = p; x < diffs.length; x++) {
				if (diffs[x] != diffs[x % p]) {
					break;
				}
			}

			if (x == diffs.length) {
				return p;
			}
		}

		return -1;
	}
}
