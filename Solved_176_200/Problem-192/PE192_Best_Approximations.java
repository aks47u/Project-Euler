package Solved_176_200;

/**
 * Best Approximations
 * Problem 192
 * 
 * Let x be a real number. A best approximation to x for the denominator bound d
 * is a rational number r/s in reduced form, with s <= d, such that any rational
 * number which is closer to x than r/s has a denominator larger than d: |p/q-x|
 * < |r/s-x| ==> q > d
 * 
 * For example, the best approximation to sqrt13 for the denominator bound 20 is
 * 18/5 and the best approximation to sqrt13 for the denominator bound 30 is
 * 101/28.
 * 
 * Find the sum of all denominators of the best approximations to sqrtn for the
 * denominator bound 1012, where n is not a perfect square and 1 < n <= 100000.
 */
public class PE192_Best_Approximations {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long result = 0;

		for (int n = 2, np = 2; n <= 100000; n++) {
			if (n == np * np) {
				np++;
			} else {
				int[] a = ContinuedFractionRoot(n, 128);
				result += LowestDenominator(a, (long) Math.pow(10, 12));
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static boolean AllowHalf(int[] a, int k) {
		int i = k, s = 1;

		while (i > 0) {
			int difference = s * (a[i] - a[2 * k - i]);

			if (difference > 0) {
				return true;
			}

			if (difference < 0) {
				return false;
			}

			s *= -1;
			i--;
		}

		return ((k - 1) & 1) != 0;
	}

	private static long LowestDenominator(int[] a, long limit) {
		long n2 = 1, d2 = 0;
		long n1 = a[0], d1 = 1;
		int i = 1;

		while (true) {
			long n = 0, d = 0;
			int minval = ((a[i] + 1) / 2);

			if ((a[i] & 1) == 0 && !AllowHalf(a, i)) {
				minval++;
			}

			for (long q = minval; q <= a[i]; q++) {
				n = n2 + q * n1;
				d = d2 + q * d1;

				if (d > limit) {
					if (q > minval) {
						return d2 + (q - 1) * d1;
					} else {
						return d1;
					}
				}
			}

			n2 = n1;
			d2 = d1;
			n1 = n;
			d1 = d;
			i++;
		}
	}

	private static int[] ContinuedFractionRoot(int n, int number) {
		int[] result = new int[number];
		int lowsqr = (int) (0.5 + Math.sqrt((double) n));

		if (lowsqr * lowsqr > n) {
			lowsqr--;
		}

		int a0 = 0, b0 = 1;

		for (int i = 0; i < number; i++) {
			int f1 = (a0 + lowsqr) / b0;
			int a1 = b0 * f1 - a0;
			int b1 = (n - a1 * a1) / b0;
			result[i] = f1;
			a0 = a1;
			b0 = b1;
		}

		return result;
	}
}
