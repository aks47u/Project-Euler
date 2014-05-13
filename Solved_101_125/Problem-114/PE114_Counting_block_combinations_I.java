package Solved_101_125;

/**
 * Counting block combinations I
 * Problem 114
 * 
 * A row measuring seven units in length has red blocks with a minimum length of
 * three units placed on it, such that any two red blocks (which are allowed to
 * be different lengths) are separated by at least one black square. There are
 * exactly seventeen ways of doing this.
 * 
 * How many ways can a row measuring fifty units in length be filled?
 * 
 * NOTE: Although the example above does not lend itself to the possibility, in
 * general it is permitted to mix block sizes. For example, on a row measuring
 * eight units in length you could use red (3), black (1), and red (4).
 */
public class PE114_Counting_block_combinations_I {
	private static long[] results = { 0, 0, 0, 2, 4, 7, 11, 17, 27, 44, 72,
		117, 189, 305, 493, 798 };

	public static void main(String[] args) {
		long start = System.nanoTime();
		
		long result = blocks(50);
		
		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long blocks(int rowlen) {
		if (rowlen < 3) {
			return 1;
		}

		if (rowlen <= 15) {
			return results[rowlen];
		}

		long sum = 1;

		for (int i = rowlen - 1; i > 2; i--) {
			sum += blocks(rowlen - i - 1);
		}

		sum += blocks(rowlen - 1);

		return sum;
	}
}
