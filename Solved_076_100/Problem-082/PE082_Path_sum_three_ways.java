package Solved_076_100;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Path sum: three ways
 * Problem 82
 * 
 * NOTE: This problem is a more challenging version of Problem 81.
 * 
 * The minimal path sum in the 5 by 5 matrix below, by starting in any cell in
 * the left column and finishing in any cell in the right column, and only
 * moving up, down, and right, is indicated in red and bold; the sum is equal to
 * 994.
 * 
 * 131 673 234 103  18
 * 201  96 342 965 150
 * 630 803 746 422 111
 * 537 699 497 121 956
 * 805 732 524  37 331
 * 
 * Find the minimal path sum, in matrix.txt (right click and 'Save Link/Target
 * As...'), a 31K text file containing a 80 by 80 matrix, from the left column
 * to the right column.
 */
public class PE082_Path_sum_three_ways {
	int[][] matrix, minSummary;
	String[][] stepFrom; // For finding the minimal sum path
	int totalX, totalY;

	public static void main(String[] args) {
		long start = System.nanoTime();

		File file = new File("PE082_matrix.txt");
		new PE082_Path_sum_three_ways(file).run();

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	public PE082_Path_sum_three_ways(File file) {
		matrix = parseInput(file);
		totalX = matrix.length;
		totalY = totalX;
		minSummary = new int[totalX][totalY];
		stepFrom = new String[totalX][totalY];

		for (int i = 0; i < totalX; i++) {
			for (int j = 0; j < totalY; j++) {
				minSummary[i][j] = Integer.MAX_VALUE;
			}
		}
	}

	public void run() {
		for (int i = 0; i < totalY; i++) {
			step(0, i, 0, "Begin ");
		}

		printResult();
	}

	private void step(int x, int y, int sumBefore, String from) {
		int sum = sumBefore + matrix[x][y];
		boolean updated = update(x, y, sum, from);

		if (updated) {
			if (isInBound(x + 1, y)) {
				step(x + 1, y, sum, "(" + x + ", " + y + ")");
			}

			if ((isInBound(x, y + 1)) && (x != 0)) {
				step(x, y + 1, sum, "(" + x + ", " + y + ")");
			}

			if ((isInBound(x, y - 1)) && (x != 0)) {
				step(x, y - 1, sum, "(" + x + ", " + y + ")");
			}
		}
	}

	private boolean update(int x, int y, int sum, String from) {
		if (sum < minSummary[x][y]) {
			minSummary[x][y] = sum;
			stepFrom[x][y] = from;

			return true;
		} else {
			return false;
		}
	}

	private boolean isInBound(int x, int y) {
		return ((x >= 0) && (y >= 0) && (x < totalX) && (y < totalY));
	}

	private void printResult() {
		int result = Integer.MAX_VALUE;

		for (int p = 0; p < totalY; p++) {
			if (minSummary[totalX - 1][p] < result) {
				result = minSummary[totalX - 1][p];
			}
		}

		System.out.println(result);
	}

	public int[][] parseInput(File inputFile) {
		int[][] ret;

		try {
			FileReader reader = null;
			reader = new FileReader(inputFile);
			BufferedReader buffer = new BufferedReader(reader);
			String line = buffer.readLine();
			String[] temp = line.split(",");
			int width = temp.length;
			ret = new int[width][width];
			int lineNum = 0;

			while (line != null) {
				temp = line.split(",");

				for (int i = 0; i < width; i++) {
					ret[i][lineNum] = Integer.parseInt(temp[i]);
				}

				line = buffer.readLine();
				lineNum++;
			}

			buffer.close();
		} catch (IOException ex) {
			throw new RuntimeException(ex.getMessage());
		}

		return ret;
	}
}
