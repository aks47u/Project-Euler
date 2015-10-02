package Solved_151_175;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Writing 1/2 as a sum of inverse squares
 * Problem 152
 * 
 * There are several ways to write the number 1/2 as a sum of inverse squares
 * using distinct integers.
 * 
 * For instance, the numbers {2,3,4,5,7,12,15,20,28,35} can be used:
 * 
 * In fact, only using integers between 2 and 45 inclusive, there are exactly
 * three ways to do it, the remaining two being: {2,3,4,6,7,9,10,20,28,35,36,45}
 * and {2,3,4,6,7,9,12,15,28,30,35,36,45}.
 * 
 * How many ways are there to write the number 1/2 as a sum of inverse squares
 * using distinct integers between 2 and 80 inclusive?
 */
public class PE152_Writing_1_2_as_a_sum_of_inverse_squares {
	private static int L = 80, rL = (int) Math.round(Math.sqrt(L));
	private static int[] ps, sort;
	private static int[] base = new int[L + 1];
	private static int[] have = new int[L + 2];
	private static int[][][] f = new int[L + 1][][];
	private static boolean[] used = new boolean[L + 1];
	private static boolean[] prime = new boolean[L + 1];
	private static long result = 0;

	public static void main(String[] args) {
		long start = System.nanoTime();

		findPrimes();
		Arrays.fill(base, 0);

		for (int p : ps) {
			for (int pp = p; pp <= L; pp *= p) {
				base[pp] = p;
			}
		}

		for (int i = 2; i <= L; ++i) {
			f[i] = split(i);
		}

		Arrays.fill(used, false);
		Arrays.fill(have, 0);
		have[2] = 2;
		int w = 0;

		for (int i = L; i >= 2; --i) {
			if (!used[i]) {
				++w;
			}
		}

		sort = new int[w];

		while (w > 0) {
			for (int i = L; i >= 2; --i) {
				if (!used[i] && base[i] > 0 && locks(i) == 0) {
					used[i] = true;
					sort[--w] = i;
				}
			}

			int best_i = -1;
			long best_f = -1;

			for (int i = L; i >= 2; --i) {
				if (!used[i]) {
					long f = 0;

					for (int k = 2; k < i; ++k) {
						if (i % k == 0) {
							if (base[k] > 0) {
								f += 1L << Math.max(0, 55 - locks(k));
							}
						}
					}

					if (f > best_f) {
						best_i = i;
						best_f = f;
					}
				}
			}

			if (best_i >= 0) {
				used[best_i] = true;
				sort[--w] = best_i;
			}
		}

		recurse(sort.length);

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static void findPrimes() {
		Arrays.fill(prime, true);
		prime[0] = false;
		prime[1] = false;

		for (int i = 2; i <= rL; ++i) {
			if (prime[i]) {
				for (int a = i * 2; a <= L; a += i) {
					prime[a] = false;
				}
			}
		}

		int c = 0;

		for (int p = 2; p <= L; ++p) {
			if (prime[p]) {
				++c;
			}
		}

		ps = new int[c];
		int i = 0;

		for (int p = 2; p <= L; ++p) {
			if (prime[p]) {
				ps[i++] = p;
			}
		}
	}

	private static int[] extended_gcd(int a, int b) {
		int r = a % b;

		if (r == 0) {
			return (new int[] { 0, 1 });
		}

		int[] s = extended_gcd(b, r);

		return (new int[] { s[1], s[0] - (a / b) * s[1] });
	}

	private static int[][] split(int n) {
		int m = 1;
		ArrayList<Integer[]> r = new ArrayList<Integer[]>();

		for (int p : ps) {
			int pp = 1;

			while (n % p == 0) {
				pp *= p;
				n /= p;
			}

			if (pp > 1) {
				int[] g = extended_gcd(pp * pp, n * n);
				g[0] *= m;
				g[1] *= m;
				int k = g[1] / (pp * pp);
				g[1] -= k * pp * pp;
				g[0] += k * n * n;

				while (g[1] != 0) {
					if (g[1] % (p * p) != 0) {
						r.add(new Integer[] { g[1] % (p * p), pp });
					}

					pp /= p;
					g[1] /= p * p;
				}

				m = g[0];
			}
		}

		int[][] R = new int[r.size()][2];

		for (int i = 0; i < r.size(); ++i) {
			R[i][0] = r.get(i)[0];
			R[i][1] = r.get(i)[1];
		}

		return R;
	}

	private static void balanceAt(int pp) {
		int p = base[pp];
		int s = p * p;

		do {
			if (have[pp] < s && have[pp] > -s) {
				return;
			}

			int up = pp / p;

			if (have[pp] >= s) {
				have[pp] -= s;
				have[up] += 1;
			} else if (have[pp] <= -s) {
				have[pp] += s;
				have[up] -= 1;
			}

			pp = up;
		} while (pp > 1);
	}

	private static void recurse(int w) {
		if (w == 0) {
			++result;

			return;
		}

		--w;
		int n = sort[w];

		if (have[n] == 0) {
			recurse(w);
		}

		if (have[n] != 0 || base[n] == 0) {
			for (int[] a : f[n]) {
				int pp = a[1];
				have[pp] -= a[0];
				balanceAt(pp);
			}

			if (have[n] == 0) {
				recurse(w);
			}

			for (int[] a : f[n]) {
				int pp = a[1];
				have[pp] += a[0];
				balanceAt(pp);
			}
		}
	}

	private static int locks(int n) {
		int lc = 0;

		for (int k = n + n; k <= L; k += n) {
			if (!used[k]) {
				++lc;
			}
		}

		return lc;
	}
}
