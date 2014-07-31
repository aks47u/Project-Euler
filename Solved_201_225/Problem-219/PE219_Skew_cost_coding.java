package Solved_201_225;

/**
 * Skew-cost coding
 * Problem 219
 * 
 * Let A and B be bit strings (sequences of 0's and 1's). If A is equal to the
 * leftmost length(A) bits of B, then A is said to be a prefix of B. For
 * example, 00110 is a prefix of 001101001, but not of 00111 or 100110.
 * 
 * A prefix-free code of size n is a collection of n distinct bit strings such
 * that no string is a prefix of any other. For example, this is a prefix-free
 * code of size 6:
 * 
 * 0000, 0001, 001, 01, 10, 11
 * 
 * Now suppose that it costs one penny to transmit a '0' bit, but four pence to
 * transmit a '1'. Then the total cost of the prefix-free code shown above is 35
 * pence, which happens to be the cheapest possible for the skewed pricing
 * scheme in question. In short, we write Cost(6) = 35.
 * 
 * What is Cost(10^9) ?
 */
public class PE219_Skew_cost_coding {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long counter = 0, f0 = 1, f1 = 0, f2 = 0, f3 = 0, f4 = 0;
		long remaining = 999999999;

		while (remaining > 0) {
			if (remaining >= f0) {
				remaining -= f0;
				f1 += f0;
				f4 += f0;
				f0 = f1;
				f1 = f2;
				f2 = f3;
				f3 = f4;
				f4 = 0;
				counter++;
			} else {
				f0 -= remaining;
				f1 += remaining;
				f4 += remaining;
				remaining = 0;
			}
		}

		long result = f0 * counter + f1 * (counter + 1) + f2 * (counter + 2)
				+ f3 * (counter + 3) + f4 * (counter + 4);

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
