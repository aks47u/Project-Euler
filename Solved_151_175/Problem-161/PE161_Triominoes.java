package Solved_151_175;

import java.util.HashMap;

/**
 * Triominoes
 * Problem 161
 * 
 * A triomino is a shape consisting of three squares joined via the edges. There
 * are two basic forms:
 * 
 * If all possible orientations are taken into account there are six:
 * 
 * Any n by m grid for which nxm is divisible by 3 can be tiled with triominoes.
 * If we consider tilings that can be obtained by reflection or rotation from
 * another tiling as different there are 41 ways a 2 by 9 grid can be tiled with
 * triominoes:
 * 
 * In how many ways can a 9 by 12 grid be tiled in this way by triominoes?
 */
public class PE161_Triominoes {
	private static int w, h;
	private static HashMap<Long, Long> ans = new HashMap<Long, Long>();

	public static void main(String[] args) {
		long start = System.nanoTime();

		w = 9;
		h = 12;
		byte[] b = new byte[w];
		long result = ans(b, 0, 0);

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long pro(byte b[], int i) {
		return i >= b.length ? 0 : (pro(b, i + 1) << 3) + b[i];
	}

	private static long ans(byte b[], int p, int c) {
		long hsh = (pro(b, 0) << 8) + (p << 4) + c;

		if (!ans.containsKey(hsh)) {
			ans.put(hsh, ans0(b, p, c));
		}

		return ans.get(hsh);
	}

	private static long ans0(byte[] b, int p, int c) {
		if (p == h) {
			return 1;
		}

		if (c == w) {
			int min = h;

			for (byte bb : b) {
				min = Math.min(min, bb);
			}

			return ans(b, min, 0);
		}

		if (b[c] != p) {
			return ans(b, p, c + 1);
		}

		long total = 0;

		if (p + 3 <= h) {
			if (c + 1 <= w) {
				b[c] += 3;
				total += ans(b, p, c + 1);
				b[c] -= 3;
			}
		}
		if (p + 1 <= h) {
			if (c + 3 <= w) {
				if (b[c] == b[c + 1] && b[c] == b[c + 2]) {
					b[c] += 1;
					b[c + 1] += 1;
					b[c + 2] += 1;
					total += ans(b, p, c + 3);
					b[c] -= 1;
					b[c + 1] -= 1;
					b[c + 2] -= 1;
				}
			}
		}

		if (p + 2 <= h) {
			if (c + 2 <= w) {
				if (b[c] == b[c + 1]) {
					b[c] += 2;
					b[c + 1] += 1;
					total += ans(b, p, c + 2);
					b[c] -= 2;
					b[c + 1] -= 1;
				}
			}
		}

		if (p + 2 <= h) {
			if (c + 2 <= w) {
				if (b[c] == b[c + 1]) {
					b[c] += 1;
					b[c + 1] += 2;
					total += ans(b, p, c + 2);
					b[c] -= 1;
					b[c + 1] -= 2;
				}
			}
		}

		if (p + 2 <= h) {
			if (c - 1 >= 0) {
				if (b[c - 1] == b[c] + 1) {
					b[c - 1] += 1;
					b[c] += 2;
					total += ans(b, p, c + 1);
					b[c - 1] -= 1;
					b[c] -= 2;
				}
			}
		}

		if (p + 2 <= h) {
			if (c + 2 <= w) {
				if (b[c + 1] == b[c] + 1) {
					b[c + 1] += 1;
					b[c] += 2;
					total += ans(b, p, c + 2);
					b[c + 1] -= 1;
					b[c] -= 2;
				}
			}
		}

		if (p + 2 <= h) {
			if (c + 3 <= w) {
				if (b[c] == b[c + 1] && b[c] == b[c + 2]) {
					b[c] += 2;
					b[c + 1] += 2;
					b[c + 2] += 2;
					total += ans(b, p, c + 3);
					b[c] -= 2;
					b[c + 1] -= 2;
					b[c + 2] -= 2;
				}
			}
		}

		if (p + 2 <= h) {
			if (c + 4 <= w) {
				if (b[c] == b[c + 1] && b[c] == b[c + 2] && b[c] == b[c + 3]) {
					b[c] += 2;
					b[c + 1] += 2;
					b[c + 2] += 1;
					b[c + 3] += 1;
					total += ans(b, p, c + 4);
					b[c] -= 2;
					b[c + 1] -= 2;
					b[c + 2] -= 1;
					b[c + 3] -= 1;
				}
			}
		}

		return total;
	}
}
