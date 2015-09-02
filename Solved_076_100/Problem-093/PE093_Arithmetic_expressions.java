package Solved_076_100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Arithmetic expressions
 * Problem 93
 * 
 * By using each of the digits from the set, {1, 2, 3, 4}, exactly once, and
 * making use of the four arithmetic operations (+, -, *, /) and
 * brackets/parentheses, it is possible to form different positive integer
 * targets.
 * 
 * For example,
 * 
 * 8 = (4 * (1 + 3)) / 2
 * 14 = 4 * (3 + 1 / 2)
 * 19 = 4 * (2 + 3) - 1
 * 36 = 3 * 4 * (2 + 1)
 * 
 * Note that concatenations of the digits, like 12 + 34, are not allowed.
 * 
 * Using the set, {1, 2, 3, 4}, it is possible to obtain thirty-one different
 * target numbers of which 36 is the maximum, and each of the numbers 1 to 28
 * can be obtained before encountering the first non-expressible number.
 * 
 * Find the set of four distinct digits, a < b < c < d, for which the longest
 * set of consecutive positive integers, 1 to n, can be obtained, giving your
 * answer as a string: abcd.
 */
public class PE093_Arithmetic_expressions {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int[] nums = { 1, 2, 3, 4 };
		double eps = 1e-9;
		int maxConsec = 0;
		String result = "";

		do {
			List<Double> list = new ArrayList<Double>();

			for (int i : nums) {
				list.add((double) i);
			}

			Set<List<Double>> bigSet = reduce(list);
			bigSet = reduce(bigSet);
			bigSet = reduce(bigSet);
			Set<Integer> finalList = new TreeSet<Integer>();

			for (List<Double> l : bigSet) {
				double x = Math.abs(l.get(0));
				int xint = (int) Math.round(x);

				if (Math.abs(xint - x) < eps) {
					finalList.add(xint);
				}
			}

			int i = 0;

			while (finalList.contains(++i))
				;

			if (i - 1 > maxConsec) {
				maxConsec = i - 1;
				result = "";

				for (int j = 0; j < nums.length; j++) {
					result += nums[j];
				}
			}
		} while (next(nums, 3));

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static boolean next(int[] nums, int pos) {
		if (++nums[pos] <= 9 - (nums.length - 1 - pos)) {
			return true;
		}

		if (pos == 0) {
			return false;
		}

		if (!next(nums, pos - 1)) {
			return false;
		}

		nums[pos] = nums[pos - 1] + 1;

		return true;
	}

	private static List<Double> makeFrom(List<Double> frm, double elem) {
		List<Double> ret = new ArrayList<Double>();
		ret.addAll(frm);
		ret.add(elem);

		return ret;
	}

	private static Set<List<Double>> reduce(List<Double> s) {
		Set<List<Double>> ret = new HashSet<List<Double>>();

		for (int i = 0; i < s.size(); i++) {
			for (int j = i + 1; j < s.size(); j++) {
				List<Double> newList = new ArrayList<Double>();

				for (int k = 0; k < s.size(); k++) {
					if (k == i || k == j) {
						continue;
					}

					newList.add(s.get(k));
				}

				double n1 = s.get(i);
				double n2 = s.get(j);
				ret.add(makeFrom(newList, n1 + n2));
				ret.add(makeFrom(newList, n1 - n2));
				ret.add(makeFrom(newList, n2 - n1));
				ret.add(makeFrom(newList, n1 * n2));

				if (n2 != 0) {
					ret.add(makeFrom(newList, n1 / n2));
				}

				if (n1 != 0) {
					ret.add(makeFrom(newList, n2 / n1));
				}
			}
		}

		return ret;
	}

	private static Set<List<Double>> reduce(Set<List<Double>> set) {
		Set<List<Double>> ret = new HashSet<List<Double>>();

		for (List<Double> l : set) {
			ret.addAll(reduce(l));
		}

		return ret;
	}
}
