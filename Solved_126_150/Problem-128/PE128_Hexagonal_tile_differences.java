package Solved_126_150;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Hexagonal tile differences
 * Problem 128
 * 
 * A hexagonal tile with number 1 is surrounded by a ring of six hexagonal
 * tiles, starting at "12 o'clock" and numbering the tiles 2 to 7 in an
 * anti-clockwise direction.
 * 
 * New rings are added in the same fashion, with the next rings being numbered 8
 * to 19, 20 to 37, 38 to 61, and so on. The diagram below shows the first three
 * rings.
 * 
 * By finding the difference between tile n and each its six neighbours we shall
 * define PD(n) to be the number of those differences which are prime.
 * 
 * For example, working clockwise around tile 8 the differences are 12, 29, 11,
 * 6, 1, and 13. So PD(8) = 3.
 * 
 * In the same way, the differences around tile 17 are 1, 17, 16, 1, 11, and 10,
 * hence PD(17) = 2.
 * 
 * It can be shown that the maximum value of PD(n) is 3.
 * 
 * If all of the tiles for which PD(n) = 3 are listed in ascending order to form
 * a sequence, the 10th tile would be 271.
 * 
 * Find the 2000th tile in this sequence.
 */
public class PE128_Hexagonal_tile_differences {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long[] values = new long[6];
		int x = 1;
		long result = 0;
		ArrayList<Long> success = new ArrayList<Long>();
		success.add(1L);

		while (true) {
			if (PD(0, x, values) == 3) {
				success.add(valueFor(0, x));
			}

			if (PD(1, x, values) == 3) {
				success.add(valueFor(1, x));
			}

			x++;

			if (success.size() >= 2000) {
				result = success.get(1999);
				break;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	public static void numbersAround(int x, int y, long[] nums) {
		nums[0] = valueFor(x, y + 1);
		nums[1] = valueFor(x - 1, y);
		nums[2] = valueFor(x - 1, y - 1);
		nums[3] = valueFor(x, y - 1);
		nums[4] = valueFor(x + 1, y);
		nums[5] = valueFor(x + 1, y + 1);
	}

	public static int PD(int x, int y, long[] nums) {
		long n = valueFor(x, y);
		numbersAround(x, y, nums);
		int count = 0;

		for (long v : nums) {
			long diff = n - v;

			if (new BigInteger(diff + "").isProbablePrime(20)) {
				count++;
			}

			if (count == 3) {
				return 3;
			}
		}

		return count;
	}

	public static long topNForRing(long r) {
		if (r == 0) {
			return 1;
		}

		return 3 * r * r - 3 * r + 2;
	}

	public static long valueFor(int x, int y) {
		if (x == 0 && y == 0) {
			return 1;
		}

		long ring = ringFor(x, y);
		char region = regionFor(x, y);
		long top = topNForRing(ring);

		if (region == 'A') {
			return top;
		} else if (region == 'B') {
			return top + ring - y;
		} else if (region == 'C') {
			return top + ring;
		} else if (region == 'D') {
			return top + ring - y;
		} else if (region == 'E') {
			return top + 2 * ring;
		} else if (region == 'F') {
			return top + 3 * ring + x;
		} else if (region == 'G') {
			return top + 3 * ring;
		} else if (region == 'H') {
			return top + 3 * ring + x;
		} else if (region == 'I') {
			return top + 4 * ring;
		} else if (region == 'J') {
			return top + 4 * ring + y;
		} else if (region == 'K') {
			return top + 5 * ring;
		} else if (region == 'L') {
			return top + 6 * ring - x;
		} else {
			return -1;
		}

	}

	public static int ringFor(int x, int y) {
		char region = regionFor(x, y);

		if (region == 'A') {
			return y;
		} else if (region == 'B') {
			return Math.abs(x) + y;
		} else if (region == 'C') {
			return Math.abs(x);
		} else if (region == 'D') {
			return Math.abs(x);
		} else if (region == 'E') {
			return Math.abs(x);
		} else if (region == 'F') {
			return Math.abs(y);
		} else if (region == 'G') {
			return Math.abs(y);
		} else if (region == 'H') {
			return x + Math.abs(y);
		} else if (region == 'I') {
			return x;
		} else if (region == 'J') {
			return x;
		} else if (region == 'K') {
			return x;
		} else if (region == 'L') {
			return y;
		}

		return -1;
	}

	public static char regionFor(int x, int y) {
		if (x == 0) {
			if (y >= 0) {
				return 'A';
			} else {
				return 'G';
			}

		} else if (y == 0) {
			if (x < 0) {
				return 'C';
			} else {
				return 'I';
			}
		} else if (x < 0 && y < 0) {
			if (x < y) {
				return 'D';
			} else {
				return 'F';
			}
		} else if (x < 0 && y > 0) {
			return 'B';
		} else if (x > 0 && y < 0) {
			return 'H';
		} else if (x > 0 && y > 0) {
			if (y > x) {
				return 'L';
			} else {
				return 'J';
			}
		} else if (x < 0 && x == y) {
			return 'E';
		} else if (x > 0 && x == y) {
			return 'K';
		}

		return 'Z';
	}
}
