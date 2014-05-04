package Solved_051_075;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Digit factorial chains
 * Problem 74
 * 
 * The number 145 is well known for the property that the sum of the factorial
 * of its digits is equal to 145:
 * 
 * 1! + 4! + 5! = 1 + 24 + 120 = 145
 * 
 * Perhaps less well known is 169, in that it produces the longest chain of
 * numbers that link back to 169; it turns out that there are only three such
 * loops that exist:
 * 
 * 169 -> 363601 -> 1454 -> 169
 * 871 -> 45361 -> 871
 * 872 -> 45362 -> 872
 * 
 * It is not difficult to prove that EVERY starting number will eventually get
 * stuck in a loop. For example,
 * 
 * 69 -> 363600 -> 1454 -> 169 -> 363601 (-> 1454)
 * 78 -> 45360 -> 871 -> 45361 (-> 871)
 * 540 -> 145 (-> 145)
 * 
 * Starting with 69 produces a chain of five non-repeating terms, but the
 * longest non-repeating chain with a starting number below one million is sixty
 * terms.
 * 
 * How many chains, with a starting number below one million, contain exactly
 * sixty non-repeating terms?
 */
public class PE074_Digit_factorial_chains {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int[] counts = new int[1000000];
		Arrays.fill(counts, 0);

		counts[0] = 2;
		counts[1] = 1;
		counts[2] = 1;

		int[] facts = { 1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880 };
		int result = 0;
		HashSet<Integer> chain;

		for (int i = 3; i < 1000000; i++) {
			chain = new HashSet<Integer>(60);
			chain.add(new Integer(i));
			int sum, num = i;

			while (true) {
				sum = 0;

				while (num > 0) {
					sum += facts[num % 10];
					num /= 10;
				}

				num = sum;

				if (!chain.add(new Integer(sum))) {
					if (chain.size() == 60) {
						result++;
					}

					counts[i] = chain.size();
					break;
				}

				if ((sum >= 1000000) || (counts[sum] == 0)) {
					continue;
				}

				counts[i] = chain.size() + counts[sum] - 1;

				if (counts[i] == 60) {
					result++;
				}

				break;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
