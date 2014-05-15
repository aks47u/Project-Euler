package Solved_126_150;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Perfect Square Collection
 * Problem 142
 * 
 * Find the smallest x + y + z with integers x > y > z > 0 such that x + y, x -
 * y, x + z, x - z, y + z, y - z are all perfect squares.
 */
public class PE142_Perfect_Square_Collection {
	public static void main(String[] args) {
		long start = System.nanoTime();

		HashMap<Integer, ArrayList<Integer>> odd = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> even = new HashMap<Integer, ArrayList<Integer>>();
		int result = 0;

		for (int i = 2; i <= 1000; i += 2) {
			for (int j = 2; j <= i; j += 2) {
				int x = (i * i + j * j) / 2;
				int y = (i * i - j * j) / 2;
				putHash(even, x, y);
			}
		}

		for (int i = 1; i <= 1000; i += 2) {
			for (int j = 1; j <= i; j += 2) {
				int x = (i * i + j * j) / 2;
				int y = (i * i - j * j) / 2;
				putHash(odd, x, y);
			}
		}

		for (int x : odd.keySet()) {
			for (int y : odd.get(x)) {
				if (even.containsKey(y)) {
					for (int z : even.get(y)) {
						if (odd.get(x).contains(z)) {
							result = x + y + z;
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

	private static void putHash(HashMap<Integer, ArrayList<Integer>> map,
			int x, int y) {
		if (map.containsKey(x)) {
			map.get(x).add(y);
		} else {
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(y);
			map.put(x, list);
		}
	}
}
