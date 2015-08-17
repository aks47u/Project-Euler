package Solved_051_075;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Maximum path sum II
 * Problem 67
 * 
 * 
 * By starting at the top of the triangle below and moving to adjacent numbers
 * on the row below, the maximum total from top to bottom is 23.
 * 
 * 3 7 4 2 4 6 8 5 9 3
 * 
 * That is, 3 + 7 + 4 + 9 = 23.
 * 
 * Find the maximum total from top to bottom in triangle.txt (right click and
 * 'Save Link/Target As...'), a 15K text file containing a triangle with
 * one-hundred rows.
 * 
 * NOTE: This is a much more difficult version of Problem 18. It is not possible
 * to try every route to solve this problem, as there are 299 altogether! If you
 * could check one trillion (1012) routes every second it would take over twenty
 * billion years to check them all. There is an efficient algorithm to solve it.
 * ;o)
 */
public class PE067_Maximum_path_sum_II {
	public static void main(String[] args) throws FileNotFoundException {
		long start = System.nanoTime();

		File fi = new File("PE067_triangle.txt");
		Scanner scn = new Scanner(fi);
		int[][] rowNumber = new int[100][];
		String[][] rowString new String[100][];

		for (int row = 0; row < 100; row++) {
			rowNumber[row] = new int[row + 1];
			rowString[row] = scn.nextLine().split(" ");

			for (int col = 0; col < row + 1; col++) {
				rowNumber[row][col] = Integer.parseInt(rowString[row][col]);
			}
		}

		scn.close();

		for (int row = 98; row >= 0; row--) {
			for (int col = 0; col < row + 1; col++) {
				rowNumber[row][col] += Math.max(rowNumber[row + 1][col],
						rowNumber[row + 1][col + 1]);
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(rowNumber[0][0]);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
