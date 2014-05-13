package Solved_101_125;

/**
 * Red, green, and blue tiles
 * Problem 117
 * 
 * Using a combination of black square tiles and oblong tiles chosen from: red
 * tiles measuring two units, green tiles measuring three units, and blue tiles
 * measuring four units, it is possible to tile a row measuring five units in
 * length in exactly fifteen different ways.
 * 
 * How many ways can a row measuring fifty units in length be tiled?
 * 
 * NOTE: This is related to problem 116.
 */
public class PE117_Red_green_and_blue_tiles {
	private static long[][] cache = new long[100][100];

	public static void main(String[] args) {
		long start = System.nanoTime();

		long result = find(2, 50);

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long find(int m, int n) {
		long count = 0;

		for (int i = m; i <= n; i++) {
			count += F(m, i);
		}

		return count + 1;
	}

	private static long F(int min, int length) {
		if (length < min) {
			return 1;
		}

		if (min < 100 && length < 100) {
			if (cache[min][length] != 0) {
				return cache[min][length];
			}
		}

		long count = 0;

		for (int i = min; i <= 4; i++) {
			for (int skip = 0; skip <= length - i; skip++) {
				if (length - i - skip < min) {
					count += 1;
					break;
				} else {
					if (min < 100 && length - i - skip < 100
							&& cache[min][length - i - skip] == 0) {
						cache[min][length - i - skip] = F(min, length - i
								- skip);
					}

					count += F(min, length - i - skip);
				}
			}
		}

		return count;
	}
}
