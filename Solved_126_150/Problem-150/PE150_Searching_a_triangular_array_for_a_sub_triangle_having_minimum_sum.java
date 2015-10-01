package Solved_126_150;

/**
 * Searching a triangular array for a sub-triangle having minimum-sum
 * Problem 150
 * 
 * In a triangular array of positive and negative integers, we wish to find a
 * sub-triangle such that the sum of the numbers it contains is the smallest
 * possible.
 * 
 * In the example below, it can be easily verified that the marked triangle
 * satisfies this condition having a sum of -42.
 * 
 * We wish to make such a triangular array with one thousand rows, so we
 * generate 500500 pseudo-random numbers sk in the range ±2^19, using a type of
 * random number generator (known as a Linear Congruential Generator) as
 * follows:
 * 
 * t := 0 for k = 1 up to k = 500500: t := (615949*t + 797807) modulo 2^20 sk :=
 * t-219
 * 
 * Thus: s1 = 273519, s2 = -153582, s3 = 450905 etc
 * 
 * Our triangular array is then formed using the pseudo-random numbers thus: s1
 * s2 s3 s4 s5 s6 s7 s8 s9 s10 ...
 * 
 * Sub-triangles can start at any element of the array and extend down as far as
 * we like (taking-in the two elements directly below it from the next row, the
 * three elements directly below from the row after that, and so on). The
 * "sum of a sub-triangle" is defined as the sum of all the elements it
 * contains. Find the smallest possible sub-triangle sum.
 */
public class PE150_Searching_a_triangular_array_for_a_sub_triangle_having_minimum_sum {
	private static long[] s;

	public static void main(String[] args) {
		long start = System.nanoTime();

		s = new long[500501];
		long pow219 = pow(2, 19);
		long pow220 = pow219 * 2;
		long t = 0;

		for (int k = 1; k <= 500500; k++) {
			t = (615949 * t + 797807) % pow220;
			s[k] = t - pow219;
		}

		long[][] sum = new long[1][1];
		sum[0][0] = s[1];
		long result = sum[0][0];

		for (int row = 2; row <= 1000; row++) {
			long[][] newSum = new long[row][row];

			for (int col1 = 0; col1 < row; col1++) {
				long lineSum = 0;

				for (int col2 = col1; col2 < row; col2++) {
					lineSum += get(row, col2);

					if (col1 == col2) {
						newSum[col1][col2] = lineSum;
					} else {
						newSum[col1][col2] = lineSum + sum[col1][col2 - 1];
					}

					if (newSum[col1][col2] < result) {
						result = newSum[col1][col2];
					}
				}
			}

			sum = newSum;
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long get(int row, int col) {
		return s[(row) * (row - 1) / 2 + col + 1];
	}

	private static long pow(long a, long b) {
		int result = 1;

		for (int i = 1; i <= b; i++) {
			result *= a;
		}

		return result;
	}
}
