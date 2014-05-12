package Solved_076_100;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

/**
 * Su Doku
 * Problem 96
 * 
 * Su Doku (Japanese meaning number place) is the name given to a popular puzzle
 * concept. Its origin is unclear, but credit must be attributed to Leonhard
 * Euler who invented a similar, and much more difficult, puzzle idea called
 * Latin Squares. The objective of Su Doku puzzles, however, is to replace the
 * blanks (or zeros) in a 9 by 9 grid in such that each row, column, and 3 by 3
 * box contains each of the digits 1 to 9. Below is an example of a typical
 * starting puzzle grid and its solution grid.
 * 
 * 0 0 3|0 2 0|6 0 0
 * 9 0 0|3 0 5|0 0 1
 * 0 0 1|8 0 6|4 0 0
 * -----|-----|-----
 * 0 0 8|1 0 2|9 0 0
 * 7 0 0|0 0 0|0 0 8
 * 0 0 6|7 0 8|2 0 0
 * -----|-----|-----
 * 0 0 2|6 0 9|5 0 0
 * 8 0 0|2 0 3|0 0 9
 * 0 0 5|0 1 0|3 0 0
 * 
 * 
 * 4 8 3|9 2 1|6 5 7
 * 9 6 7|3 4 5|8 2 1
 * 2 5 1|8 7 6|4 9 3
 * -----|-----|-----
 * 5 4 8|1 3 2|9 7 6
 * 7 2 9|5 6 4|1 3 8
 * 1 3 6|7 9 8|2 4 5
 * -----|-----|-----
 * 3 7 2|6 8 9|5 1 4
 * 8 1 4|2 5 3|7 6 9
 * 6 9 5|4 1 7|3 8 2
 * 
 * A well constructed Su Doku puzzle has a unique solution and can be solved by
 * logic, although it may be necessary to employ "guess and test" methods in
 * order to eliminate options (there is much contested opinion over this). The
 * complexity of the search determines the difficulty of the puzzle; the example
 * above is considered easy because it can be solved by straight forward direct
 * deduction.
 * 
 * The 6K text file, sudoku.txt (right click and 'Save Link/Target As...'),
 * contains fifty different Su Doku puzzles ranging in difficulty, but all with
 * unique solutions (the first puzzle in the file is the example above).
 * 
 * By solving all fifty puzzles find the sum of the 3-digit numbers found in the
 * top left corner of each solution grid; for example, 483 is the 3-digit number
 * found in the top left corner of the solution grid above.
 */
public class PE096_Su_Doku {
	private static BufferedReader inFile;
	private static boolean[][] rows = new boolean[9][10];
	private static boolean[][] cols = new boolean[9][10];
	private static boolean[][][] sqs = new boolean[3][3][10];
	private static long[][] d = new long[9][9];

	public static void main(String[] args) {
		long start = System.nanoTime();

		try {
			inFile = new BufferedReader(new FileReader("PE096_sudoku.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int result = 0;

		while (next()) {
			solve(0, 0);
			result += d[0][0] * 100 + d[0][1] * 10 + d[0][2];
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static boolean solve(int i, int j) {
		if (i == 9) {
			return true;
		}

		if (j == 9) {
			return solve(i + 1, 0);
		}

		if (d[i][j] != 0) {
			return solve(i, j + 1);
		}

		for (int dg = 1; dg < 10; ++dg) {
			if (can(i, j, dg)) {
				set(i, j, dg);

				if (solve(i, j + 1)) {
					unset(i, j, dg);
					return true;
				}

				unset(i, j, dg);
			}
		}

		d[i][j] = 0;

		return false;
	}

	private static void unset(int i, int j, int dg) {
		rows[i][dg] = false;
		cols[j][dg] = false;
		sqs[i / 3][j / 3][dg] = false;
	}

	private static void set(int i, int j, int dg) {
		rows[i][dg] = true;
		cols[j][dg] = true;
		sqs[i / 3][j / 3][dg] = true;
		d[i][j] = dg;
	}

	private static boolean can(int i, int j, int dg) {
		return (!rows[i][dg] && !cols[j][dg] && !sqs[i / 3][j / 3][dg]);
	}

	private static boolean next() {
		for (int i = 0; i < 9; ++i) {
			Arrays.fill(rows[i], false);
			Arrays.fill(cols[i], false);
		}

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				Arrays.fill(sqs[i][j], false);
			}
		}

		try {
			String line = inFile.readLine();

			if (line == null) {
				return false;
			}

			String row;

			for (int i = 0; i < 9; ++i) {
				row = inFile.readLine();

				for (int j = 0; j < 9; ++j) {
					int dg = row.charAt(j) - '0';
					set(i, j, dg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}
}
