package Solved_126_150;

/**
 * Fibonacci golden nuggets
 * Problem 137
 * 
 * Consider the infinite polynomial series AF(x) = xF1 + F2x^2 + F3x^3 + ...,
 * where Fk is the kth term in the Fibonacci sequence: 1, 1, 2, 3, 5, 8, ... ;
 * that is, Fk = Fk-1 + Fk-2, F1 = 1 and F2 = 1.
 * 
 * For this problem we shall be interested in values of x for which AF(x) is a
 * positive integer. Surprisingly AF(1/2) = (1/2).1 + (1/2)^2.1 + (1/2)^3.2 +
 * (1/2)^4.3 + (1/2)^5.5 + ... = 1/2 + 1/4 + 2/8 + 3/16 + 5/32 + ... = 2
 * 
 * The corresponding values of x for the first five natural numbers are shown
 * below.
 * x			AF(x)
 * sqrt2-1		1
 * 1/2			2
 * (sqrt13-2)/3		3
 * (sqrt89-5)/8		4
 * (sqrt34-3)/5		5
 * 
 * We shall call AF(x) a golden nugget if x is rational, because they become
 * increasingly rarer; for example, the 10th golden nugget is 74049690.
 * 
 * Find the 15th golden nugget.
 */
public class PE137_Fibonacci_golden_nuggets {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int nuggets = 0, max = 15;
		long result = 0l;

		for (long n = 1;; n++) {
			long sqrt = sqrt(5 * n * n + 4);

			if (sqrt * sqrt == 5 * n * n + 4) {
				nuggets++;

				if (nuggets == max) {
					result = n * (n + sqrt) / 2;
					break;
				}
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long sqrt(long x) {
		return Math.round(Math.exp(Math.log(x) * .5));
	}
}
