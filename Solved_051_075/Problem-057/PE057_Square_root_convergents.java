package Solved_051_075;

/**
 * Square root convergents
 * Problem 57
 * 
 * It is possible to show that the square root of two can be expressed as an
 * infinite continued fraction.
 * 
 * sqrt 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...
 * 
 * By expanding this for the first four iterations, we get:
 * 
 * 1 + 1/2 = 3/2 = 1.5
 * 1 + 1/(2 + 1/2) = 7/5 = 1.4
 * 1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
 * 1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...
 * 
 * The next three expansions are 99/70, 239/169, and 577/408, but the eighth
 * expansion, 1393/985, is the first example where the number of digits in the
 * numerator exceeds the number of digits in the denominator.
 * 
 * In the first one-thousand expansions, how many fractions contain a numerator
 * with more digits than denominator?
 */
public class PE057_Square_root_convergents {
	private int n = 1000;
	private double[] numerators = new double[n + 1];
	private double[] denominators = new double[n + 1];
	private int result = 0;

	public static void main(String[] args) {
		long start = System.nanoTime();

		PE057_Square_root_convergents root2 = new PE057_Square_root_convergents();

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(root2.result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	public PE057_Square_root_convergents() {
		numerators[1] = 3;
		denominators[1] = 2;

		for (int i = 2; i <= n; i++) {
			denominators[i] = numerators[i - 1] + denominators[i - 1];
			numerators[i] = denominators[i] + denominators[i - 1];

			while (denominators[i] > 10.0) {
				denominators[i] *= 0.1;
				numerators[i] *= 0.1;
			}

			if (qualifies(denominators[i])) {
				result++;
			}
		}
	}

	private boolean qualifies(double d) {
		double ld = Math.log(d) / Math.log(10);
		double frac = ld - (int) ld - 1;

		return Math.exp(2 * (frac * Math.log(10))) > 0.5;
	}
}
