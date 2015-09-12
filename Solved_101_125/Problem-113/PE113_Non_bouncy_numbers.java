package Solved_101_125;

/**
 * Non-bouncy numbers
 * Problem 113
 * 
 * Working from left-to-right if no digit is exceeded by the digit to its left
 * it is called an increasing number; for example, 134468.
 * 
 * Similarly if no digit is exceeded by the digit to its right it is called a
 * decreasing number; for example, 66420.
 * 
 * We shall call a positive integer that is neither increasing nor decreasing a
 * "bouncy" number; for example, 155349.
 * 
 * As n increases, the proportion of bouncy numbers below n increases such that
 * there are only 12951 numbers below one-million that are not bouncy and only
 * 277032 non-bouncy numbers below 10^10.
 * 
 * How many numbers below a googol (10^100) are not bouncy?
 */
public class PE113_Non_bouncy_numbers {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int N = 100;
		long[] sums1 = new long[10];
		long[] sums2 = new long[10];

		for (int i = 0; i < 10; i++) {
			sums1[i] = 1;
		}

		sums2[0] = 0;

		for (int i = 1; i < 10; i++) {
			sums2[i] = 1;
		}

		long result = 20;

		for (int i = 2; i <= N; i++) {
			for (int j = 1; j < 10; j++) {
				sums1[j] = sums1[j] + sums1[j - 1];
				sums2[j] = sums2[j] + sums2[j - 1];
			}

			long sum = 0;

			for (int j = 0; j < 10; j++) {
				sum += sums1[j];
			}

			result += sum;
			sum = 0;

			for (int j = 0; j < 10; j++) {
				sum += sums2[j];
			}

			result += sum;
		}

		result += -10 - (N - 1) * 10 - 1;

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
