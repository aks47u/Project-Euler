package Solved_076_100;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Product-sum numbers
 * Problem 88
 * 
 * A natural number, N, that can be written as the sum and product of a given
 * set of at least two natural numbers, {a1, a2, ... , ak} is called a
 * product-sum number: N = a1 + a2 + ... + ak = a1 × a2 × ... × ak.
 * 
 * For example, 6 = 1 + 2 + 3 = 1 × 2 × 3.
 * 
 * For a given set of size, k, we shall call the smallest N with this property a
 * minimal product-sum number. The minimal product-sum numbers for sets of size,
 * k = 2, 3, 4, 5, and 6 are as follows.
 * 
 * k=2: 4 = 2 × 2 = 2 + 2
 * k=3: 6 = 1 × 2 × 3 = 1 + 2 + 3
 * k=4: 8 = 1 × 1 × 2 × 4 = 1 + 1 + 2 + 4
 * k=5: 8 = 1 × 1 × 2 × 2 × 2 = 1 + 1 + 2 + 2 + 2
 * k=6: 12 = 1 × 1 × 1 × 1 × 2 × 6 = 1 + 1 + 1 + 1 + 2 + 6
 * 
 * Hence for 2<=k<=6, the sum of all the minimal product-sum numbers is 4+6+8+12 =
 * 30; note that 8 is only counted once in the sum.
 * 
 * In fact, as the complete set of minimal product-sum numbers for 2<=k<=12 is {4,
 * 6, 8, 12, 15, 16}, the sum is 61.
 * 
 * What is the sum of all the minimal product-sum numbers for 2<=k<=12000?
 */
public class PE088_Product_sum_numbers {
	private static ArrayList<HashSet<Integer>> sets = new ArrayList<HashSet<Integer>>();

	public static void main(String[] args) {
		long start = System.nanoTime();

		int[] minimum = new int[12001];

		for (int i = 0; i < 12001; i++) {
			minimum[i] = 999999;
		}

		sets.add(new HashSet<Integer>());
		sets.add(new HashSet<Integer>());
		HashSet<Integer> one = new HashSet<Integer>();
		one.add(1);
		sets.add(one);
		sets.add(one);

		for (int i = 4; i <= 15000; i++) {
			find(i);

			for (Integer a : sets.get(i)) {
				if (a <= 12000 && i < minimum[a]) {
					minimum[a] = i;
				}
			}
		}

		HashSet<Integer> results = new HashSet<Integer>();

		for (int i = 2; i <= 12000; i++) {
			results.add(minimum[i]);
		}

		int result = 0;

		for (Integer a : results) {
			result += a;
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static void find(int number) {
		sets.add(new HashSet<Integer>());
		sets.get(number).add(1);

		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0) {
				for (Integer pair : sets.get(i)) {
					for (Integer pair2 : sets.get(number / i)) {
						int sum = pair + pair2;
						sum += (number - i - number / i);
						sets.get(number).add(sum);
					}
				}
			}
		}
	}
}
