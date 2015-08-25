package Solved_076_100;

import java.io.File;
import java.util.Scanner;

/**
 * Path sum: two ways
 * Problem 81
 * 
 * In the 5 by 5 matrix below, the minimal path sum from the top left to the
 * bottom right, by only moving to the right and down, is indicated in bold red
 * and is equal to 2427.
 * 
 * 131 673 234 103  18
 * 201  96 342 965 150
 * 630 803 746 422 111
 * 537 699 497 121 956
 * 805 732 524  37 331
 * 
 * Find the minimal path sum, in matrix.txt (right click and 'Save Link/Target
 * As...'), a 31K text file containing a 80 by 80 matrix, from the top left to
 * the bottom right by only moving right and down.
 */
public class PE081_Path_sum_two_ways {
	private static int[][] matrix;
	private static String[][] matrixString;

	public static void main(String[] args) throws Exception {
		long start = System.nanoTime();

		matrixString = new String[80][80];
		matrix = new int[82][82];
		File file = new File("PE081_matrix.txt");
		Scanner scn = new Scanner(file);

		for (int index = 0; index < 80; index++) {
			matrixString[index] = scn.nextLine().split(",");

			for (int index2 = 0; index2 < 80; index2++) {
				matrix[index + 1][index2 + 1] = Integer
						.parseInt(matrixString[index][index2]);
			}
		}

		scn.close();
		int row, col;

		for (int startCol = 79; startCol > 0; startCol--) {
			for (int offset = 0; offset < 81 - startCol; offset++) {
				matrix[row = 80 - offset][col = startCol + offset] += min(
						matrix[row + 1][col], matrix[row][col + 1]);
			}
		}

		for (int startRow = 79; startRow > 0; startRow--) {
			for (int offset = 0; offset < startRow; offset++) {
				matrix[row = startRow - offset][col = offset + 1] += min(
						matrix[row + 1][col], matrix[row][col + 1]);
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(matrix[1][1]);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static int min(int a, int b) {
		return (a == 0 || (b < a && b != 0)) ? b : a;
	}
}
