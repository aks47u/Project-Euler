package Solved_026_050;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

/**
 * Prime permutations
 * Problem 49
 * 
 * The arithmetic sequence, 1487, 4817, 8147, in which each of the terms
 * increases by 3330, is unusual in two ways: (i) each of the three terms are
 * prime, and, (ii) each of the 4-digit numbers are permutations of one another.
 * 
 * There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes,
 * exhibiting this property, but there is one other 4-digit increasing sequence.
 * 
 * What 12-digit number do you form by concatenating the three terms in this
 * sequence?
 */
public class PE049_Prime_permutations {
	static ArrayList<Integer> h = new ArrayList<Integer>();

	public static void main(String[] arg) {
		long start = System.nanoTime();

		for (int i = 1000; i < 10000; i++) {
			if (prime(i)) {
				h.add(i);
			}
		}

		for (int i = 0; i < h.size(); i++) {
			int N = Integer.parseInt("" + h.get(i));
			if (number(N) == 3) {
				view(N);
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println("\nRuntime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static int[] permut(int n) {
		int t[] = new int[10];
		String s = "" + n;

		for (int i = 0; i < s.length(); i++) {
			int index = s.charAt(i) - '0';
			t[index]++;
		}

		return t;
	}

	private static boolean prime(int n) {
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}

	private static int number(int n) {
		int sum = 1;

		for (int i = 0; i < h.size(); i++) {
			int k = Integer.parseInt("" + h.get(i));

			if (k > n && Arrays.equals(permut(k), permut(n))) {
				sum++;
			}
		}

		return sum;
	}

	private static void view(int n) {
		Vector<Integer> v = new Vector<Integer>();
		v.add(n);

		for (int i = 0; i < h.size(); i++) {
			int k = Integer.parseInt("" + h.get(i));

			if (k > n && Arrays.equals(permut(k), permut(n))) {
				v.add(k);
			}
		}

		if (2 * v.get(1) == v.get(0) + v.get(2)) {
			System.out.print("" + v.get(0) + v.get(1) + v.get(2));
		}
	}
}
