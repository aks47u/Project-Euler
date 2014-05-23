package Solved_201_225;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;

/**
 * Crack-free Walls
 * Problem 215
 * 
 * Consider the problem of building a wall out of 2×1 and 3×1 bricks
 * (horizontal×vertical dimensions) such that, for extra strength, the gaps
 * between horizontally-adjacent bricks never line up in consecutive layers,
 * i.e. never form a "running crack".
 * 
 * For example, the following 9×3 wall is not acceptable due to the running
 * crack shown in red:
 * 
 * There are eight ways of forming a crack-free 9×3 wall, written W(9,3) = 8.
 * 
 * Calculate W(32,10).
 */
public class PE215_Crack_free_Walls {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int n = 32, m = 10;
		BitSet cracks = new BitSet(n - 1);
		ArrayList<BitSet> rows = new ArrayList<BitSet>();
		long[] temp;
		generateRows(cracks, rows, -1, n);
		boolean[][] canConnect = new boolean[rows.size()][rows.size()];

		for (int i = 0; i < rows.size(); i++) {
			for (int j = 0; j <= i; j++) {
				canConnect[i][j] = !rows.get(i).intersects(rows.get(j));
				canConnect[j][i] = canConnect[i][j];
			}
		}

		long[] c = new long[rows.size()];
		Arrays.fill(c, 1);
		long[] nc = new long[rows.size()];

		for (int i = 1; i < m; i++) {
			Arrays.fill(nc, 0);

			for (int j = 0; j < nc.length; j++) {
				for (int k = 0; k < nc.length; k++) {
					if (canConnect[j][k]) {
						nc[k] += c[j];
					}
				}
			}

			temp = c;
			c = nc;
			nc = temp;
		}

		long result = 0;

		for (long l : c) {
			result += l;
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static void generateRows(BitSet r, Collection<BitSet> rows,
			int pos, int n) {
		if (pos >= n - 4) {
			if (pos == n - 4 || pos == n - 3) {
				rows.add(r);
			}

			return;
		}

		BitSet r2 = (BitSet) r.clone();
		r2.set(pos + 2);
		generateRows(r2, rows, pos + 2, n);
		BitSet r3 = (BitSet) r.clone();
		r3.set(pos + 3);
		generateRows(r3, rows, pos + 3, n);

		return;
	}
}
