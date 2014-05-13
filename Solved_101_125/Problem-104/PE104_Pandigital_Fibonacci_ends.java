package Solved_101_125;

/**
 * Pandigital Fibonacci ends
 * Problem 104
 * 
 * The Fibonacci sequence is defined by the recurrence relation:
 * 
 * Fn = Fn-1 + Fn-2, where F1 = 1 and F2 = 1.
 * 
 * It turns out that F541, which contains 113 digits, is the first Fibonacci
 * number for which the last nine digits are 1-9 pandigital (contain all the
 * digits 1 to 9, but not necessarily in order). And F2749, which contains 575
 * digits, is the first Fibonacci number for which the first nine digits are 1-9
 * pandigital.
 * 
 * Given that Fk is the first Fibonacci number for which the first nine digits
 * AND the last nine digits are 1-9 pandigital, find k.
 */
public class PE104_Pandigital_Fibonacci_ends {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long f1 = 0, f2 = 1;
		long l1 = 0, l2 = 1;
		int k = 0, result = 0;

		while (!isSolution(f1, l1) && !isSolution(f2, l2)) {
			f1 += f2;
			f2 += f1;

			if (f1 > 10000000000000000L) {
				f1 = f1 / 10;
				f2 = f2 / 10;
			}

			l1 = (l1 + l2) % 1000000000;
			l2 = (l2 + l1) % 1000000000;
			k += 2;
		}

		if (isSolution(f1, l1)) {
			result = k;
		} else {
			result = k + 1;
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");

	}

	private static boolean isSolution(long f, long l) {
		if (String.valueOf(f).length() < 9) {
			return false;
		} else {
			return isPandigital(String.valueOf(f).substring(0, 9))
					&& isPandigital(String.valueOf(l));
		}

	}

	private static boolean isPandigital(String str) {
		if (str.length() < 9) {
			return false;
		}

		boolean isValid = true;
		char testChr = '0';

		for (int index = 0; isValid && index < 9; index++) {
			if (str.indexOf(++testChr) == -1) {
				isValid = false;
			}
		}

		return isValid;
	}
}
