package Solved_176_200;

/**
 * Tri-colouring a triangular grid
 * Problem 189
 * 
 * Consider the following configuration of 64 triangles:
 * 
 * We wish to colour the interior of each triangle with one of three colours:
 * red, green or blue, so that no two neighbouring triangles have the same
 * colour. Such a colouring shall be called valid. Here, two triangles are said
 * to be neighbouring if they share an edge. Note: if they only share a vertex,
 * then they are not neighbours.
 * 
 * For example, here is a valid colouring of the above grid:
 * 
 * A colouring C' which is obtained from a colouring C by rotation or reflection
 * is considered distinct from C unless the two are identical.
 * 
 * How many distinct valid colourings are there for the above configuration?
 */
public class PE189_Tri_colouring_a_triangular_grid {
	private static long[][] count = new long[9][];

	public static void main(String[] args) {
		long start = System.nanoTime();

		int limit = 8;
		long result = solve("", limit);

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long solve(String in, int limit) {
		long result = 0;
		int len = in.length();

		if (count[len] == null) {
			count[len] = new long[high(3, len)];
		}

		if (len == 0) {
			result += solve("0", limit);
			result += solve("1", limit);
			result += solve("2", limit);
		} else if (len < limit) {
			char[] oben = in.toCharArray();
			int z = 0;
			int inverted = 0;

			for (int i = 0; i < len; i++) {
				z = z * 3;
				z = z + ((3 - (oben[0]) + (oben[i])) % 3);
				inverted = inverted * 3;
				inverted = inverted
						+ ((3 - (oben[len - 1]) + (oben[len - 1 - i])) % 3);
			}

			z = Math.min(z, inverted);

			if (count[len][z] == 0) {
				for (int i = 0; i < high(2, len); i++) {
					char[] unten = new char[len];
					int k = i;

					for (int j = 0; j < len; j++) {
						int add = k % 2;
						k = k >> 1;
						unten[j] = (char) ((oben[j] + 1 + add) % 3 + '0');
					}

					result += solveB("", unten, limit);
				}

				count[len][z] = result;
			} else {
				result = count[len][z];
			}
		} else {
			result += 1L;
		}

		return result;
	}

	private static long solveB(String in, char[] below, int limit) {
		long result = 0;
		int len = in.length();

		if (len == below.length) {
			result += solve(in + (char) (((int) below[len - 1] + 1) % 3 + '0'),
					limit);
			result += solve(in + (char) (((int) below[len - 1] + 2) % 3 + '0'),
					limit);
		} else if (len == 0 || below[len - 1] == below[len]) {
			result += solveB(in + (char) (((int) below[len] + 1) % 3 + '0'),
					below, limit);
			result += solveB(in + (char) (((int) below[len] + 2) % 3 + '0'),
					below, limit);
		} else {
			result += solveB(
					in
					+ (char) ((99 - (int) (below[len - 1] + below[len])) % 3 + '0'),
					below, limit);
		}

		return result;
	}

	private static int high(int i, int j) {
		if (j == 0) {
			return 1;
		}

		int result = 1;

		for (int k = 0; k < j; k++) {
			result *= i;
		}

		return result;
	}
}
