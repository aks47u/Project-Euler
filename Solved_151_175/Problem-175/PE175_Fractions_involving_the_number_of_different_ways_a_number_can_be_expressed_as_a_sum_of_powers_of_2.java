package Solved_151_175;

import java.util.ArrayList;
import java.util.List;

/**
 * Fractions involving the number of different ways a number can be expressed as
 * a sum of powers of 2
 * Problem 175
 * 
 * Define f(0)=1 and f(n) to be the number of ways to write n as a sum of powers
 * of 2 where no power occurs more than twice.
 * 
 * For example, f(10)=5 since there are five different ways to express 10: 10 =
 * 8+2 = 8+1+1 = 4+4+2 = 4+2+2+1+1 = 4+4+1+1
 * 
 * It can be shown that for every fraction p/q (p>0, q>0) there exists at least
 * one integer n such that f(n)/f(n-1)=p/q.
 * 
 * For instance, the smallest n for which f(n)/f(n-1)=13/17 is 241. The binary
 * expansion of 241 is 11110001. Reading this binary number from the most
 * significant bit to the least significant bit there are 4 one's, 3 zeroes and
 * 1 one. We shall call the string 4,3,1 the Shortened Binary Expansion of 241.
 * 
 * Find the Shortened Binary Expansion of the smallest n for which
 * f(n)/f(n-1)=123456789/987654321.
 * 
 * Give your answer as comma separated integers, without any whitespaces.
 */
public class PE175_Fractions_involving_the_number_of_different_ways_a_number_can_be_expressed_as_a_sum_of_powers_of_2 {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int p = 123456789, q = 987654321;
		List<Integer> answer = new ArrayList<Integer>();

		while (q != 0) {
			if (p > q) {
				answer.add(0, p / q - (p % q == 0 ? 1 : 0));
				p = p % q == 0 ? q : p % q;
			} else {
				answer.add(0, q / p);
				q %= p;
			}
		}

		String result = "";

		for (int i = 0; i < answer.size(); i++) {
			result += answer.get(i) + ",";
		}

		result = result.substring(0, result.length() - 1);

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
