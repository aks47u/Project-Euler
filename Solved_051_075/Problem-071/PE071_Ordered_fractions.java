package Solved_051_075;

/**
 * Ordered fractions
 * Problem 71
 * 
 * Consider the fraction, n/d, where n and d are positive integers. If n<d and
 * HCF(n,d)=1, it is called a reduced proper fraction.
 * 
 * If we list the set of reduced proper fractions for d <= 8 in ascending order
 * of size, we get:
 * 
 * 1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3,
 * 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
 * 
 * It can be seen that 2/5 is the fraction immediately to the left of 3/7.
 * 
 * By listing the set of reduced proper fractions for d <= 1,000,000 in ascending
 * order of size, find the numerator of the fraction immediately to the left of
 * 3/7.
 */
public class PE071_Ordered_fractions {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int result = 2, max_denominator = 5;

		for (int denominator = 20; denominator <= 1000000; denominator++) {
			int approximation = (int) (3.0 / 7.0 * denominator - 2);

			for (int numerator = approximation; numerator < denominator; numerator++) {
				if (numerator * 7 >= 3.0 * denominator) {
					break;
				}

				if (numerator * max_denominator > result * denominator) {
					result = numerator;
					max_denominator = denominator;
				}
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
