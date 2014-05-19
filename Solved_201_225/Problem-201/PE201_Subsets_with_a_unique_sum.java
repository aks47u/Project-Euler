package Solved_201_225;

/**
 * Subsets with a unique sum
 * Problem 201
 * 
 * For any set A of numbers, let sum(A) be the sum of the elements of A.
 * Consider the set B = {1,3,6,8,10,11}. There are 20 subsets of B containing
 * three elements, and their sums are:
 * 
 * sum({1,3,6}) = 10,
 * sum({1,3,8}) = 12,
 * sum({1,3,10}) = 14,
 * sum({1,3,11}) = 15,
 * sum({1,6,8}) = 15,
 * sum({1,6,10}) = 17,
 * sum({1,6,11}) = 18,
 * sum({1,8,10}) = 19,
 * sum({1,8,11}) = 20,
 * sum({1,10,11}) = 22,
 * sum({3,6,8}) = 17,
 * sum({3,6,10}) = 19,
 * sum({3,6,11}) = 20,
 * sum({3,8,10}) = 21,
 * sum({3,8,11}) = 22,
 * sum({3,10,11}) = 24,
 * sum({6,8,10}) = 24,
 * sum({6,8,11}) = 25,
 * sum({6,10,11}) = 27,
 * sum({8,10,11}) = 29.
 * 
 * Some of these sums occur more than once, others are unique. For a set A, let
 * U(A,k) be the set of unique sums of k-element subsets of A, in our example we
 * find U(B,3) = {10,12,14,18,21,25,27,29} and sum(U(B,3)) = 156.
 * 
 * Now consider the 100-element set S = {1^2, 2^2, ... , 100^2}. S has
 * 100891344545564193334812497256 50-element subsets.
 * 
 * Determine the sum of all integers which are the sum of exactly one of the
 * 50-element subsets of S, i.e. find sum(U(S,50)).
 */
public class PE201_Subsets_with_a_unique_sum {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int x = 100, y = 50, l = y + 1, result = x * x * x / 3 + x * x / 2 + x
				/ 6 - y * y * y / 3 - y * y / 2 - y / 6;
		int[] r = new int[result * l + l];

		for (int i = r[0] = 1, d = l + 1; i <= x; d = l * ++i * i + 1) {
			for (int a = r.length - d - 1; a >= 0; r[a + d] += r[a--]) {
				;
			}
		}
		for (int i = result = 0; ++i < r.length / l; result += r[i * l + y] == 1 ? i
				: 0) {
			;
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
