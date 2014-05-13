package Solved_101_125;

/**
 * Special subset sums: optimum
 * Problem 103
 * 
 * Let S(A) represent the sum of elements in set A of size n. We shall call it a
 * special sum set if for any two non-empty disjoint subsets, B and C, the
 * following properties are true:
 * 
 * S(B) != S(C); that is, sums of subsets cannot be equal. If B contains more
 * elements than C then S(B) > S(C).
 * 
 * If S(A) is minimised for a given n, we shall call it an optimum special sum
 * set. The first five optimum special sum sets are given below.
 * 
 * n = 1: {1}
 * n = 2: {1, 2}
 * n = 3: {2, 3, 4}
 * n = 4: {3, 5, 6, 7}
 * n = 5: {6, 9, 11, 12, 13}
 * 
 * It seems that for a given optimum set, A = {a1, a2, ... , an}, the next
 * optimum set is of the form B = {b, a1+b, a2+b, ... ,an+b}, where b is the
 * "middle" element on the previous row.
 * 
 * By applying this "rule" we would expect the optimum set for n = 6 to be A =
 * {11, 17, 20, 22, 23, 24}, with S(A) = 117. However, this is not the optimum
 * set, as we have merely applied an algorithm to provide a near optimum set.
 * The optimum set for n = 6 is A = {11, 18, 19, 20, 22, 25}, with S(A) = 115
 * and corresponding set string: 111819202225.
 * 
 * Given that A is an optimum special sum set for n = 7, find its set string.
 * 
 * NOTE: This problem is related to problems 105 and 106.
 */
public class PE103_Special_subset_sums_optimum {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int max = 50;
		int[] s = new int[7];
		int bestSum = Integer.MAX_VALUE;
		String result = "";

		for (int i = 1; i <= max; i++) {
			for (int j = i + 1; j <= max; j++) {
				for (int k = j + 1; k <= max; k++) {
					for (int l = k + 1; l <= max; l++) {
						for (int m = l + 1; m <= max; m++) {
							for (int n = m + 1; n <= max; n++) {
								for (int o = n + 1; o <= max; o++) {
									s[0] = i;
									s[1] = j;
									s[2] = k;
									s[3] = l;
									s[4] = m;
									s[5] = n;
									s[6] = o;

									if (isSpecialSumSet(s)) {
										int sum = s[0] + s[1] + s[2] + s[3]
												+ s[4] + s[5] + s[6];

										if (sum < bestSum) {
											bestSum = sum;
											result = "";

											for (int p = 0; p < s.length; p++) {
												result += s[p];
											}
										}
									}
								}
							}
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

	private static boolean isSpecialSumSet(int[] s) {
		if (s[0] + s[1] <= s[6]) {
			return false;
		}

		if (s[0] + s[1] + s[2] <= s[5] + s[6]) {
			return false;
		}

		if (s[0] + s[1] + s[2] + s[3] <= s[4] + s[5] + s[6]) {
			return false;
		}

		for (int i = 0; i < 7; i++) {
			for (int j = i + 1; j < 7; j++) {
				for (int k = j + 1; k < 7; k++) {
					for (int l = k + 1; l < 7; l++) {
						if (s[i] + s[l] == s[j] + s[k]) {
							return false;
						}
					}
				}
			}
		}

		for (int i = 0; i < 7; i++) {
			for (int j = i + 1; j < 7; j++) {
				for (int k = j + 1; k < 7; k++) {
					for (int l = k + 1; l < 7; l++) {
						for (int m = l + 1; m < 7; m++) {
							for (int n = m + 1; n < 7; n++) {
								if (s[i] + s[m] + s[n] == s[j] + s[k] + s[l]) {
									return false;
								}

								if (s[i] + s[l] + s[n] == s[j] + s[k] + s[m]) {
									return false;
								}

								if (s[i] + s[l] + s[m] == s[j] + s[k] + s[n]) {
									return false;
								}

								if (s[i] + s[k] + s[n] == s[j] + s[l] + s[m]) {
									return false;
								}

								if (s[i] + s[j] + s[n] == s[k] + s[l] + s[m]) {
									return false;
								}
							}
						}
					}
				}
			}
		}

		return true;
	}
}
