package Solved_201_225;

import java.util.Map;
import java.util.TreeMap;

/**
 * Robot Walks
 * Problem 208
 * 
 * A robot moves in a series of one-fifth circular arcs (72°), with a free
 * choice of a clockwise or an anticlockwise arc for each step, but no turning
 * on the spot.
 * 
 * One of 70932 possible closed paths of 25 arcs starting northward is
 * 
 * Given that the robot starts facing North, how many journeys of 70 arcs in
 * length can it take that return it, after the final arc, to its starting
 * position? (Any arc may be traversed multiple times.)
 */
public class PE208_Robot_Walks {
	private static int size = 70;

	public static void main(String[] args) {
		long start = System.nanoTime();

		Map<Path, Long> map1 = new TreeMap<Path, Long>();
		map1.put(new Path(), 1L);
		Map<Path, Long> map2 = new TreeMap<Path, Long>();
		Path temp = new Path();

		for (int i = 0; i < size; i++) {
			for (Map.Entry<Path, Long> pair : map1.entrySet()) {
				temp.set(pair.getKey());

				if (temp.solvableIn(size - i)) {
					temp.growLeft();
					addPath(map2, temp, pair.getValue());
					temp.set(pair.getKey());
					temp.growRight();
					addPath(map2, temp, pair.getValue());
				}
			}

			map1.clear();
			Map<Path, Long> map3 = map1;
			map1 = map2;
			map2 = map3;
		}

		long result = map1.get(new Path());

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static void addPath(Map<Path, Long> map, Path p, long c) {
		Long old = map.get(p);
		
		if (old == null) {
			map.put(new Path(p), c);
		} else {
			map.put(p, c + old);
		}
	}
}

class Path implements Comparable<Path> {
	public byte[] steps = new byte[5];
	public int rotation;

	public Path() {
	}

	public Path(Path p2) {
		set(p2);
	}

	public int compareTo(Path p2) {
		if (rotation != p2.rotation) {
			return rotation - p2.rotation;
		}
		
		for (int i = 0; i < 5; i++) {
			if (steps[i] != p2.steps[i]) {
				return steps[i] - p2.steps[i];
			}
		}

		return 0;
	}

	public void set(Path p2) {
		for (int i = 0; i < 5; i++) {
			steps[i] = p2.steps[i];
		}

		rotation = p2.rotation;
	}

	public void growLeft() {
		steps[rotation]--;
		rotation = rotation == 0 ? 4 : rotation - 1;
		reduce();
	}

	public void growRight() {
		rotation = rotation == 4 ? 0 : rotation + 1;
		steps[rotation]--;
		reduce();
	}

	private void reduce() {
		if (steps[0] < 0 || steps[1] < 0 || steps[2] < 0 || steps[3] < 0
				|| steps[4] < 0) {
			++steps[0];
			++steps[1];
			++steps[2];
			++steps[3];
			++steps[4];
		}
	}

	public boolean solvableIn(int d) {
		return d - (steps[0] + steps[1] + steps[2] + steps[3] + steps[4]) >= 0;
	}
}
