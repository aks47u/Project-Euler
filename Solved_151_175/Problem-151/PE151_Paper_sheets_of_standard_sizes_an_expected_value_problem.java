package Solved_151_175;

/**
 * Paper sheets of standard sizes: an expected-value problem
 * Problem 151
 * 
 * A printing shop runs 16 batches (jobs) every week and each batch requires a
 * sheet of special colour-proofing paper of size A5.
 * 
 * Every Monday morning, the foreman opens a new envelope, containing a large
 * sheet of the special paper with size A1.
 * 
 * He proceeds to cut it in half, thus getting two sheets of size A2. Then he
 * cuts one of them in half to get two sheets of size A3 and so on until he
 * obtains the A5-size sheet needed for the first batch of the week.
 * 
 * All the unused sheets are placed back in the envelope.
 * 
 * At the beginning of each subsequent batch, he takes from the envelope one
 * sheet of paper at random. If it is of size A5, he uses it. If it is larger,
 * he repeats the 'cut-in-half' procedure until he has what he needs and any
 * remaining sheets are always placed back in the envelope.
 * 
 * Excluding the first and last batch of the week, find the expected number of
 * times (during each week) that the foreman finds a single sheet of paper in
 * the envelope.
 * 
 * Give your answer rounded to six decimal places using the format x.xxxxxx .
 */
public class PE151_Paper_sheets_of_standard_sizes_an_expected_value_problem {
	private static double result = 0.0;

	public static void main(String[] args) {
		long start = System.nanoTime();

		f(1.0, 1, 1, 1, 1);

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.print(String.format("%.6g%n", result));
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static void f(double p, int a2, int a3, int a4, int a5) {
		int sum = a2 + a3 + a4 + a5;

		if (sum == 1 && a5 == 0) {
			result += p;
		}

		if (sum == 0) {
			return;
		}

		if (a2 > 0) {
			f((p * a2) / sum, a2 - 1, a3 + 1, a4 + 1, a5 + 1);
		}

		if (a3 > 0) {
			f((p * a3) / sum, a2, a3 - 1, a4 + 1, a5 + 1);
		}

		if (a4 > 0) {
			f((p * a4) / sum, a2, a3, a4 - 1, a5 + 1);
		}

		if (a5 > 0) {
			f((p * a5) / sum, a2, a3, a4, a5 - 1);
		}
	}
}
