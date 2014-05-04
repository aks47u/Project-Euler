package Solved_051_075;

/**
 * Prime digit replacements
 * Problem 51
 * 
 * By replacing the 1st digit of the 2-digit number *3, it turns out that six of
 * the nine possible values: 13, 23, 43, 53, 73, and 83, are all prime.
 * 
 * By replacing the 3rd and 4th digits of 56**3 with the same digit, this
 * 5-digit number is the first example having seven primes among the ten
 * generated numbers, yielding the family: 56003, 56113, 56333, 56443, 56663,
 * 56773, and 56993. Consequently 56003, being the first member of this family,
 * is the smallest prime with this property.
 * 
 * Find the smallest prime which, by replacing part of the number (not
 * necessarily adjacent digits) with the same digit, is part of an eight prime
 * value family.
 */
public class PE051_Prime_digit_replacements {
	private static long start;

	public static void main(String[] args) {
		start = System.nanoTime();

		for (int i = 3; i < 10; i += 3) {
			for (int j = i + 1; j < 11; j++) {
				for (int k = i; k < j; k++) {
					int[] template = new int[j];
					generateAndTry(i, template, k);
				}
			}
		}
	}

	private static boolean isPrime(int[] digits) {
		return isPrime(toInteger(digits));
	}

	private static int toInteger(int[] digits) {
		int mult = 1;
		int retval = 0;

		for (int i : digits) {
			assert (mult > 0);
			retval += i * mult;
			mult *= 10;
		}
		return retval;
	}

	private static boolean isPrime(int val) {
		boolean retval = isPrimeImpl(val);
		assert (verifyPrime(val, retval));
		return retval;
	}

	private static boolean verifyPrime(int val, boolean retval) {
		boolean validate = isPrimeBruteImpl(val);
		return validate == retval;
	}

	private static boolean isPrimeImpl(int val) {
		if (2 == val || 3 == val) {
			return true;
		}

		if (0 == (val & 1)) {
			return false;
		}

		int till = (int) (Math.sqrt(val) + 3);

		for (int i = 3; i < till; i += 2) {
			if (0 == (val % i)) {
				return false;
			}
		}

		return true;
	}

	private static boolean isPrimeBruteImpl(int val) {
		for (int i = 2; i < val; i++) {
			if (0 == (val % i)) {
				return false;
			}
		}

		return true;
	}

	private static void generateAndTry(int tries, int[] template, int start) {
		if (0 == tries) {
			actualTry(template, template.length - 1, new int[template.length]);
			return;
		}

		for (int i = start; i >= tries; i--) {
			template[i] = 1;

			for (int j = tries - 1; j < i; j++) {
				generateAndTry(tries - 1, template, j);
			}

			template[i] = 0;
		}
	}

	private static void actualTry(int[] template, int start, int value[]) {
		int indx = findIndex(template, start);

		if (0 == indx) {
			handleLSD(value, template);
			return;
		}

		for (int i = 0; i <= 9; i++) {
			value[indx] = i;
			actualTry(template, start - 1, value);
		}
	}

	private static void handleLSD(int[] value, int[] template) {
		final int lsd[] = { 1, 3, 7, 9 };

		for (int i : lsd) {
			value[0] = i;
			int num = 0;
			int min = -1;
			replaceTemplate(value, template, 0);

			if (isPrime(value)) {
				num++;
				min = 0;
			}

			replaceTemplate(value, template, 1);

			if (isPrime(value)) {
				num++;

				if (-1 == min) {
					min = 1;
				}
			}

			replaceTemplate(value, template, 2);

			if (isPrime(value)) {
				num++;

				if (-1 == min) {
					min = 2;
				}
			}

			if (num > 0) {
				replaceTemplate(value, template, 3);

				if (isPrime(value)) {
					num++;
				}

				if (num < 2) {
					continue;
				}

				replaceTemplate(value, template, 4);

				if (isPrime(value)) {
					num++;
				}

				if (num < 3) {
					continue;
				}

				replaceTemplate(value, template, 5);

				if (isPrime(value)) {
					num++;
				}

				if (num < 4) {
					continue;
				}

				replaceTemplate(value, template, 6);

				if (isPrime(value)) {
					num++;
				}

				if (num < 5) {
					continue;
				}

				replaceTemplate(value, template, 7);

				if (isPrime(value)) {
					num++;
				}

				if (num < 6) {
					continue;
				}

				replaceTemplate(value, template, 8);

				if (isPrime(value)) {
					num++;
				}

				if (num < 7) {
					continue;
				}

				replaceTemplate(value, template, 9);

				if (isPrime(value)) {
					num++;
				}
			}

			if (num >= 8) {
				replaceTemplate(value, template, min);
				System.out.println(toInteger(value));

				long end = System.nanoTime();
				long runtime = end - start;
				System.out.println("Runtime: " + runtime / 1000000 + "ms ("
						+ runtime + "ns)");
				System.exit(0);
			}
		}
	}

	private static void replaceTemplate(int[] value, int[] template, int which) {
		for (int i = 0; i < template.length; i++) {
			if (1 == template[i]) {
				value[i] = which;
			}
		}
	}

	private static int findIndex(int[] template, int start) {
		if (0 == start) {
			return 0;
		}

		while (1 == template[start]) {
			assert (start > 0);
			start--;
		}

		return start;
	}
}
