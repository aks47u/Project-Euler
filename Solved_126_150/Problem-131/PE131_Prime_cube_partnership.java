package Solved_126_150;

/**
 * Prime cube partnership
 * Problem 131
 * 
 * There are some prime values, p, for which there exists a positive integer, n,
 * such that the expression n^3 + pn^2 is a perfect cube.
 * 
 * For example, when p = 19, 8^3 + 8^2×19 = 12^3.
 * 
 * What is perhaps most surprising is that for each prime with this property the
 * value of n is unique, and there are only four such primes below one-hundred.
 * 
 * How many primes below one million have this remarkable property?
 */
public class PE131_Prime_cube_partnership {
	private static int MAX_N = 1000000;
	private static int MAX_CNT_PRIMES = 300000;
	private static int cnt_primes = 0;
	private static int[] ind_prime = new int[MAX_N + 1];
	private static int[] primes = new int[MAX_CNT_PRIMES + 1];

	public static void main(String[] args) {
		long start = System.nanoTime();

		sieve();
		int[] marked = new int[MAX_N + 1];
		int result = 0;

		for (int A = 1; A <= 1000; A++) {
			for (int B = A + 1; B <= 1000; B++) {
				int s = B * B * B - A * A * A;

				if (s < MAX_N && marked[s] == 0 && ind_prime[s] > 0) {
					result++;
					marked[s] = 1;
				}
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static void sieve() {
		for (int i = 0; i < ind_prime.length; i++) {
			ind_prime[i] = -1;
		}

		cnt_primes = 0;
		int k = 2;

		while (k <= MAX_N) {
			while (k <= MAX_N && ind_prime[k] == 0) {
				k++;
			}

			if (k > MAX_N) {
				break;
			}

			ind_prime[k] = cnt_primes + 1;
			primes[cnt_primes + 1] = k;
			cnt_primes++;
			int t = k + k;

			while (t <= MAX_N) {
				ind_prime[t] = 0;
				t += k;
			}

			k++;
		}
	}
}
