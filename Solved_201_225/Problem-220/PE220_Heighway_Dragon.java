package Solved_201_225;

/**
 * Heighway Dragon
 * Problem 220
 * 
 * Let D0 be the two-letter string "Fa". For n>=1, derive Dn from Dn-1 by the
 * string-rewriting rules:
 * 
 * "a" -> "aRbFR" "b" -> "LFaLb"
 * 
 * Thus, D0 = "Fa", D1 = "FaRbFR", D2 = "FaRbFRRLFaLbFR", and so on.
 * 
 * These strings can be interpreted as instructions to a computer graphics
 * program, with "F" meaning "draw forward one unit", "L" meaning
 * "turn left 90 degrees", "R" meaning "turn right 90 degrees", and "a" and "b"
 * being ignored. The initial position of the computer cursor is (0,0), pointing
 * up towards (0,1).
 * 
 * Then Dn is an exotic drawing known as the Heighway Dragon of order n. For
 * example, D10 is shown below; counting each "F" as one step, the highlighted
 * spot at (18,16) is the position reached after 500 steps.
 * 
 * What is the position of the cursor after 10^12 steps in D50 ? Give your
 * answer in the form x,y with no spaces.
 */
public class PE220_Heighway_Dragon {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long[] res = getDisplacement(1000000000000L, 50);
		String result = res[2] + "," + res[3];

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long[] getDisplacement(long distance, int generation) {
		if (generation == 0) {
			if (distance == 0) {
				return new long[] { 0, 1, 0, 0 };
			} else if (distance == 1) {
				return new long[] { 0, 1, 0, 1 };
			} else {
				throw new RuntimeException("Travel past end");
			}
		}

		long halflen = 1L << (generation - 1);
		boolean secondHalf = distance > halflen;

		if (secondHalf) {
			distance = halflen + halflen - distance;
		}

		long[] ret = getDisplacement(distance, generation - 1);
		long ex = ret[0], ey = ret[1];
		ret[0] = ex + ey;
		ret[1] = ey - ex;

		if (secondHalf) {
			long px = ret[2], py = ret[3];
			ret[2] = ex + ey - py;
			ret[3] = ey - ex + px;
		}

		return ret;
	}
}
