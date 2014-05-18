package Solved_176_200;

/**
 * Maximum product of parts
 * Problem 183
 * 
 * Let N be a positive integer and let N be split into k equal parts, r = N/k,
 * so that N = r + r + ... + r. Let P be the product of these parts, P = r × r ×
 * ... × r = r^k.
 * 
 * For example, if 11 is split into five equal parts, 11 = 2.2 + 2.2 + 2.2 + 2.2
 * + 2.2, then P = 2.25 = 51.53632.
 * 
 * Let M(N) = Pmax for a given value of N.
 * 
 * It turns out that the maximum for N = 11 is found by splitting eleven into
 * four equal parts which leads to Pmax = (11/4)^4; that is, M(11) = 14641/256 =
 * 57.19140625, which is a terminating decimal.
 * 
 * However, for N = 8 the maximum is achieved by splitting it into three equal
 * parts, so M(8) = 512/27, which is a non-terminating decimal.
 * 
 * Let D(N) = N if M(N) is a non-terminating decimal and D(N) = -N if M(N) is a
 * terminating decimal.
 * 
 * For example, SUMD(N) for 5 <= N <= 100 is 2438.
 * 
 * Find SUMD(N) for 5 <= N <= 10000.
 */
public class PE183_Maximum_product_of_parts {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int limit = 10000, result = 0;
		double[] lp = new double[3700];
		lp[1] = 0.;
		double[] lv = new double[3700];
		int[] plusminus = new int[3700];

		for (int i = 2; i < lp.length; i++) {
			lp[i] = Math.log(i) * i;
			lv[i] = lp[i] - lp[i - 1];
		}

		int vindex = 1;

		for (int i = 5; i <= limit; i++) {
			while (Math.log(i) > lv[vindex]) {
				vindex++;
			}

			if (plusminus[vindex - 1] == 0) {
				int j = vindex - 1;

				while (j % 2 == 0) {
					j /= 2;
				}

				while (j % 5 == 0) {
					j /= 5;
				}

				plusminus[vindex - 1] = j;
			}

			if (i % plusminus[vindex - 1] == 0) {
				result -= i;
			} else {
				result += i;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
