package Solved_026_050;

/**
 * Distinct primes factors
 * Problem 47
 * 
 * The first two consecutive numbers to have two distinct prime factors are:
 * 
 * 14 = 2 × 7 15 = 3 × 5
 * 
 * The first three consecutive numbers to have three distinct prime factors are:
 * 
 * 644 = 2² × 7 × 23
 * 645 = 3 × 5 × 43
 * 646 = 2 × 17 × 19.
 * 
 * Find the first four consecutive integers to have four distinct prime factors.
 * What is the first of these numbers?
 */
public class PE047_Distinct_primes_factors {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int count = 0, result = 0;

		for (int startNumber = 1; true; startNumber++) {
			if (distinctFactorCount(startNumber) == 4) {
				count++;
			} else {
				count = 0;
			}
			if (count == 4) {
				result = startNumber - 3;
				break;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static int distinctFactorCount(long n) {
		int factorCount = 0;
		int factor = 2;

		if (n % factor == 0) {
			factorCount++;
			n /= factor;
		}

		while (n % factor == 0) {
			n /= factor;
		}

		for (factor = 3; factor * factor <= n; factor += 2) {
			if (n % factor == 0) {
				factorCount++;
				n /= factor;
			}

			while (n % factor == 0) {
				n /= factor;
			}
		}

		if (factorCount > 0 && n > 1) {
			factorCount++;
		}

		return factorCount;
	}
}
