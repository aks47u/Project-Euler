package Solved_101_125;

import java.util.Collections;
import java.util.Vector;

/**
 * Digit power sum
 * Problem 119
 * 
 * The number 512 is interesting because it is equal to the sum of its digits
 * raised to some power: 5 + 1 + 2 = 8, and 8^3 = 512. Another example of a
 * number with this property is 614656 = 28^4.
 * 
 * We shall define an to be the nth term of this sequence and insist that a
 * number must contain at least two digits to have a sum.
 * 
 * You are given that a2 = 512 and a10 = 614656.
 * 
 * Find a30.
 */
public class PE119_Digit_power_sum {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long i, j, k, m;
		Vector<Long> v = new Vector<Long>();

		for (i = 2; i <= 100; i++) {
			k = i;

			for (j = 2; j <= 9; j++) {
				k *= i;
				m = sod(k);

				if (m == i) {
					v.add(k);
				}
			}
		}

		Collections.sort(v);
		long result = v.get(29);

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static int sod(long x) {
		int sum = 0;

		for (; x > 0; x /= 10) {
			sum += (x % 10);
		}

		return sum;
	}
}
