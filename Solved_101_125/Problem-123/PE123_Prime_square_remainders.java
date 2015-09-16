package Solved_101_125;

import java.util.ArrayList;

/**
 * Prime square remainders
 * Problem 123
 * 
 * Let pn be the nth prime: 2, 3, 5, 7, 11, ..., and let r be the remainder when
 * (pn-1)^n + (pn+1)^n is divided by pn^2.
 * 
 * For example, when n = 3, p3 = 5, and 4^3 + 6^3 = 280 == 5 mod 25.
 * 
 * The least value of n for which the remainder first exceeds 10^9 is 7037.
 * 
 * Find the least value of n for which the remainder first exceeds 10^10.
 */
public class PE123_Prime_square_remainders {
	public static void main(String[] args) {
		long start = System.nanoTime();
		
		ArrayList<Long> a = primes();
		long temp = 0, max = 0, t, result = -1;

		for (long i = 1; i < 111111; i += 2) {
			temp = a.get((int) (i - 1));
			t = (temp * i * 2l) % (temp * temp);

			if (t > max) {
				max = t;
				result = i;
			}

			if (t > 10000000000l) {
				break;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static ArrayList<Long> primes() {
		ArrayList<Long> p = new ArrayList<Long>();
		p.add(2L);
		p.add(3L);
		p.add(5L);
		p.add(7L);

		for (long i = 11; i < 250000; i += 2) {
			if (isPrime(i)) {
				p.add(i);
			}
		}

		return p;
	}

	private static int prymes() {
		int count = 4;

		for (int i = 11; i < 50000000; i += 2) {
			if (isPrime(i)) {
				count++;
			}
		}

		return count;
	}

	private static ArrayList<Integer> erast() {
		ArrayList<Integer> a = new ArrayList<Integer>();

		for (int i = 3; i < 50000000; i += 2) {
			a.add(i);
		}

		return a;
	}

	private static boolean isPrime(long n) {
		int b = (int) Math.sqrt(n);

		for (int i = 3; i <= b; i += 2) {
			if (n % i == 0) {
				n /= i--;

				return false;
			}
		}

		return true;
	}
}
