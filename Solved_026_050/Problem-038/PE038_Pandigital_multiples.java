package Solved_026_050;

/**
 * Pandigital multiples
 * Problem 38
 * 
 * Take the number 192 and multiply it by each of 1, 2, and 3:
 * 
 * 192 × 1 = 192
 * 192 × 2 = 384
 * 192 × 3 = 576
 * 
 * By concatenating each product we get the 1 to 9 pandigital, 192384576. We
 * will call 192384576 the concatenated product of 192 and (1,2,3)
 * 
 * The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4,
 * and 5, giving the pandigital, 918273645, which is the concatenated product of
 * 9 and (1,2,3,4,5).
 * 
 * What is the largest 1 to 9 pandigital 9-digit number that can be formed as
 * the concatenated product of an integer with (1,2, ... , n) where n > 1?
 */
public class PE038_Pandigital_multiples {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int result = 0;

		for (int i = 1; i < 10000; i++) {
			String s = "";

			for (int j = 1; j < 10; j++) {
				int product = i * j;
				s += String.valueOf(product);

				if (isPandigital(s)) {
					int t = Integer.valueOf(s);

					if (t > result) {
						result = t;
					}
				}

				if (s.length() > 9) {
					break;
				}
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static boolean isPandigital(String s) {
		if (s.length() != 9) {
			return false;
		}

		boolean[] numbers = new boolean[11];

		for (int i = 0; i < s.length(); i++) {
			numbers[s.charAt(i) - '0'] = true;
		}

		return numbers[1] && numbers[2] && numbers[3] && numbers[4]
				&& numbers[5] && numbers[6] && numbers[7] && numbers[8]
						&& numbers[9];
	}
}
