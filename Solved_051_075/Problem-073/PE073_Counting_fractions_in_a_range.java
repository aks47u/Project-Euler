package Solved_051_075;

/**
 * Counting fractions in a range
 * Problem 73
 * 
 * Consider the fraction, n/d, where n and d are positive integers. If n<d and
 * HCF(n,d)=1, it is called a reduced proper fraction.
 * 
 * If we list the set of reduced proper fractions for d <= 8 in ascending order
 * of size, we get:
 * 
 * 1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3,
 * 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
 * 
 * It can be seen that there are 3 fractions between 1/3 and 1/2.
 * 
 * How many fractions lie between 1/3 and 1/2 in the sorted set of reduced
 * proper fractions for d <= 12,000?
 */
public class PE073_Counting_fractions_in_a_range {
	private static int result;

	public static void main(String[] args) {
		long start = System.nanoTime();

		result = 0;
		count(1, 3, 2, 5, 1, 2);

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static void count(int leftN, int leftD, int midN, int midD,
			int rightN, int rightD) {
		if (midD > 12000) {
			return;
		}

		result++;
		count(leftN, leftD, leftN + midN, leftD + midD, midN, midD);
		count(midN, midD, rightN + midN, rightD + midD, rightN, rightD);
	}
}
