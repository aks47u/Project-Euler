package Solved_051_075;

/**
 * Permuted multiples
 * Problem 52
 * 
 * It can be seen that the number, 125874, and its double, 251748, contain
 * exactly the same digits, but in a different order.
 * 
 * Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x,
 * contain the same digits.
 */
public class PE052_Permuted_multiples {
	public static void main(String[] args) {
		long start = System.nanoTime();

		for (int i = 1; true; i++) {
			if (sameDigits(i, (2 * i))) {
				if (sameDigits(i, (3 * i))) {
					if (sameDigits(i, (4 * i))) {
						if (sameDigits(i, (5 * i))) {
							if (sameDigits(i, (6 * i))) {
								long end = System.nanoTime();
								System.out.println(i);
								long runtime = end - start;
								System.out.println("Runtime: " + runtime
										/ 1000000 + "ms (" + runtime + "ns)");

								return;
							}
						}
					}
				}
			}
		}
	}

	private static boolean sameDigits(int first, int second) {
		String x = new Integer(first).toString();
		String y = new Integer(second).toString();
		int counter = 0;

		if (x.length() != y.length()) {
			return false;
		}

		for (int i = 0; i < x.length(); i++) {
			String tmp = x.substring(i, i + 1);

			for (int j = 0; j < y.length(); j++) {
				String tmp2 = y.substring(j, j + 1);

				if (tmp.equals(tmp2)) {
					counter++;
					break;
				}
			}
		}

		if (counter == x.length()) {
			return true;
		}

		return false;
	}
}
