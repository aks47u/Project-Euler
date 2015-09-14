package Solved_101_125;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Pandigital prime sets
 * Problem 118
 * 
 * Using all of the digits 1 through 9 and concatenating them freely to form
 * decimal integers, different sets can be formed. Interestingly with the set
 * {2,5,47,89,631}, all of the elements belonging to it are prime.
 * 
 * How many distinct sets containing each of the digits one through nine exactly
 * once contain only prime elements?
 */
public class PE118_Pandigital_prime_sets {
	public static int n = 0;
	private static Set<Set<Integer>> res = new HashSet<Set<Integer>>();

	public static void main(String[] args) {
		long start = System.nanoTime();

		SortedSet<Integer> starter = new TreeSet<Integer>();
		nextNumber(starter);
		int result = res.size();

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static boolean isPrime(int num) {
		if (num == 2) {
			return true;
		}

		if (((num % 2) == 0) || num < 2) {
			return false;
		}

		for (int t = 3; t * t <= num; t += 2) {
			if ((num % t) == 0) {
				return false;
			}
		}

		return true;
	}

	private static void nextNumber(SortedSet<Integer> set) {
		int freeDigits[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		if (!set.isEmpty()) {
			for (Iterator<Integer> it = set.iterator(); it.hasNext();) {
				freeDigits = NumbersGenerator.getFreeDigits(it.next()
						.intValue(), freeDigits);
			}
		}

		if (freeDigits.length == 0) {
			res.add(set);

			return;
		}

		NumbersGenerator ng = new NumbersGenerator(freeDigits);
		List<Integer> numbers = ng.getNumbers();

		for (Integer i : numbers) {
			if (!isPrime(i.intValue())) {
				continue;
			}

			SortedSet<Integer> newSet = new TreeSet<Integer>(set);
			newSet.add(i);
			nextNumber(newSet);
		}
	}
}

class NumbersGenerator {
	private int[] digits;
	private boolean[] used;
	private int len;
	private List<Integer> numbers;
	private boolean isGenerated = false;

	public NumbersGenerator(int[] digs) {
		digits = digs;
		len = digs.length;
		used = new boolean[len];
		numbers = new ArrayList<Integer>();
	}

	public List<Integer> getNumbers() {
		if (!isGenerated) {
			nextNumber(0);
			isGenerated = true;
		}

		return numbers;
	}

	private void nextNumber(int curNum) {
		for (int i = 0; i < len; i++) {
			if (!used[i]) {
				used[i] = true;
				int newNum = curNum * 10 + digits[i];
				numbers.add(new Integer(newNum));
				nextNumber(newNum);
				used[i] = false;
			}
		}
	}

	public static int[] getFreeDigits(int num, int[] mask) {
		boolean[] used = new boolean[10];
		Arrays.sort(mask);

		do {
			used[num % 10] = true;
			num /= 10;
		} while (num > 0);

		int[] free = new int[10];
		int len = 0;

		for (int d : mask) {
			if (!used[d]) {
				free[len++] = d;
			}
		}

		return Arrays.copyOf(free, len);
	}
}
