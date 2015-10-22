package Solved_176_200;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

/**
 * Find the 200th prime-proof sqube containing the contiguous sub-string "200"
 * Problem 200
 * 
 * We shall define a sqube to be a number of the form, p^2.q^3, where p and q
 * are distinct primes. For example, 200 = 5^2.2^3 or 120072949 = 23^2.61^3.
 * 
 * The first five squbes are 72, 108, 200, 392, and 500.
 * 
 * Interestingly, 200 is also the first number for which you cannot change any
 * single digit to make a prime; we shall call such numbers, prime-proof. The
 * next prime-proof sqube which contains the contiguous sub-string "200" is
 * 1992008.
 * 
 * Find the 200th prime-proof sqube containing the contiguous sub-string "200".
 */
public class PE200_Find_the_200th_prime_proof_sqube_containing_the_contiguous_sub_string_200 {
	private static Random rng;
	private static char[] cc;
	private static ArrayList<BigInteger> list;
	private static boolean[] prime;
	private static String twoHundred;

	public static void main(String[] args) {
		long start = System.nanoTime();

		twoHundred = "200";
		list = new ArrayList<BigInteger>();
		cc = new char[10];
		cc[0] = '0';
		cc[1] = '1';
		cc[2] = '2';
		cc[3] = '3';
		cc[4] = '4';
		cc[5] = '5';
		cc[6] = '6';
		cc[7] = '7';
		cc[8] = '8';
		cc[9] = '9';
		rng = new Random();
		long max = 1000000;
		prime = new boolean[(int) max + 1];

		for (int i = 0; i < prime.length; i++) {
			prime[i] = true;
		}

		for (int i = 2; i < prime.length; i++) {
			if (prime[i]) {
				for (int j = 2 * i; j < prime.length; j += i) {
					prime[j] = false;
				}
			}
		}

		outer: for (long x = 2; x <= max; x++) {
			if (sqube(x, x).toString().length() > 12) {
				break;
			}

			if (isPrime(x)) {
				for (long y = x + 1; y <= max; y++) {
					if (isPrime(y)) {
						BigInteger a = sqube(x, y);
						BigInteger b = sqube(y, x);

						if (b.toString().length() > 12) {
							continue outer;
						}

						if (works(a)) {
							list.add(a);
						}

						if (works(b)) {
							list.add(b);
						}
					}
				}
			}
		}

		sort();

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(list.get(199));
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static void sort() {
		while (!sorted()) {
			bubbleSort();
		}
	}

	private static void bubbleSort() {
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).compareTo(list.get(j)) > 0) {
					BigInteger a = list.get(i);
					BigInteger b = list.get(j);
					list.set(i, b);
					list.set(j, a);
				}
			}
		}
	}

	private static boolean sorted() {
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i).compareTo(list.get(i + 1)) > 0) {
				return false;
			}
		}

		return true;
	}

	private static boolean works(BigInteger x) {
		String s = x.toString();

		if (s.indexOf(twoHundred) == -1) {
			return false;
		}

		char[] c = s.toCharArray();
		char[] t = s.toCharArray();

		if (Integer.parseInt(Character.toString(c[c.length - 1])) % 2 == 0) {
			for (int j = 1; j <= 9; j += 2) {
				t[t.length - 1] = cc[j];

				if (check(t)) {
					return false;
				}

				t[t.length - 1] = c[t.length - 1];
			}

			return true;
		}

		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j <= 9; j++) {
				if (i == 0 && j == 0) {
					continue;
				}

				t[i] = cc[j];

				if (check(t)) {
					return false;
				}

				t[i] = c[i];
			}
		}

		return true;
	}

	private static boolean check(char[] c) {
		StringBuilder sb = new StringBuilder();

		for (char a : c) {
			sb.append(a);
		}

		return isPrime(new BigInteger(sb.toString()));
	}

	private static BigInteger sqube(long x, long y) {
		return BigInteger.valueOf(x).pow(2)
				.multiply(BigInteger.valueOf(y).pow(3));
	}

	private static BigInteger generate(int x) {
		StringBuilder sb = new StringBuilder();
		sb.append(rng.nextInt(9) + 1);

		for (int i = 1; i <= x; i++) {
			sb.append(rng.nextInt(10));
		}

		return new BigInteger(sb.toString());
	}

	private static boolean witness(BigInteger a, BigInteger n) {
		BigInteger temp = n.subtract(BigInteger.ONE);
		long powersTwo = 0;
		BigInteger two = new BigInteger("2");

		while (temp.mod(two).equals(BigInteger.ZERO)) {
			powersTwo++;
			temp = temp.divide(two);
		}

		BigInteger first = a.modPow(temp, n);

		for (int i = 1; i <= powersTwo; i++) {
			BigInteger next = first.pow(2).mod(n);

			if (next.equals(BigInteger.ONE) && !first.equals(BigInteger.ONE)
					&& !first.equals(n.subtract(BigInteger.ONE))) {
				return true;
			}

			first = next;
		}

		return !first.equals(BigInteger.ONE);
	}

	private static boolean isPrime(long n) {
		return prime[(int) n];
	}

	private static boolean isPrime(BigInteger n) {
		for (int j = 1; j <= 8; j++) {
			BigInteger a = generate(n.toString().length()).mod(n);

			if (witness(a, n)) {
				return false;
			}
		}

		return true;
	}
}
