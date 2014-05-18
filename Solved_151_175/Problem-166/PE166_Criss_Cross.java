package Solved_151_175;

/**
 * Criss Cross
 * Problem 166
 * 
 * A 4x4 grid is filled with digits d, 0 <= d <= 9.
 * 
 * It can be seen that in the grid
 * 
 * 6 3 3 0
 * 5 0 4 3
 * 0 7 1 4
 * 1 2 4 5
 * 
 * the sum of each row and each column has the value 12. Moreover the sum of
 * each diagonal is also 12.
 * 
 * In how many ways can you fill a 4x4 grid with the digits d, 0 <= d <= 9 so
 * that each row, each column, and both diagonals have the same sum?
 */
public class PE166_Criss_Cross {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int result = 0;

		for (int d = 0; d <= 9; d++) {
			for (int e = 0; e <= 9; e++) {
				for (int h = 0; h <= 9; h++) {
					if (d + e - h < 0 || d + e - h > 9) {
						continue;
					}

					for (int b = 0; b <= 9; b++) {
						for (int c = 0; c <= 9; c++) {
							for (int f = 0; f <= 9; f++) {
								for (int g = 0; g <= 9; g++) {
									int[] lower = { b + c + d + e - h,
											c + e + f + g + h - 9, d + e + f,
											c + g + h, e + f + g, c + d + e,
											b + c + d + d + e + f - h - 9,
											b + d + f };
									int[] upper = { b + c + d + e - h + 9,
											c + e + f + g + h, d + e + f + 9,
											c + g + h + 9, e + f + g + 9,
											c + d + e + 9,
											b + c + d + d + e + f - h,
											b + d + f + 9 };
									int maxLower = getMax(lower), minUpper = getMin(upper);

									if (maxLower <= minUpper) {
										result += minUpper - maxLower + 1;
									}
								}
							}
						}
					}
				}
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static int getMin(int[] arr) {
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < arr.length; i++) {
			if (min > arr[i]) {
				min = arr[i];
			}
		}

		return min;
	}

	private static int getMax(int[] arr) {
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < arr.length; i++) {
			if (max < arr[i]) {
				max = arr[i];
			}
		}

		return max;
	}
}
