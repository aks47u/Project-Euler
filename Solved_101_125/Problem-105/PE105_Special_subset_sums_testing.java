package Solved_101_125;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Special subset sums: testing
 * Problem 105
 * 
 * Let S(A) represent the sum of elements in set A of size n. We shall call it a
 * special sum set if for any two non-empty disjoint subsets, B and C, the
 * following properties are true:
 * 
 * i.	S(B) != S(C); that is, sums of subsets cannot be equal.
 * ii.	If B contains more elements than C then S(B) > S(C).
 * 
 * For example, {81, 88, 75, 42, 87, 84, 86, 65} is not a special sum set
 * because 65 + 87 + 88 = 75 + 81 + 84, whereas {157, 150, 164, 119, 79, 159,
 * 161, 139, 158} satisfies both rules for all possible subset pair combinations
 * and S(A) = 1286.
 * 
 * Using sets.txt (right click and "Save Link/Target As..."), a 4K text file
 * with one-hundred sets containing seven to twelve elements (the two examples
 * given above are the first two sets in the file), identify all the special sum
 * sets, A1, A2, ..., Ak, and find the value of S(A1) + S(A2) + ... + S(Ak).
 * 
 * NOTE: This problem is related to problems 103 and 106.
 */
public class PE105_Special_subset_sums_testing {
	public static void main(String[] args) throws Exception {
		long start = System.nanoTime();

		int result = 0;
		BufferedReader rdr = new BufferedReader(
				new FileReader("PE105_sets.txt"));

		loop: while (rdr.ready()) {
			String[] s = rdr.readLine().split(",");
			int n = s.length;
			int[] nums = new int[n];

			for (int i = 0; i < n; i++) {
				nums[i] = Integer.valueOf(s[i]);
			}

			for (int i = 0; i < (1 << n); i++) {
				for (int j = i + 1; j < (1 << n); j++) {
					if (i == 0 || (i & j) != 0) {
						continue;
					}

					int bts1 = Integer.bitCount(i);
					int bts2 = Integer.bitCount(j);
					int sm1 = 0;
					int sm2 = 0;

					for (int k = 0; k < n; k++) {
						if ((i & (1 << k)) != 0) {
							sm1 += nums[k];
						} else if ((j & (1 << k)) != 0) {
							sm2 += nums[k];
						}
					}

					if (sm1 == sm2 || (bts1 > bts2 && sm1 < sm2)
							|| (bts2 > bts1 && sm2 < sm1)) {
						continue loop;
					}
				}
			}

			for (int i : nums) {
				result += i;
			}
		}

		rdr.close();

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
