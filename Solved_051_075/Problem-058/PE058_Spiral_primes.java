package Solved_051_075;

/**
 * Spiral primes
 * Problem 58
 * 
 * Starting with 1 and spiralling anticlockwise in the following way, a square
 * spiral with side length 7 is formed.
 * 
 * 37 36 35 34 33 32 31
 * 38 17 16 15 14 13 30
 * 39 18  5  4  3 12 29
 * 40 19  6  1  2 11 28
 * 41 20  7  8  9 10 27
 * 42 21 22 23 24 25 26
 * 43 44 45 46 47 48 49
 * 
 * It is interesting to note that the odd squares lie along the bottom right
 * diagonal, but what is more interesting is that 8 out of the 13 numbers lying
 * along both diagonals are prime; that is, a ratio of 8/13 â‰ˆ 62%.
 * 
 * If one complete new layer is wrapped around the spiral above, a square spiral
 * with side length 9 will be formed. If this process is continued, what is the
 * side length of the square spiral for which the ratio of primes along both
 * diagonals first falls below 10%?
 */
public class PE058_Spiral_primes {
	private Primes pri = new Primes(10000);
	private static long start;

	public static void main(String[] args) {
		start = System.nanoTime();

		@SuppressWarnings("unused")
		PE058_Spiral_primes sp = new PE058_Spiral_primes();

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	public PE058_Spiral_primes() {
		int count = 0, result = 3;

		do {
			if (pri.isPrime(result * result - result + 1)) {
				count++;
			}

			if (pri.isPrime(result * result - 2 * result + 2)) {
				count++;
			}

			if (pri.isPrime(result * result - 3 * result + 3)) {
				count++;
			}

			if (10 * count < 2 * (result - 1) + 1) {
				break;
			}

			result += 2;
		} while (true);

		System.out.println(result);
	}
}

class Primes {
	int[] primes;
	int nPrimes;

	public Primes(int n) {
		primes = new int[n + 1];
		primes[1] = 2;
		primes[2] = 3;
		nPrimes = 2;
		int p = 3;

		while (nPrimes < n) {
			p = primes[++nPrimes] = nextPrime(p);
		}
	}

	private int nextPrime(int p) {
		do {
			p += 2;
		} while (!isPrime(p));

		return p;
	}

	public boolean isPrime(int n) {
		int root = (int) Math.sqrt(n + 1);

		for (int i = 1; primes[i] <= root; i++) {
			if (n % primes[i] == 0) {
				return false;
			}
		}

		return true;
	}

	public int[] getPrimes() {
		return primes;
	}
}
