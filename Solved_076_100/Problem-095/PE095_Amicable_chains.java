package Solved_076_100;

/**
 * Amicable chains
 * Problem 95
 * 
 * The proper divisors of a number are all the divisors excluding the number
 * itself. For example, the proper divisors of 28 are 1, 2, 4, 7, and 14. As the
 * sum of these divisors is equal to 28, we call it a perfect number.
 * 
 * Interestingly the sum of the proper divisors of 220 is 284 and the sum of the
 * proper divisors of 284 is 220, forming a chain of two numbers. For this
 * reason, 220 and 284 are called an amicable pair.
 * 
 * Perhaps less well known are longer chains. For example, starting with 12496,
 * we form a chain of five numbers:
 * 
 * 12496 -> 14288 -> 15472 -> 14536 -> 14264 (-> 12496 -> ...)
 * 
 * Since this chain returns to its starting point, it is called an amicable
 * chain.
 * 
 * Find the smallest member of the longest amicable chain with no element
 * exceeding one million.
 */
public class PE095_Amicable_chains {
	private static int[] next = new int[1000000];
	private static int[] table = new int[1000000];

	public static void main(String[] args) {
		long start = System.nanoTime();

		int max = 0;
		int result = 0;
		int n, k;
		next[0] = next[1] = 0;

		for (int i = 2; i < 1000000; i++) {
			next[i] = 1;
		}

		for (int i = 2; i < 500000; i++) {
			for (int j = i << 1; j < 1000000; j += i) {
				next[j] += i;
			}
		}

		for (int i = 2; i <= 1000000; i++) {
			n = 0;
			int j = i;
			table[n++] = i;

			while (true) {
				if (j >= 1000000) {
					break;
				}

				j = next[j];

				for (k = 0; k < n; k++) {
					if (table[k] == j) {
						break;
					}
				}

				if (k != n) {
					break;
				}

				table[n++] = j;
			}

			if (j == i) {
				if (n > max) {
					max = n;
					result = j;
				}
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
