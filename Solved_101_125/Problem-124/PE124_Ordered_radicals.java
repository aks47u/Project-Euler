package Solved_101_125;

/**
 * Ordered radicals
 * Problem 124
 * 
 * The radical of n, rad(n), is the product of the distinct prime factors of n.
 * For example, 504 = 2^3 × 3^2 × 7, so rad(504) = 2 × 3 × 7 = 42.
 * 
 * If we calculate rad(n) for 1 <= n <= 10, then sort them on rad(n), and sorting
 * on n if the radical values are equal, we get:
 * Unsorted			Sorted
 * n	rad(n)		n	rad(n)	k
 * 1	1			1	1		1
 * 2	2			2	2		2
 * 3	3			4	2		3
 * 4	2			8	2		4
 * 5	5			3	3		5
 * 6	6			9	3		6
 * 7	7			5	5		7
 * 8	2			6	6		8
 * 9	3			7	7		9
 * 10	10			10	10		10
 * 
 * Let E(k) be the kth element in the sorted n column; for example, E(4) = 8 and
 * E(6) = 9.
 * 
 * If rad(n) is sorted for 1 <= n <= 100000, find E(10000).
 */
public class PE124_Ordered_radicals {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int[] primes = new int[20000];
		int[] sieve = new int[100000];
		int i;

		for (i = 2; i < 100000; i += 2) {
			sieve[i] = 1;
			sieve[i + 1] = 0;
		}

		int numprimes = 1;
		primes[0] = 2;

		while (numprimes < 20000) {
			for (i = primes[numprimes - 1] + 1; i < 100000; i++) {
				if (sieve[i] == 0) {
					break;
				}
			}

			primes[numprimes] = i;
			numprimes++;

			for (int j = i * 2; j < 100000; j += i) {
				sieve[j] = 1;
			}

			if (i > 100000) {
				break;
			}
		}

		holder[] hArray = new holder[100001];

		for (i = 0; i <= 100000; i++) {
			hArray[i] = new holder();
			hArray[i].n = i;
			hArray[i].radn = 0;
		}

		for (i = 1; i <= 100000; i++) {
			int rad = 1;
			int factor = i;

			for (int j = 0; j < numprimes; j++) {
				int prime = primes[j];

				if (factor % prime != 0) {
					continue;
				}

				rad *= prime;

				while (factor % prime == 0) {
					factor /= prime;
				}

				if (factor == 1) {
					break;
				}
			}

			hArray[i].radn = rad;
		}

		java.util.Arrays.sort(hArray);

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(hArray[10000].n);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	public static class holder implements Comparable<Object> {
		int n;
		int radn;

		public int compareTo(Object ho) {
			holder h = (holder) ho;
			if (radn < h.radn) {
				return -1;
			}

			if (radn > h.radn) {
				return 1;
			}

			if (n < h.n) {
				return -1;
			}

			if (n > h.n) {
				return 1;
			}

			return 0;
		}
	}
}
