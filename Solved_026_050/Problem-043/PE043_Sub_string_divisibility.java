package Solved_026_050;

/**
 * Sub-string divisibility
 * Problem 43
 * 
 * The number, 1406357289, is a 0 to 9 pandigital number because it is made up
 * of each of the digits 0 to 9 in some order, but it also has a rather
 * interesting sub-string divisibility property.
 * 
 * Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note
 * the following:
 * 
 * d_2d_3d_4=406 is divisible by 2
 * d_3d_4d_5=063 is divisible by 3
 * d_4d_5d_6=635 is divisible by 5
 * d_5d_6d_7=357 is divisible by 7
 * d_6d_7d_8=572 is divisible by 11
 * d_7d_8d_9=728 is divisible by 13
 * d_8d_9d_10=289 is divisible by 17
 * 
 * Find the sum of all 0 to 9 pandigital numbers with this property.
 */
public class PE043_Sub_string_divisibility {
	private static int[] primeList = { 2, 3, 5, 7, 11, 13, 17 };
	private static String[][] modList;
	private static long result = 0;
	private static String numbers = "0123456789";

	public static void main(String[] args) {
		long start = System.nanoTime();

		modList = new String[primeList.length][];
		int prime;

		for (int i = 0; i < primeList.length; i++) {
			prime = primeList[i];
			modList[i] = new String[prime];

			for (int j = 0; j < prime; j++) {
				modList[i][j] = new String("");
			}

			for (int j = 0; j < 1000; j += 100) {
				if (j % prime == 0) {
					modList[i][0] += (j / 100);
				} else {
					modList[i][(j / prime + 1) * prime % j] += (j / 100);
				}
			}
		}

		String mult17str;

		for (int i = 102; i < 1003; i += 17) {
			if (!isValid(mult17str = String.valueOf(i))) {
				continue;
			} else {
				findSolutions(mult17str);
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static void findSolutions(String number) {
		if (number.length() == 9 && isValid(number)) {
			boolean found = false;
			int i;

			for (i = 0; !found && i < numbers.length(); i++) {
				if (number.indexOf(numbers.charAt(i)) == -1) {
					found = true;
				}
			}

			i--;

			if (i != 0) {
				result += Long.parseLong(String.valueOf(i) + number);
			}
		} else {
			int primeIndex = primeList.length - number.length() + 1;
			int mod = Integer.parseInt(number.substring(0, 2))
					% primeList[primeIndex];
			String newNumber;

			for (int i = 0; i < modList[primeIndex][mod].length(); i++) {
				if (isValid(newNumber = modList[primeIndex][mod].substring(i,
						i + 1) + number)) {
					findSolutions(newNumber);
				}
			}
		}
	}

	private static boolean isValid(String number) {
		boolean valid = true;

		for (int i = 0; valid && i < number.length() - 1; i++) {
			if (number.indexOf(number.charAt(i), i + 1) != -1) {
				valid = false;
			}
		}

		return valid;
	}
}
