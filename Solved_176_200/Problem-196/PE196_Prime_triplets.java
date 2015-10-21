package Solved_176_200;

/**
 * Prime triplets
 * Problem 196
 * 
 * Build a triangle from all positive integers in the following way:
 * 
 *  1
 *  2  3
 *  4  5  6
 *  7  8  9 10
 * 11 12 13 14 15
 * 16 17 18 19 20 21
 * 22 23 24 25 26 27 28
 * 29 30 31 32 33 34 35 36
 * 37 38 39 40 41 42 43 44 45
 * 46 47 48 49 50 51 52 53 54 55
 * 56 57 58 59 60 61 62 63 64 65 66
 * . . .
 * 
 * Each positive integer has up to eight neighbours in the triangle.
 * 
 * A set of three primes is called a prime triplet if one of the three primes
 * has the other two as neighbours in the triangle.
 * 
 * For example, in the second row, the prime numbers 2 and 3 are elements of
 * some prime triplet.
 * 
 * If row 8 is considered, it contains two primes which are elements of some
 * prime triplet, i.e. 29 and 31. If row 9 is considered, it contains only one
 * prime which is an element of some prime triplet: 37.
 * 
 * Define S(n) as the sum of the primes in row n which are elements of any prime
 * triplet. Then S(8)=60 and S(9)=37.
 * 
 * You are given that S(10000)=950007619.
 * 
 * Find S(5678027) + S(7208785).
 */
public class PE196_Prime_triplets {
	private static PrimeRange pr;

	public static void main(String[] args) {
		long start = System.nanoTime();

		long result = sumTriple(5678027) + sumTriple(7208785);

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long sumTriple(int row) {
		long first = 1 + (long) row * (row - 1) / 2;
		pr = new PrimeRange(first - row * 2 + 3, first + row * 3 + 3);
		long total = 0;

		for (int x = 0; x < row; x++) {
			if (isTriple(first + x, x, row)) {
				total += first + x;
			}
		}

		return total;
	}

	private static boolean isTriple(long n, int x, int y) {
		return countNeighbrs(n, x, y, -1, 0) >= 3;
	}

	private static int countNeighbrs(long n, int x, int y, int fromdir, int cnt) {
		if (x < 0 || x > y || !pr.isPrime(n)) {
			return cnt;
		}

		cnt++;

		for (int d = 0; d < 6 && cnt < 3; d++) {
			if (d != fromdir) {
				int x2 = x, y2 = y;
				long n2 = n;

				if (d <= 2) {
					n2 += d - 1 - (y - 1);
					y2--;
					x2 += d - 1;
				} else {
					n2 += d - 4 + y;
					y2++;
					x2 += d - 4;
				}

				cnt = countNeighbrs(n2, x2, y2, 5 - d, cnt);
			}
		}

		return cnt;
	}
}

class PrimeRange {
	private long first;
	private long last;
	private boolean[] sieve;

	public PrimeRange(long minp, long maxp) {
		int maxrt = (int) Math.sqrt(maxp);
		int size1 = (maxrt - 1) / 2;
		boolean[] sieve1 = new boolean[size1];

		for (int i = 0; i < size1; i++) {
			sieve1[i] = true;
		}

		int p = 3;

		do {
			for (int q = (p * 3 - 3) / 2; q < size1; q += p) {
				sieve1[q] = false;
			}

			do {
				p += 2;
			} while (!sieve1[(p - 3) / 2]);
		} while (p * p <= size1 + size1 + 1);

		first = minp | 1;
		last = (maxp - 1) | 1;
		int size = (int) (maxp - first) / 2 + 1;
		sieve = new boolean[size];

		for (int i = 0; i < size; i++) {
			sieve[i] = true;
		}

		p = 3;

		do {
			long q1 = (first - 1) / p * p + p;

			if ((q1 & 1) == 0) {
				q1 += p;
			}

			if (q1 == p) {
				q1 += p + p;
			}

			for (int q = (int) (q1 - first) / 2; q < size; q += p) {
				sieve[q] = false;
			}

			do {
				p += 2;
			} while (p <= size1 + size1 + 1 && !sieve1[(p - 3) / 2]);
		} while (p <= size1 + size1 + 1);
	}

	public boolean isPrime(long p) {
		if (p < first - 1 || p > last + 1) {
			throw new IndexOutOfBoundsException("Number out of range ["
					+ (first - 1) + "," + (last + 1) + "] : " + p);
		}

		if ((p & 1) == 0) {
			return false;
		}

		int ix = (int) (p - first) / 2;

		return sieve[ix];
	}

	public long getNextPrime(long p) {
		if ((p & 1) == 0) {
			p++;
		} else {
			p += 2;
		}

		while (!isPrime(p)) {
			p += 2;
		}

		return p;
	}
}
