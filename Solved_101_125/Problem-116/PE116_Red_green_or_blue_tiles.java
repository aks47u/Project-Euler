package Solved_101_125;

import java.math.BigInteger;

/**
 * Red, green or blue tiles
 * Problem 116
 * 
 * A row of five black square tiles is to have a number of its tiles replaced
 * with coloured oblong tiles chosen from red (length two), green (length
 * three), or blue (length four).
 * 
 * If red tiles are chosen there are exactly seven ways this can be done.
 * 
 * If green tiles are chosen there are three ways.
 * 
 * And if blue tiles are chosen there are two ways.
 * 
 * Assuming that colours cannot be mixed there are 7 + 3 + 2 = 12 ways of
 * replacing the black tiles in a row measuring five units in length.
 * 
 * How many different ways can the black tiles in a row measuring fifty units in
 * length be replaced if colours cannot be mixed and at least one coloured tile
 * must be used?
 * 
 * NOTE: This is related to problem 117.
 */
public class PE116_Red_green_or_blue_tiles {
	private static final int BLOCK_SIZE = 50;

	public static void main(String[] args) {
		long start = System.nanoTime();

		BigInteger result = findAll(2).add(findAll(3)).add(findAll(4));

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static BigInteger findAll(int length) {
		BigInteger result = BigInteger.ZERO;

		for (int amount = 1; amount <= (BLOCK_SIZE / length); amount++) {
			long n = BLOCK_SIZE + (1 - length) * amount;
			result = result.add(nChooseR(n, amount));
		}

		return result;
	}

	private static BigInteger nChooseR(long n, long r) {
		BigInteger result = BigInteger.ONE;

		for (int i = 0; i < r; i++) {
			result = result.multiply(BigInteger.valueOf(n - i));
		}

		for (int i = 0; i < r; i++) {
			result = result.divide(BigInteger.valueOf(r - i));
		}

		return result;
	}
}
