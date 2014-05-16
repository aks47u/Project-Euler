package Solved_151_175;

/**
 * Exploring strings for which only one character comes lexicographically after
 * its neighbour to the left
 * Problem 158
 * 
 * Taking three different letters from the 26 letters of the alphabet, character
 * strings of length three can be formed. Examples are 'abc', 'hat' and 'zyx'.
 * When we study these three examples we see that for 'abc' two characters come
 * lexicographically after its neighbour to the left. For 'hat' there is exactly
 * one character that comes lexicographically after its neighbour to the left.
 * For 'zyx' there are zero characters that come lexicographically after its
 * neighbour to the left. In all there are 10400 strings of length 3 for which
 * exactly one character comes lexicographically after its neighbour to the
 * left.
 * 
 * We now consider strings of n <= 26 different characters from the alphabet. For
 * every n, p(n) is the number of strings of length n for which exactly one
 * character comes lexicographically after its neighbour to the left.
 * 
 * What is the maximum value of p(n)?
 */
public class PE158_Exploring_strings_for_which_only_one_character_comes_lexicographically_after_its_neighbour_to_the_left {
	private static int n;
	private static long[][][][] memo;

	public static void main(String[] args) {
		long start = System.nanoTime();

		long result = 0;

		for (int i = 0; i <= 26; i++) {
			n = i;
			memo = new long[30][30][30][3];
			result = Math.max(result, doit(0, 26, 0, 0));
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long doit(int at, int under, int over, int cur) {
		if (at == n) {
			{
				if (cur == 1) {
					return 1;
				}
			}

			return 0;
		}

		if (cur > 1) {
			return 0;
		}

		if (memo[at][under][over][cur] != 0) {
			return memo[at][under][over][cur] - 1;
		}

		long ret = 0;

		for (int i = 0; i < under; i++) {
			ret += doit(at + 1, i, over + under - i - 1, cur);
		}

		for (int i = 0; i < over; i++) {
			ret += doit(at + 1, under + i, over - i - 1, cur + 1);
		}

		memo[at][under][over][cur] = ret + 1;

		return ret;
	}
}
