package Solved_076_100;

/**
 * Arranged probability
 * Problem 100
 * 
 * If a box contains twenty-one coloured discs, composed of fifteen blue discs
 * and six red discs, and two discs were taken at random, it can be seen that
 * the probability of taking two blue discs, P(BB) = (15/21)×(14/20) = 1/2.
 * 
 * The next such arrangement, for which there is exactly 50% chance of taking
 * two blue discs at random, is a box containing eighty-five blue discs and
 * thirty-five red discs.
 * 
 * By finding the first arrangement to contain over 10^12 = 1,000,000,000,000
 * discs in total, determine the number of blue discs that the box would
 * contain.
 */
public class PE100_Arranged_probability {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long result = 1, total = 1;
		long oBlue = 1, oTotal = 1;

		for (total = 0L; total / 1000000 < 1000000; total = 4 * oBlue + 3
				* oTotal - 3) {
			oBlue = result;
			oTotal = total;
			result = 3 * oBlue + 2 * oTotal - 2;
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
