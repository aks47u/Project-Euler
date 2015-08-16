package Solved_051_075;

import java.math.BigInteger;

/**
 * Diophantine equation
 * Problem 66
 * 
 * Consider quadratic Diophantine equations of the form:
 * 
 * x^2 - Dy^2 = 1
 * 
 * For example, when D=13, the minimal solution in x is 649^2 - 13*180^2 = 1.
 * 
 * It can be assumed that there are no solutions in positive integers when D is
 * square.
 * 
 * By finding minimal solutions in x for D = {2, 3, 5, 6, 7}, we obtain the
 * following:
 * 
 * 3^2 - 2*2^2 = 1
 * 2^2 - 3*1^2 = 1
 * 9^2 - 5*4^2 = 1
 * 5^2 - 6*2^2 = 1
 * 8^2 - 7*3^2 = 1
 * 
 * Hence, by considering minimal solutions in x for D <= 7, the largest x is
 * obtained when D=5.
 * 
 * Find the value of D <= 1000 in minimal solutions of x for which the largest
 * value of x is obtained.
 */
public class PE066_Diophantine_equation {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int result = 0;
		BigInteger max = BigInteger.ZERO;
		BigInteger cand;

		for (int i = 2; i <= 1000; i++) {
			int f = (int) Math.sqrt(i);

			if (f * f != i) {
				int[] sqip = CFPeriod(i);

				if (sqip.length % 2 == 0) {
					cand = writeCF(f, sqip, sqip.length - 1);
				} else {
					cand = writeCF(f, sqip, 2 * sqip.length - 1);
				}

				if (cand.compareTo(max) > 0) {
					max = cand;
					result = i;
				}
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static BigInteger writeCF(int f, int[] period, int n) {
		BigInteger num, denom, temp;
		num = BigInteger.ONE;
		denom = BigInteger.valueOf(period[(n - 1) % period.length]);

		for (int i = n - 2; i >= 0; i--) {
			num = num.add(BigInteger.valueOf(period[i % period.length])
					.multiply(denom));
			temp = num;
			num = denom;
			denom = temp;
		}

		num = num.add(denom.multiply(BigInteger.valueOf(f)));

		return num;
	}

	private static int[] CFPeriod(int i) {
		int[] terms = new int[500];
		int nS = (int) Math.sqrt(i);
		int a = 1, b = nS, c, d, e;
		int startA = a, startB = b, gcd, nTerms;
		nTerms = 0;
		double sqrtI = Math.sqrt(i);

		do {
			c = i - b * b;
			gcd = getGCD(a, c);
			a /= gcd;
			c /= gcd;
			d = (int) (a * (sqrtI + b) / c);
			e = d * c / a - b;
			a = c;
			b = e;
			terms[nTerms++] = d;
		} while (!(a == startA && b == startB));

		int[] termsFit = new int[nTerms];

		for (int j = 0; j < termsFit.length; j++) {
			termsFit[j] = terms[j];
		}

		return termsFit;
	}

	private static int getGCD(int a, int b) {
		if (b == 0) {
			return a;
		}

		return getGCD(b, a % b);
	}
}
