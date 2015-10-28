package Solved_201_225;

import java.util.BitSet;
import java.util.Random;

/**
 * Investigating the primality of numbers of the form 2n^2-1
 * Problem 216
 * 
 * Consider numbers t(n) of the form t(n) = 2n^2-1 with n > 1. The first such
 * numbers are 7, 17, 31, 49, 71, 97, 127 and 161. It turns out that only 49 =
 * 7*7 and 161 = 7*23 are not prime. For n <= 10000 there are 2202 numbers t(n)
 * that are prime.
 * 
 * How many numbers t(n) are prime for n <= 50,000,000 ?
 */
public class PE216_Investigating_the_primality_of_numbers_of_the_form_2n2_1 {
	private static Random random = new Random();

	public static void main(String[] args) {
		long start = System.nanoTime();

		int N = 50000000;
		long Nl = N;
		int MAX = (int) Math.sqrt(2 * Nl * Nl + 1);
		BitSet sieve = new BitSet(MAX + 1);
		int lim = (int) Math.sqrt(MAX);

		for (int i = 2; i <= lim; i++) {
			if (sieve.get(i)) {
				continue;
			}

			int n = 2 * i;

			while (n <= MAX) {
				sieve.set(n);
				n += i;
			}
		}

		BitSet sieve2 = new BitSet(N + 1);

		for (int p = 3; p <= MAX; p++) {
			if (sieve.get(p)) {
				continue;
			}

			int mod8 = p % 8;

			if (mod8 == 3 || mod8 == 5) {
				continue;
			}

			long n1 = sqrtmod((p + 1) / 2, p);

			if (n1 == 0) {
				continue;
			}

			if (n1 < 0) {
				n1 += p;
			}

			long n2 = p - n1;

			for (int x = (int) n1; x <= N; x += p) {
				if (2 * x * x - 1 == p) {
					continue;
				}

				sieve2.set(x);
			}

			for (int x = (int) n2; x <= N; x += p) {
				if (2 * x * x - 1 == p) {
					continue;
				}

				sieve2.set(x);
			}

		}

		int result = 0;

		for (int n = 2; n <= N; n++) {
			if (sieve2.get(n)) {
				continue;
			}

			result++;
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long mulmod(long a, long b, long p) {
		return (a * b) % p;
	}

	private static long powmod(long a, long k, long p) {
		long res = 1;

		while (k > 0) {
			if ((k & 1) == 1) {
				res = (res * a) % p;
			}

			a = (a * a) % p;
			k >>= 1;
		}

		return res;
	}

	private static int jacobi(long a, long m) {
		int t = 1;
		long z;
		a %= m;

		while (a != 0) {
			while ((a & 1) == 0) {
				a >>= 1;

				if ((m & 7) == 3 || (m & 7) == 5) {
					t = -t;
				}
			}

			z = a;
			a = m;
			m = z;

			if ((a & 3) == 3 && (m & 3) == 3) {
				t = -t;
			}

			a %= m;
		}

		if (m == 1) {
			return t;
		}

		return 0;
	}

	private static long sqrtmod(long a, long p) {
		if (p == 2) {
			return a & 1;
		}

		a %= p;

		if (jacobi(a, p) != 1) {
			return 0;
		}

		int p8 = (int) (p & 7);

		if (p8 == 3 || p8 == 5 || p8 == 7) {
			if ((p8 & 3) == 3) {
				return powmod(a, (p + 1) / 4, p);
			}

			long x = powmod(a, (p + 3) / 8, p);
			long c = mulmod(x, x, p);

			return c == a ? x : mulmod(x, powmod(2, (p - 1) / 4, p), p);
		}

		int alpha = 0;
		long s = p - 1;

		while ((s & 1) == 0) {
			s >>= 1;
			alpha++;
		}

		long r = powmod(a, (s + 1) / 2, p);
		long r2a = mulmod(r, powmod(a, (s + 1) / 2 - 1, p), p);
		long n = 1;

		do {
			n = random.nextLong() % (p - 2) + 2;

			if (n < 0) {
				n += p;
			}
		} while (jacobi(n, p) != -1);

		long b = powmod(n, s, p);
		long J = 0;

		for (int i = 0; i < alpha - 1; i++) {
			long c = powmod(b, 2 * J, p);
			c = mulmod(r2a, c, p);
			c = powmod(c, 1L << (alpha - i - 2), p);

			if (c == p - 1) {
				J += 1L << i;
			}
		}

		return mulmod(r, powmod(b, J, p), p);
	}
}
