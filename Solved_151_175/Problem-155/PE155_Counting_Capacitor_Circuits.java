package Solved_151_175;

import java.util.Arrays;

/**
 * Counting Capacitor Circuits
 * Problem 155
 * 
 * An electric circuit uses exclusively identical capacitors of the same value
 * C. The capacitors can be connected in series or in parallel to form
 * sub-units, which can then be connected in series or in parallel with other
 * capacitors or other sub-units to form larger sub-units, and so on up to a
 * final circuit.
 * 
 * Using this simple procedure and up to n identical capacitors, we can make
 * circuits having a range of different total capacitances. For example, using
 * up to n=3 capacitors of 60 F each, we can obtain the following 7 distinct
 * total capacitance values:
 * 
 * If we denote by D(n) the number of distinct total capacitance values we can
 * obtain when using up to n equal-valued capacitors and the simple procedure
 * described above, we have: D(1)=1, D(2)=3, D(3)=7 ...
 * 
 * Find D(18).
 * 
 * Reminder : When connecting capacitors C1, C2 etc in parallel, the total
 * capacitance is CT = C1 + C2 +..., whereas when connecting them in series, the
 * overall capacitance is given by:
 */
public class PE155_Counting_Capacitor_Circuits {
	public static void main(String[] args) {
		long start = System.nanoTime();

		double[][] mem = new double[19][];
		mem[1] = new double[1];
		mem[1][0] = 1;
		double[] temp = new double[5200009];
		int result = 0;

		for (int i = 2; i <= 18; i++) {
			result = 0;

			for (int j = 1; j <= i / 2; j++) {
				int k = i - j;

				for (double d1 : mem[j]) {
					for (double d2 : mem[k]) {
						temp[result++] = d1 + d2;
						temp[result++] = 1 / (1 / d1 + 1 / d2);

						if (result > 5200000) {
							result = trim(temp, 0, result);
						}
					}
				}
			}

			result = trim(temp, 0, result);

			if (i < 18) {
				mem[i] = new double[result];

				for (int j = 0; j < result; j++) {
					mem[i][j] = temp[j];
				}
			}
		}

		for (int i = 1; i <= 17; i++) {
			for (double d : mem[i]) {
				temp[result++] = d;

				if (result > 5200000) {
					result = trim(temp, 0, result);
				}
			}
		}

		result = trim(temp, 0, result);

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static int trim(double[] array, int fromIndex, int toIndex) {
		Arrays.sort(array, fromIndex, toIndex);
		int lastIndex = fromIndex - 1;
		double last = 0;

		for (int j = fromIndex; j < toIndex; j++) {
			if (Math.abs((array[j] - last) / array[j]) > 0.00000001) {
				array[++lastIndex] = array[j];
				last = array[j];
			}
		}

		return lastIndex + 1;
	}
}
