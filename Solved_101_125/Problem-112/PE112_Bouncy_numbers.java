package Solved_101_125;

/**
 * Bouncy numbers
 * Problem 112
 * 
 * Working from left-to-right if no digit is exceeded by the digit to its left
 * it is called an increasing number; for example, 134468.
 * 
 * Similarly if no digit is exceeded by the digit to its right it is called a
 * decreasing number; for example, 66420.
 * 
 * We shall call a positive integer that is neither increasing nor decreasing a
 * "bouncy" number; for example, 155349.
 * 
 * Clearly there cannot be any bouncy numbers below one-hundred, but just over
 * half of the numbers below one-thousand (525) are bouncy. In fact, the least
 * number for which the proportion of bouncy numbers first reaches 50% is 538.
 * 
 * Surprisingly, bouncy numbers become more and more common and by the time we
 * reach 21780 the proportion of bouncy numbers is equal to 90%.
 * 
 * Find the least number for which the proportion of bouncy numbers is exactly
 * 99%.
 */
public class PE112_Bouncy_numbers {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int result, j = 0;

		for (result = 100; true; result++) {
			if (isIncrease(result)) {
				continue;
			}

			if (isDecrease(result)) {
				continue;
			}

			j++;

			if (result * 99 == j * 100) {
				break;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static boolean isIncrease(int x) {
		String temp = new String(x + "");

		for (int i = 1; i < temp.length(); i++) {
			if (temp.charAt(i - 1) > temp.charAt(i)) {
				return false;
			}
		}

		return true;
	}

	private static boolean isDecrease(int x) {
		String temp = new String(x + "");

		for (int i = 1; i < temp.length(); i++) {
			if (temp.charAt(i - 1) < temp.charAt(i)) {
				return false;
			}
		}

		return true;
	}
}
