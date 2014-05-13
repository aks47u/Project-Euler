package Solved_101_125;

/**
 * Special subset sums: meta-testing
 * Problem 106
 * 
 * Let S(A) represent the sum of elements in set A of size n. We shall call it a
 * special sum set if for any two non-empty disjoint subsets, B and C, the
 * following properties are true:
 * 
 * i.	S(B) != S(C); that is, sums of subsets cannot be equal.
 * ii.	If B contains more elements than C then S(B) > S(C).
 * 
 * For this problem we shall assume that a given set contains n strictly
 * increasing elements and it already satisfies the second rule.
 * 
 * Surprisingly, out of the 25 possible subset pairs that can be obtained from a
 * set for which n = 4, only 1 of these pairs need to be tested for equality
 * (first rule). Similarly, when n = 7, only 70 out of the 966 subset pairs need
 * to be tested.
 * 
 * For n = 12, how many of the 261625 subset pairs that can be obtained need to
 * be tested for equality?
 * 
 * NOTE: This problem is related to problems 103 and 105.
 */
public class PE106_Special_subset_sums_meta_testing {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int result = 0;
		int n = 12;

		for (int x = 1; x < (1 << n); x++) {
			for (int y = x + 1; y < (1 << n); y++) {
				if ((x & y) == 0) {
					int d1 = 0, d2 = 0;

					for (int j = 0; j < n; j++) {
						if ((x & (1 << j)) > 0) {
							d1++;
						}
					}

					for (int j = 0; j < n; j++) {
						if ((y & (1 << j)) > 0) {
							d2++;
						}
					}

					if (d1 == d2) {
						int t = 0;
						boolean ok1 = false, ok2 = false;

						for (int j = 0; j < n; j++) {
							if ((x & (1 << j)) > 0) {
								t++;
							}

							if ((y & (1 << j)) > 0) {
								t--;
							}

							if (t > 0) {
								ok1 = true;
							}

							if (t < 0) {
								ok2 = true;
							}
						}

						if ((ok1 && ok2) || (!ok1 && !ok2)) {
							result++;
						}
					}
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
