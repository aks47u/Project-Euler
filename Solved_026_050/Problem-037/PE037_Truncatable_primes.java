package Solved_026_050;

/**
 * Truncatable primes
 * Problem 37
 * 
 * The number 3797 has an interesting property. Being prime itself, it is
 * possible to continuously remove digits from left to right, and remain prime
 * at each stage: 3797, 797, 97, and 7. Similarly we can work from right to
 * left: 3797, 379, 37, and 3.
 * 
 * Find the sum of the only eleven primes that are both truncatable from left to
 * right and right to left.
 * 
 * NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
 */
public class PE037_Truncatable_primes {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int length, result = 0, count = 0, current = 23;
		String string;

		while (count < 11) {
			string = String.valueOf(current);
			length = string.length();

			if (!(string.contains("0") || string.charAt(0) == '1'
					|| string.charAt(length - 1) == '1'
					|| string.charAt(0) == '4'
					|| string.charAt(length - 1) == '4'
					|| string.charAt(0) == '6'
					|| string.charAt(length - 1) == '6'
					|| string.charAt(0) == '8'
					|| string.charAt(length - 1) == '8'
					|| string.charAt(0) == '9' || string.charAt(length - 1) == '9')) {
				if (isPrime(current) && isTruncatable(current)) {
					count++;
					result += current;
				}
			}

			current++;
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static boolean isPrime(int number) {
		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0) {
				return false;
			}
		}

		return true;
	}

	private static boolean isTruncatable(int number) {
		String string = String.valueOf(number), subString;
		int length = string.length(), value;

		for (int i = 1; i < length; i++) {
			subString = string.substring(i);
			value = Integer.valueOf(subString);

			if (!isPrime(value)) {
				return false;
			}
		}

		for (int i = 0; i < length - 1; i++) {
			subString = string.substring(0, length - 1 - i);
			value = Integer.valueOf(subString);

			if (!isPrime(value)) {
				return false;
			}
		}

		return true;
	}
}
