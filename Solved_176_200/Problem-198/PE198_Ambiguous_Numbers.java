package Solved_176_200;

import java.util.ArrayDeque;

/**
 * Ambiguous Numbers
 * Problem 198
 * 
 * A best approximation to a real number x for the denominator bound d is a
 * rational number r/s (in reduced form) with s <= d, so that any rational number
 * p/q which is closer to x than r/s has q > d.
 * 
 * Usually the best approximation to a real number is uniquely determined for
 * all denominator bounds. However, there are some exceptions, e.g. 9/40 has the
 * two best approximations 1/4 and 1/5 for the denominator bound 6. We shall
 * call a real number x ambiguous, if there is at least one denominator bound
 * for which x possesses two best approximations. Clearly, an ambiguous number
 * is necessarily rational.
 * 
 * How many ambiguous numbers x = p/q, 0 < x < 1/100, are there whose
 * denominator q does not exceed 10^8?
 */
public class PE198_Ambiguous_Numbers {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long size = 100000000;
		long boundn = 1, boundd = 100;
		ArrayDeque<Long> list = new ArrayDeque<Long>();
		long leftn = 0, leftd = 1;
		long rghtn = 1, rghtd = 1;
		long result = 0;

		while (true) {
			long ambigd = 2 * leftd * rghtd;

			if (ambigd <= size) {
				long ambign = leftd * rghtn + leftn * rghtd;

				if (ambign * boundd < boundn * ambigd) {
					result++;
				}

				long medn = leftn + rghtn, medd = leftd + rghtd;

				if ((medn * boundd <= boundn * medd)) {
					list.push(leftd);
					list.push(leftn);
					leftn = medn;
					leftd = medd;
				} else {
					rghtd = medd;
					rghtn = medn;
				}
			} else {
				if (list.isEmpty()) {
					break;
				}

				rghtn = leftn;
				rghtd = leftd;
				leftn = list.pop();
				leftd = list.pop();
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
