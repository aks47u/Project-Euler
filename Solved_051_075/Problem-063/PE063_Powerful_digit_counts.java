package Solved_051_075;

/**
 * Powerful digit counts
 * Problem 63
 * 
 * The 5-digit number, 16807=7^5, is also a fifth power. Similarly, the 9-digit
 * number, 134217728=8^9, is a ninth power.
 * 
 * How many n-digit positive integers exist which are also an nth power?
 */
public class PE063_Powerful_digit_counts {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int result = 0;

		for (int i = 1; i < 10; i++) {
			result += (int) (1.0 / (1 - Math.log(i) / Math.log(10)));
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
