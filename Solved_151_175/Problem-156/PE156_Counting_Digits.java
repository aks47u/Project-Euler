package Solved_151_175;

/**
 * Counting Digits
 * Problem 156
 * 
 * Starting from zero the natural numbers are written down in base 10 like this:
 * 0 1 2 3 4 5 6 7 8 9 10 11 12....
 * 
 * Consider the digit d=1. After we write down each number n, we will update the
 * number of ones that have occurred and call this number f(n,1). The first
 * values for f(n,1), then, are as follows:
 * n	f(n,1)
 * 0	0
 * 1	1
 * 2	1
 * 3	1
 * 4	1
 * 5	1
 * 6	1
 * 7	1
 * 8	1
 * 9	1
 * 10	2
 * 11	4
 * 12	5
 * 
 * Note that f(n,1) never equals 3. So the first two solutions of the equation
 * f(n,1)=n are n=0 and n=1. The next solution is n=199981.
 * 
 * In the same manner the function f(n,d) gives the total number of digits d
 * that have been written down after the number n has been written. In fact, for
 * every digit d != 0, 0 is the first solution of the equation f(n,d)=n.
 * 
 * Let s(d) be the sum of all the solutions for which f(n,d)=n. You are given
 * that s(1)=22786974071.
 * 
 * Find SUM s(d) for 1 <= d <= 9.
 * 
 * Note: if, for some n, f(n,d)=n for more than one value of d this value of n
 * is counted again for every value of d for which f(n,d)=n.
 */
public class PE156_Counting_Digits {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long[] sums = new long[11];
		int[] digits = new int[12];
		long[] totsums = new long[11];

		for (long n = 0; n < 99999999999L;) {
			long mindiff = Long.MAX_VALUE;

			for (int d = 1; d <= 9; d++) {
				long diff = Math.abs(sums[d] - n + 1);

				if (diff == 0) {
					totsums[d] += n - 1;
				}

				mindiff = Math.min(diff, mindiff);
			}

			if (mindiff > 1500000L && n % 1000000 == 0) {
				n = stepN(n, 1000000, 6, sums, digits);

			} else if (mindiff > 1500L && n % 1000 == 0) {
				n = stepN(n, 1000, 3, sums, digits);
			} else {
				n = stepN(n, 1, 0, sums, digits);
			}
		}

		long result = 0;

		for (int i = 1; i <= 9; i++) {
			result += totsums[i];
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long stepN(long n, int N, int logN, long[] sums, int[] digits) {
		for (int d = logN; d <= 11; d++) {
			sums[digits[d]] += N;
		}

		int add = logN * N / 10;

		if (add > 0) {
			for (int i = 1; i <= 9; i++) {
				sums[i] += add;
			}
		}

		n += N;

		for (int d = logN; d < 11; d++) {
			if (digits[d] == 9) {
				digits[d] = 0;
			} else {
				digits[d]++;
				break;
			}
		}

		return n;
	}
}
