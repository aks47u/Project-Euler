package Solved_026_050;

/**
 * Pandigital prime
 * Problem 41
 * 
 * We shall say that an n-digit number is pandigital if it makes use of all the
 * digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital and is
 * also prime.
 * 
 * What is the largest n-digit pandigital prime that exists?
 */
public class PE041_Pandigital_prime {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int result = rek(7, 0, 0, new int[7]);

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static int rek(int max, int used, int n, int[] nbr) {
		if (n == max) {
			int x = 1, candidate = 0;

			for (int i = 1; i <= max; i++) {
				candidate += nbr[max - i] * x;
				x *= 10;
			}

			return (isPrime(candidate)) ? candidate : -1;
		}

		for (int i = 0; i < max; i++) {
			if ((used & (1 << i)) == 0) {
				nbr[n] = (max - i);
				int res = rek(max, used | (1 << i), n + 1, nbr);

				if (res != -1) {
					return res;
				}
			}
		}

		return -1;
	}

	private static boolean isPrime(int n) {
		if (n % 2 == 0) {
			return false;
		}

		for (int i = 3; i * i < n; i += 2) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}
}
