package Solved_151_175;

/**
 * Solving the diophantine equation 1/a+1/b= p/10^n
 * Problem 157
 * 
 * Consider the diophantine equation 1/a+1/b= p/10^n with a, b, p, n positive
 * integers and a <= b. For n=1 this equation has 20 solutions that are listed
 * below: 1/1+1/1=20/10 1/1+1/2=15/10 1/1+1/5=12/10 1/1+1/10=11/10 1/2+1/2=10/10
 * 1/2+1/5=7/10 1/2+1/10=6/10 1/3+1/6=5/10 1/3+1/15=4/10 1/4+1/4=5/10
 * 1/4+1/20=3/10 1/5+1/5=4/10 1/5+1/10=3/10 1/6+1/30=2/10 1/10+1/10=2/10
 * 1/11+1/110=1/10 1/12+1/60=1/10 1/14+1/35=1/10 1/15+1/30=1/10 1/20+1/20=1/10
 * 
 * How many solutions has this equation for 1 <= n <= 9?
 */
public class PE157_Solving_the_diophantine_equation_1_a_1_b__p_10_n {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int LimitN = 10, result = 0;
		int[] magnitude2 = new int[LimitN];
		magnitude2[0] = 1;

		for (int n = 1; n < LimitN; n++) {
			magnitude2[n] = magnitude2[n - 1] << 1;
		}

		int[] magnitude5 = new int[LimitN];
		magnitude5[0] = 1;

		for (int n = 1; n < LimitN; n++) {
			magnitude5[n] = magnitude5[n - 1] * 5;
		}

		for (int n = 1; n < LimitN; n++) {
			int ten = high(10, n);

			for (int ai = 0; ai <= n; ai++) {
				for (int bi = 0; bi <= n; bi++) {
					result += f157(magnitude2[ai], magnitude5[bi], ten);
				}
			}

			for (int ai = 1; ai <= n; ai++) {
				for (int bi = 1; bi <= n; bi++) {
					result += f157(1, magnitude2[ai] * magnitude5[bi], ten);
				}
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static int f157(int a, int b, int ten) {
		long tensum = (long) ten * (a + b);

		if (tensum % b != 0 || tensum % a != 0) {
			return 0;
		}

		return prime((int) (tensum / (a * b)));
	}

	private static int prime(int num) {
		int result = 1;

		for (int k = 2; k * k <= num; k++) {
			int count = 1;

			while (num % k == 0) {
				num /= k;
				count++;
			}

			result *= count;
		}

		if (num != 1) {
			result <<= 1;
		}

		return result;
	}

	private static int high(int b, int e) {
		int result = 1;

		while (e > 0) {
			if ((e & 1) == 1) {
				result *= b;
			}

			e >>= 1;
			b *= b;
		}

		return result;
	}
}
