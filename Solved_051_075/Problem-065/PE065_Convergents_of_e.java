package Solved_051_075;

import java.math.BigInteger;

/**
 * Convergents of e
 * Problem 65
 * 
 * The square root of 2 can be written as an infinite continued fraction. âˆš2 = 1
 * + 1
 * 2 + 1
 * 2 + 1
 * 2 + 1
 * 2 + ...
 * 
 * The infinite continued fraction can be written, âˆš2 = [1;(2)], (2) indicates
 * that 2 repeats ad infinitum. In a similar way, âˆš23 = [4;(1,3,1,8)].
 * 
 * It turns out that the sequence of partial values of continued fractions for
 * square roots provide the best rational approximations. Let us consider the
 * convergents for âˆš2. 1 + 1
 * 
 * = 3/2
 * 2
 * 1 + 1
 * = 7/5 2 + 1
 * 2
 * 1 + 1
 * = 17/12 2 + 1
 * 2 + 1
 * 2
 * 1 + 1
 * = 41/29 2 + 1
 * 2 + 1
 * 2 + 1
 * 2
 * 
 * Hence the sequence of the first ten convergents for âˆš2 are: 1, 3/2, 7/5,
 * 17/12, 41/29, 99/70, 239/169, 577/408, 1393/985, 3363/2378, ...
 * 
 * What is most surprising is that the important mathematical constant, e = [2;
 * 1,2,1, 1,4,1, 1,6,1 , ... , 1,2k,1, ...].
 * 
 * The first ten terms in the sequence of convergents for e are: 2, 3, 8/3,
 * 11/4, 19/7, 87/32, 106/39, 193/71, 1264/465, 1457/536, ...
 * 
 * The sum of digits in the numerator of the 10th convergent is 1+4+5+7=17.
 * 
 * Find the sum of digits in the numerator of the 100th convergent of the
 * continued fraction for e.
 */
public class PE065_Convergents_of_e {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int n = 100;
		long l[] = new long[n + 1];
		l[1] = 2;

		for (int i = 2; i <= n; i++) {
			if (i % 3 == 0) {
				l[i] = 2 * (i / 3);
			} else {
				l[i] = 1;
			}
		}

		BigInteger[] a = new BigInteger[n + 1];
		BigInteger[] p = new BigInteger[n + 1];
		Long q1 = new Long(l[n - 1] * l[n] + 1);
		String s1 = q1.toString();
		a[1] = new BigInteger(s1);
		Long r1 = new Long(l[n]);
		String theS = r1.toString();
		p[1] = new BigInteger(theS);

		for (int i = 2; i < n; i++) {
			p[i] = a[i - 1];
			a[i] = p[i - 1];

			for (int k = 1; k <= l[n - i]; k++) {
				a[i] = a[i].add(a[i - 1]);
			}

			BigInteger meg = a[i].gcd(p[i]);
			a[i] = a[i].divide(meg);
			p[i] = p[i].divide(meg);
		}

		String a1 = a[n - 1].toString();
		int result = 0;

		for (int i = 0; i < a1.length(); i++) {
			result = result + a1.charAt(i) - '0';
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
