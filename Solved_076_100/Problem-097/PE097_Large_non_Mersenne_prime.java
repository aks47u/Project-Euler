package Solved_076_100;

import java.math.BigInteger;

/**
 * Large non-Mersenne prime
 * Problem 97
 * 
 * The first known prime found to exceed one million digits was discovered in
 * 1999, and is a Mersenne prime of the form 26972593-1; it contains exactly
 * 2,098,960 digits. Subsequently other Mersenne primes, of the form 2p-1, have
 * been found which contain more digits.
 * 
 * However, in 2004 there was found a massive non-Mersenne prime which contains
 * 2,357,207 digits: 28433×2^7830457+1.
 * 
 * Find the last ten digits of this prime number.
 */
public class PE097_Large_non_Mersenne_prime {
	public static void main(String[] args) {
		long start = System.nanoTime();

		BigInteger result = new BigInteger("2");
		BigInteger mod = new BigInteger("10000000000");
		result = result.modPow(new BigInteger("7830457"), mod)
				.multiply(new BigInteger("28433")).add(BigInteger.ONE).mod(mod);

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
