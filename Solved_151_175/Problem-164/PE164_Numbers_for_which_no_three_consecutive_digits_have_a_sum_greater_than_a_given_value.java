package Solved_151_175;

/**
 * Numbers for which no three consecutive digits have a sum greater than a given
 * value
 * Problem 164
 * 
 * How many 20 digit numbers n (without any leading zero) exist such that no
 * three consecutive digits of n have a sum greater than 9?
 */
public class PE164_Numbers_for_which_no_three_consecutive_digits_have_a_sum_greater_than_a_given_value {
	public static void main(String[] args) {
		long start = System.nanoTime();
		
		long[][][] v = new long[20][10][10];

		for (int i = 1; i < 10; i++) {
			v[0][0][i] = 1;
		}

		for (int i = 1; i < 20; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 10; k++) {
					for (int d = 0; d + j + k < 10; d++) {
						v[i][k][d] += v[i - 1][j][k];
					}
				}
			}
		}

		long result = 0;

		for (int j = 0; j < 10; j++) {
			for (int k = 0; k < 10; k++) {
				result += v[19][j][k];
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
