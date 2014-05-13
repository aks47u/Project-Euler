package Solved_101_125;

import java.util.HashSet;
import java.util.Set;

/**
 * Palindromic sums
 * Problem 125
 * 
 * The palindromic number 595 is interesting because it can be written as the
 * sum of consecutive squares: 6^2 + 7^2 + 8^2 + 9^2 + 10^2 + 11^2 + 12^2.
 * 
 * There are exactly eleven palindromes below one-thousand that can be written
 * as consecutive square sums, and the sum of these palindromes is 4164. Note
 * that 1 = 0^2 + 1^2 has not been included as this problem is concerned with
 * the squares of positive integers.
 * 
 * Find the sum of all the numbers less than 10^8 that are both palindromic and
 * can be written as the sum of consecutive squares.
 */
public class PE125_Palindromic_sums {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long result = 0;
		Set<Integer> values = new HashSet<Integer>();
		int[] squares = new int[7071];

		for (int k = 0; k < squares.length; k++) {
			squares[k] = k * k;
		}

		for (int k = 1; k < squares.length; k++) {
			int count = squares[k];

			for (int j = k + 1; j < squares.length; j++) {
				count += squares[j];

				if (count >= 100000000) {
					break;
				}

				if (isPalindrome(count)) {
					if (values.add(count)) {
						result += count;
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

	private static boolean isPalindrome(int n) {
		StringBuilder s = new StringBuilder();
		s.append(n);
		String s1 = s.toString();
		s.reverse();

		return s1.equals(s.toString());
	}
}
