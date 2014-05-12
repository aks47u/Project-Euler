package Solved_076_100;

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
