package Solved_201_225;

/**
 * Dice Game
 * Problem 205
 * 
 * Peter has nine four-sided (pyramidal) dice, each with faces numbered 1, 2, 3,
 * 4. Colin has six six-sided (cubic) dice, each with faces numbered 1, 2, 3, 4,
 * 5, 6.
 * 
 * Peter and Colin roll their dice and compare totals: the highest total wins.
 * The result is a draw if the totals are equal.
 * 
 * What is the probability that Pyramidal Pete beats Cubic Colin? Give your
 * answer rounded to seven decimal places in the form 0.abcdefg
 */
public class PE205_Dice_Game {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int[] pet = new int[] { 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int[] col = new int[] { 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		for (int a = 2; a <= 9; ++a) {
			int[] x = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0 };

			for (int b = 1; b < x.length; ++b) {
				for (int c = Math.max(0, b - 4); c < b; ++c) {
					x[b] += pet[c];
				}
			}

			pet = x;
		}

		for (int a = 2; a <= 6; ++a) {
			int[] x = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0 };

			for (int b = 1; b < x.length; ++b) {
				for (int c = Math.max(0, b - 6); c < b; ++c) {
					x[b] += col[c];
				}
			}

			col = x;
		}

		long count = 0;

		for (int p = 0; p < pet.length; ++p) {
			for (int c = 0; c < p; ++c) {
				count += pet[p] * col[c];
			}
		}

		long total = 0;

		for (int p = 0; p < pet.length; ++p) {
			for (int c = 0; c < col.length; ++c) {
				total += pet[p] * col[c];
			}
		}

		double result = (double) Math
				.round(((double) count / total) * 10000000) / 10000000;
		
		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
