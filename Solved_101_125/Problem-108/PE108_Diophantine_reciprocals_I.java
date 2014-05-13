package Solved_101_125;

/**
 * Diophantine reciprocals I
 * Problem 108
 * 
 * In the following equation x, y, and n are positive integers.
 * 1   1   1
 * - + - = -
 * x + y   n
 * 
 * For n = 4 there are exactly three distinct solutions:
 * 1    1   1
 * - + -- = -
 * 5 + 20   4
 * 
 * 1    1   1
 * - + -- = -
 * 6 + 12   4
 * 
 * 1   1   1
 * - + - = -
 * 8   8   4
 * 
 * What is the least value of n for which the number of distinct solutions
 * exceeds one-thousand?
 * 
 * NOTE: This problem is an easier version of problem 110; it is strongly
 * advised that you solve this one first.
 */
public class PE108_Diophantine_reciprocals_I {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long result = 2 * 3 * 5 * 7;

		while (true) {
			long sq = result * result;
			int count = 2;

			for (int i = 2; i < result; i++) {
				if (sq % i == 0) {
					count++;
				}
			}

			if (count > 1000) {
				break;
			}
			result += 2 * 3 * 5 * 7;
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
