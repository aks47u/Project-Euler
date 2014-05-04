package Solved_051_075;

/**
 * Cubic permutations
 * Problem 62
 * 
 * The cube, 41063625 (345^3), can be permuted to produce two other cubes:
 * 56623104 (384^3) and 66430125 (405^3). In fact, 41063625 is the smallest cube
 * which has exactly three permutations of its digits which are also cube.
 * 
 * Find the smallest cube for which exactly five permutations of its digits are
 * cube.
 */
public class PE062_Cubic_permutations {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long[][] cubes = new long[100000][11];

		for (long i = 0; i < 100000; i++) {
			cubes[(int) i][10] = i * i * i;

			for (long j = 0; j < 10; j++) {
				cubes[(int) i][(int) j] = occurencesInLong(cubes[(int) i][10],
						j);
			}
		}

		for (int i = 4642; i < 10000; i++) {
			int counter = 0;

			for (int j = i + 1; j < 10000; j++) {
				boolean isPermutation = true;

				for (int k = 0; k < 10; k++) {
					if (cubes[i][k] != cubes[j][k]) {
						isPermutation = false;
					}
				}

				if (isPermutation) {
					counter++;

					if (counter == 4) {
						long end = System.nanoTime();
						long runtime = end - start;
						System.out.println(cubes[i][10]);
						System.out.println("Runtime: " + runtime / 1000000
								+ "ms (" + runtime + "ns)");
						System.exit(0);
					}
				}
			}
		}
	}

	public static int occurencesInLong(long number, long digit) {
		String strNumber = String.valueOf(number);
		int count = 0;

		for (int i = 0; i < strNumber.length(); i++) {
			if (strNumber.charAt(i) - '0' == digit) {
				count++;
			}
		}

		return count;
	}
}
