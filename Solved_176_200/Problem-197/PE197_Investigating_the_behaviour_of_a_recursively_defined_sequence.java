package Solved_176_200;

/**
 * Investigating the behaviour of a recursively defined sequence
 * Problem 197
 * 
 * Given is the function f(x) = floor(2^30.403243784-x^2) × 10^-9 (floor is the
 * floor-function), the sequence un is defined by u0 = -1 and un+1 = f(un).
 * 
 * Find un + un+1 for n = 10^12. Give your answer with 9 digits after the
 * decimal point.
 */
public class PE197_Investigating_the_behaviour_of_a_recursively_defined_sequence {
	public static void main(String[] args) {
		long start = System.nanoTime();

		double u1 = -1.0, u2 = 0.0, result = 0.0;
		boolean got_it = false;

		while (!got_it) {
			double test1 = Math.floor(Math.pow(2, 30.403243784 - u1 * u1)) * 1e-9;
			double test2 = Math
					.floor(Math.pow(2, 30.403243784 - test1 * test1)) * 1e-9;

			if (test1 == u2 && test2 == u1) {
				got_it = true;
			} else {
				u1 = test2;
				u2 = test1;
			}
		}

		result = u1 + u2;

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
