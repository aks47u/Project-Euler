package Solved_101_125;

/**
 * Counting block combinations II
 * Problem 115
 * 
 * NOTE: This is a more difficult version of problem 114.
 * 
 * A row measuring n units in length has red blocks with a minimum length of m
 * units placed on it, such that any two red blocks (which are allowed to be
 * different lengths) are separated by at least one black square.
 * 
 * Let the fill-count function, F(m, n), represent the number of ways that a row
 * can be filled.
 * 
 * For example, F(3, 29) = 673135 and F(3, 30) = 1089155.
 * 
 * That is, for m = 3, it can be seen that n = 30 is the smallest value for
 * which the fill-count function first exceeds one million.
 * 
 * In the same way, for m = 10, it can be verified that F(10, 56) = 880711 and
 * F(10, 57) = 1148904, so n = 57 is the least value for which the fill-count
 * function first exceeds one million.
 * 
 * For m = 50, find the least value of n for which the fill-count function first
 * exceeds one million.
 */
public class PE115_Counting_block_combinations_II {
	private static long sum = 0;
	private static int blocklength = 50;
	private static long[] sumval = new long[250];

	public static void main(String[] args) {
		long start = System.nanoTime();

		int result = 0;

		for (int n = 0; n < 250; n++) {
			sum = 0;
			block(n);
			sumval[n] = sum;
			if (sum > 1000000) {
				result = n;
				break;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static void block(int remaining) {
		for (int i = blocklength; remaining >= i; i++) {
			for (int h = 0; h <= (remaining - i); h++) {
				sum++;

				if ((remaining - (i + h + 1)) >= blocklength) {
					sum += sumval[(remaining - (i + 1 + h))];
				}
			}
		}
	}
}
