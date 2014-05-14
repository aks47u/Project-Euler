package Solved_126_150;

/**
 * Composites with prime repunit property Problem 130
 * 
 * A number consisting entirely of ones is called a repunit. We shall define
 * R(k) to be a repunit of length k; for example, R(6) = 111111.
 * 
 * Given that n is a positive integer and GCD(n, 10) = 1, it can be shown that
 * there always exists a value, k, for which R(k) is divisible by n, and let
 * A(n) be the least such value of k; for example, A(7) = 6 and A(41) = 5.
 * 
 * You are given that for all primes, p > 5, that p - 1 is divisible by A(p).
 * For example, when p = 41, A(41) = 5, and 40 is divisible by 5.
 * 
 * However, there are rare composite values for which this is also true; the
 * first five examples being 91, 259, 451, 481, and 703.
 * 
 * Find the sum of the first twenty-five composite values of n for which GCD(n,
 * 10) = 1 and n - 1 is divisible by A(n).
 */
public class PE130_Composites_with_prime_repunit_property {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int k = 0, n = 50, result = 0, count = 0;

		while (count < 25) {
			n++;

			if (n % 2 == 0 || n % 5 == 0 || isPrime(n)) {
				continue;
			}

			k = calculateMinK(n);

			if ((n - 1) % k == 0) {
				count++;
				result += n;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static boolean isPrime(int n) {
		if (n == 2) {
			return true;
		}

		if ((n == 1) || (n % 2 == 0)) {
			return false;
		}

		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}

	private static int calculateMinK(int n) {
		int k = 1, total = 10;

		while (total % (9 * n) != 1) {
			total = (total * 10) % (9 * n);
			k++;
		}

		return k;
	}
}
