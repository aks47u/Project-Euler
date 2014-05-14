package Solved_126_150;

/**
 * Prime pair connection
 * Problem 134
 * 
 * Consider the consecutive primes p1 = 19 and p2 = 23. It can be verified that
 * 1219 is the smallest number such that the last digits are formed by p1 whilst
 * also being divisible by p2.
 * 
 * In fact, with the exception of p1 = 3 and p2 = 5, for every pair of
 * consecutive primes, p2 > p1, there exist values of n for which the last
 * digits are formed by p1 and n is divisible by p2. Let S be the smallest of
 * these values of n.
 * 
 * Find SUM S for every pair of consecutive primes with 5 <= p1 <= 1000000.
 */
public class PE134_Prime_pair_connection {
	private static int[] primes = new int[78497];

	public static void main(String[] args) {
		long start = System.nanoTime();

		int index = 0;

		for (int i = 5; index < 78497; i++) {
			if (isPrime(i)) {
				primes[index++] = i;
			}
		}

		long result = 0;

		for (int j = 0; j < 78496; j++) {
			int size = (int) Math.round(Math.pow(10,
					Math.floor(Math.log10(primes[j])) + 1));
			result += pmod(
					(egcd(size % primes[j + 1], primes[j + 1])[0] * (primes[j + 1] - primes[j])),
					primes[j + 1])
					* size + primes[j];
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long pmod(long a, long b) {
		return (a < 0) ? (((-a / b) + 1) * b + a) : (a % b);
	}

	private static boolean isPrime(int n) {
		if (n == 2) {
			return true;
		}

		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}

	private static long[] egcd(long a, long b) {
		long r = a % b;

		if (r == 0) {
			return (new long[] { 0, 1 });
		}

		long[] s = egcd(b, r);

		return (new long[] { s[1], s[0] - (a / b) * s[1] });
	}
}
