package Solved_126_150;

/**
 * How many reversible numbers are there below one-billion?
 * Problem 145
 * 
 * Some positive integers n have the property that the sum [ n + reverse(n) ]
 * consists entirely of odd (decimal) digits. For instance, 36 + 63 = 99 and 409
 * + 904 = 1313. We will call such numbers reversible; so 36, 63, 409, and 904
 * are reversible. Leading zeroes are not allowed in either n or reverse(n).
 * 
 * There are 120 reversible numbers below one-thousand.
 * 
 * How many reversible numbers are there below one-billion (10^9)?
 */
public class PE145_How_many_reversible_numbers_are_there_below_one_billion {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int result = 0;

		for (int i = 1; i < 100000000; i++) {
			if (isOdd(i + reverse(i)) && !(hasZero(i))) {
				result++;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	public static boolean hasZero(int n) {
		return Integer.toString(n).charAt(Integer.toString(n).length() - 1) == '0';
	}

	public static boolean isOdd(int n) {
		int temp = 0;

		while (n > 0) {
			temp = n % 10;
			n /= 10;

			if (temp % 2 == 0) {
				return false;
			}
		}

		return true;
	}

	public static int reverse(int n) {
		int reversedNumber = 0;
		int temp = 0;

		while (n > 0) {
			temp = n % 10;
			reversedNumber = reversedNumber * 10 + temp;
			n /= 10;
		}

		return reversedNumber;
	}
}
