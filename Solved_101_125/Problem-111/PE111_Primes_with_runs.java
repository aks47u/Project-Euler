package Solved_101_125;

/**
 * Primes with runs
 * Problem 111
 * 
 * Considering 4-digit primes containing repeated digits it is clear that they
 * cannot all be the same: 1111 is divisible by 11, 2222 is divisible by 22, and
 * so on. But there are nine 4-digit primes containing three ones:
 * 
 * 1117, 1151, 1171, 1181, 1511, 1811, 2111, 4111, 8111
 * 
 * We shall say that M(n, d) represents the maximum number of repeated digits
 * for an n-digit prime where d is the repeated digit, N(n, d) represents the
 * number of such primes, and S(n, d) represents the sum of these primes.
 * 
 * So M(4, 1) = 3 is the maximum number of repeated digits for a 4-digit prime
 * where one is the repeated digit, there are N(4, 1) = 9 such primes, and the
 * sum of these primes is S(4, 1) = 22275. It turns out that for d = 0, it is
 * only possible to have M(4, 0) = 2 repeated digits, but there are N(4, 0) = 13
 * such cases.
 * 
 * In the same way we obtain the following results for 4-digit primes.
 * Digit, d		M(4, d)		N(4, d)		S(4, d)
 * 0			2			13			67061
 * 1			3			9			22275
 * 2			3			1			2221
 * 3			3			12			46214
 * 4			3			2			8888
 * 5			3			1			5557
 * 6			3			1			6661
 * 7			3			9			57863
 * 8			3			1			8887
 * 9			3			7			48073
 * 
 * For d = 0 to 9, the sum of all S(4, d) is 273700.
 * 
 * Find the sum of all S(10, d).
 */
public class PE111_Primes_with_runs {
	private static int[] primes = new int[10000];
	private static int numprimes = 1;

	public static void main(String[] args) {
		long start = System.nanoTime();

		int[] sieve = new int[100000];
		int i;

		for (i = 2; i < 100000; i += 2) {
			sieve[i] = 1;
			sieve[i + 1] = 0;
		}

		primes[0] = 2;

		while (numprimes < 10000) {
			for (i = primes[numprimes - 1] + 1; i < 100000; i++) {
				if (sieve[i] == 0) {
					break;
				}
			}

			if (i >= 99999) {
				break;
			}

			primes[numprimes++] = i;

			for (int j = i * 2; j < 100000; j += i) {
				sieve[j] = 1;
			}
		}

		long result = 0;

		for (i = 0; i < 10; i++) {
			long x = S(10, i);
			result += x;
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static boolean isPrime(long num) {
		long limit = (long) (Math.sqrt((double) num)) + 1;

		for (int i = 0; i < numprimes; i++) {
			int prime = primes[i];

			if (prime > limit) {
				break;
			}

			if (num % prime == 0) {
				return false;
			}
		}

		return true;
	}

	private static long S2(int len, int digit) {
		StringBuffer sb = new StringBuffer(10);
		long total = 0;

		for (int i = 0; i < len; i++) {
			sb.append(digit);
		}

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < 10; j++) {
				String d = String.valueOf(j);
				StringBuffer sb2 = new StringBuffer(sb.toString());
				sb2.replace(i, i + 1, d);
				long num = Long.parseLong(sb2.toString());

				if (isPrime(num)) {
					total += num;
				}
			}
		}

		return total;
	}

	private static long S1(int len, int digit) {
		StringBuffer sb = new StringBuffer(10);
		long total = 0;

		for (int i = 0; i < len; i++) {
			sb.append(digit);
		}

		for (int i = 1; i < 10; i += 2) {
			if (i == 5) {
				continue;
			}

			StringBuffer sb2 = new StringBuffer().append(sb);
			sb2.replace(len - 1, len, String.valueOf(i));

			for (int j = 0; j < 10; j++) {
				StringBuffer sb3 = new StringBuffer().append(sb2);

				for (int k = 0; k < 10; k++) {
					if ((j + k) == 0) {
						continue;
					}

					sb3.replace(j, j + 1, String.valueOf(k));
					long num = Long.parseLong(sb3.toString());

					if (isPrime(num)) {
						total += num;
					}
				}
			}
		}

		return total;
	}

	private static long S0(int len) {
		long total = 0;

		for (int i = 1; i < 10; i += 2) {
			if (i == 5) {
				continue;
			}

			StringBuffer sb2 = new StringBuffer("000000000").append(i);

			for (int j = 1; j < 10; j++) {
				sb2.replace(0, 1, String.valueOf(j));
				long num = Long.parseLong(sb2.toString());

				if (isPrime(num)) {
					total += num;
				}
			}
		}

		return total;
	}

	private static long S(int len, int digit) {
		switch (digit) {
		case 1:
		case 3:
		case 4:
		case 6:
		case 7:
		case 9:
		case 5:
			return S2(len, digit);
		case 0:
			return S0(len);
		case 2:
		case 8:
			return S1(len, digit);
		}

		return 0;
	}
}
