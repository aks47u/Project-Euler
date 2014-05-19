package Solved_201_225;

import java.util.HashSet;

/**
 * Squarefree Binomial Coefficients
 * Problem 203
 * 
 * The binomial coefficients nCk can be arranged in triangular form, Pascal's
 * triangle, like this:
 *         1
 *        1 1
 *       1 2 1
 *      1 3 3 1
 *     1 4 6 4 1
 *   1 5 10 10 5 1
 *  1 6 15 20 15 6 1
 * 1 7 21 35 35 21 7 1
 * 		.........
 * 
 * It can be seen that the first eight rows of Pascal's triangle contain twelve
 * distinct numbers: 1, 2, 3, 4, 5, 6, 7, 10, 15, 20, 21 and 35.
 * 
 * A positive integer n is called squarefree if no square of a prime divides n.
 * Of the twelve distinct numbers in the first eight rows of Pascal's triangle,
 * all except 4 and 20 are squarefree. The sum of the distinct squarefree
 * numbers in the first eight rows is 105.
 * 
 * Find the sum of the distinct squarefree numbers in the first 51 rows of
 * Pascal's triangle.
 */
public class PE203_Squarefree_Binomial_Coefficients {
	private static int lastLine = 51;
	private static long[] squareOfPrimes = { 4, 9, 25, 49, 121, 169, 289, 361,
		529, 841, 961, 1369, 1681, 1849, 2209 };

	public static void main(String[] args) {
		long start = System.nanoTime();

		HashSet<Long> values = new HashSet<Long>();
		long[] line = { 1, 2, 1 };

		while (line.length <= lastLine) {
			for (int i = 0; i < line.length; i++) {
				values.add(line[i]);
			}

			line = next(line);
		}

		long result = 0;

		for (long x : values) {
			if (isSquarefree(x)) {
				result += x;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static boolean isSquarefree(long x) {
		for (int i = 0; i < squareOfPrimes.length; i++) {
			if (squareOfPrimes[i] > x) {
				return true;
			}

			if (x % squareOfPrimes[i] == 0) {
				return false;
			}
		}

		return true;
	}

	private static long[] next(long[] line) {
		long[] newLine = new long[line.length + 1];
		newLine[0] = newLine[line.length] = 1;

		for (int i = 1; i < newLine.length - 1; i++) {
			newLine[i] = line[i] + line[i - 1];
		}

		return newLine;
	}
}
