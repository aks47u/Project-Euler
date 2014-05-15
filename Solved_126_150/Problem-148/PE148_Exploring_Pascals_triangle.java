package Solved_126_150;

/**
 * Exploring Pascal's triangle
 * Problem 148
 * 
 * We can easily verify that none of the entries in the first seven rows of
 * Pascal's triangle are divisible by 7:
 *        1
 *       1 1
 *      1 2 1
 *     1 3 3 1
 *    1 4 6 4 1
 *  1 5 10 10 5 1
 * 1 6 15 20 15 6 1
 * 
 * However, if we check the first one hundred rows, we will find that only 2361
 * of the 5050 entries are not divisible by 7.
 * 
 * Find the number of entries which are not divisible by 7 in the first one
 * billion (10^9) rows of Pascal's triangle.
 */
public class PE148_Exploring_Pascals_triangle {
	private static long[][] units;

	public static void main(String[] args) {
		long start = System.nanoTime();
		
		units = new long[13][8];
		
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j <= 7; j++) {
				if (i == 0) {
					units[i][j] = (0 + j) * (j + 1) / 2;
				} else {
					units[i][j] = (0 + j) * (j + 1) / 2 * units[i - 1][7];
				}
			}
		}
		
		System.out.println(count(1000000000L));
		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long count(long numLines) {
		long count = 0;
		int index = 0;
		
		while (numLines > 0) {
			int mod = (int) (numLines % 7);
			count = count * (mod + 1) + units[index][mod];
			numLines = numLines / 7;
			index++;
		}
		
		return count;
	}
}
