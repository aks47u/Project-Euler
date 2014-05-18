package Solved_151_175;

import java.math.BigInteger;

/**
 * Exploring the number of different ways a number can be expressed as a sum of
 * powers of 2
 * Problem 169
 * 
 * Define f(0)=1 and f(n) to be the number of different ways n can be expressed
 * as a sum of integer powers of 2 using each power no more than twice.
 * 
 * For example, f(10)=5 since there are five different ways to express 10:
 * 
 * 1 + 1 + 8
 * 1 + 1 + 4 + 4
 * 1 + 1 + 2 + 2 + 4
 * 2 + 4 + 4
 * 2 + 8
 * 
 * What is f(10^25)?
 */
public class PE169_Exploring_the_number_of_different_ways_a_number_can_be_expressed_as_a_sum_of_powers_of_2 {
	public static void main(String[] args) {
		long start = System.nanoTime();

		BigInteger bi = new BigInteger("10000000000000000000000000");
		long a = 1, b = 0, result = 0;

		for (int i = bi.bitLength(); i > 0; i--) {
			if (bi.testBit(i)) {
				b += a;
			} else {
				a += b;
			}
		}

		result = a + b;

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
