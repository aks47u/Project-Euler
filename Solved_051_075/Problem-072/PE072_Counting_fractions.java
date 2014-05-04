package Solved_051_075;

/**
 * Counting fractions
 * Problem 72
 * 
 * Consider the fraction, n/d, where n and d are positive integers. If n<d and
 * HCF(n,d)=1, it is called a reduced proper fraction.
 * 
 * If we list the set of reduced proper fractions for d <= 8 in ascending order
 * of size, we get:
 * 
 * 1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3,
 * 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
 * 
 * It can be seen that there are 21 elements in this set.
 * 
 * How many elements would be contained in the set of reduced proper fractions
 * for d <= 1,000,000?
 */
public class PE072_Counting_fractions {
	private int size = 1000001, k = 3;
	private long result = 0;
	private double[] tots = new double[size];

	public static void main(String[] args) {
		long start = System.nanoTime();

		new PE072_Counting_fractions();

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	public PE072_Counting_fractions() {
		solve();
	}

	public void solve() {
		for (int i = 0; i < size; i++) {
			tots[i] = i;
		}

		specialSieve(2);

		while (k < size) {
			specialSieve(k);
			k = findNext(k);
		}

		for (int i = 2; i < size; i++) {
			result += tots[i];
		}

		System.out.println(result);
	}

	private void specialSieve(int k) {
		for (int p = k; p < size; p += k) {
			tots[p] *= (k - 1) / (double) k;
		}
	}

	private int findNext(int m) {
		m += 2;

		while (m < size && tots[m] != m) {
			m += 2;
		}

		return m;
	}
}
