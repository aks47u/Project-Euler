package Solved_176_200;

/**
 * Coloured Configurations
 * Problem 194
 * 
 * Consider graphs built with the units A: and B: , where the units are glued
 * along the vertical edges as in the graph .
 * 
 * A configuration of type (a,b,c) is a graph thus built of a units A and b
 * units B, where the graph's vertices are coloured using up to c colours, so
 * that no two adjacent vertices have the same colour. The compound graph above
 * is an example of a configuration of type (2,2,6), in fact of type (2,2,c) for
 * all c >= 4.
 * 
 * Let N(a,b,c) be the number of configurations of type (a,b,c).
 * For example, N(1,0,3) = 24, N(0,2,4) = 92928 and N(2,2,3) = 20736.
 * 
 * Find the last 8 digits of N(25,75,1984).
 */
public class PE194_Coloured_Configurations {
	private static long mod = 100000000;
	private static boolean[][] conn = new boolean[][] {
		{ false, true, true, false, false, true, false },
		{ true, false, false, false, true, false, false },
		{ true, false, false, true, false, true, false },
		{ false, false, true, false, true, false, false },
		{ false, true, false, true, false, false, true },
		{ true, false, true, false, false, false, true },
		{ false, false, false, false, true, true, false } };

	public static void main(String[] args) {
		long start = System.nanoTime();

		int typeA = 25, typeB = 75, colors = 1984;
		long result = N(typeA, typeB, colors);

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long N(int a, int b, int c) {
		int[] arr = new int[7];
		long waysA = 0, waysB = 0;

		for (int i = 3; i <= Math.min(7, c); i++) {
			arr[0] = 1;
			arr[1] = 2;
			long[] t = recurse(i, 3, arr, 2);
			long mult = comb(c - 2, i - 2);
			waysA += (t[0] * mult) % mod;

			if (waysA >= mod) {
				waysA -= mod;
			}

			waysB += (t[1] * mult) % mod;

			if (waysB >= mod) {
				waysB -= mod;
			}
		}

		long[][] dp = new long[a + 1][b + 1];
		dp[0][0] = 1;

		for (int j = 1; j <= b; j++) {
			dp[0][j] = (dp[0][j - 1] * waysB) % mod;
		}

		for (int i = 1; i <= a; i++) {
			dp[i][0] = (dp[i - 1][0] * waysA) % mod;

			for (int j = 1; j <= b; j++) {
				dp[i][j] = ((dp[i - 1][j] * waysA) % mod + (dp[i][j - 1] * waysB)
						% mod)
						% mod;
			}
		}
		
		return ((((dp[a][b] * c) % mod) * (c - 1)) % mod) % mod;
	}

	private static long[] recurse(int n, int vis, int[] arr, int idx) {
		if (idx == 7) {
			if (vis + 1 != 1 << n) {
				return new long[] { 0, 0 };
			}

			if (arr[1] == arr[6]) {
				return new long[] { 0, 1 };
			}

			else {
				return new long[] { 1, 1 };
			}
		}

		long[] ans = new long[2];

		for (int i = 1; i <= n; i++) {
			boolean ok = true;

			for (int j = 0; j < idx; j++) {
				if (conn[idx][j]) {
					if (i == arr[j]) {
						ok = false;
						break;
					}
				}
			}

			if (ok) {
				arr[idx] = i;
				long[] temp = recurse(n, vis | (1 << (i - 1)), arr, idx + 1);
				arr[idx] = 0;
				ans[0] += temp[0];
				ans[1] += temp[1];

				if (ans[0] >= mod) {
					ans[0] -= mod;
				}

				if (ans[1] >= mod) {
					ans[1] -= mod;
				}
			}
		}

		return ans;
	}

	private static long comb(int n, int k) {
		int a = Math.min(k, n - k);
		long res = 1;

		for (int i = 1; i <= a; i++) {
			res *= n--;
			res /= i;
		}

		return res % mod;
	}
}
