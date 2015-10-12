package Solved_151_175;

import java.math.BigInteger;

/**
 * Number Rotations
 * Problem 168
 * 
 * Consider the number 142857. We can right-rotate this number by moving the
 * last digit (7) to the front of it, giving us 714285. It can be verified that
 * 714285=5×142857. This demonstrates an unusual property of 142857: it is a
 * divisor of its right-rotation.
 * 
 * Find the last 5 digits of the sum of all integers n, 10 < n < 10^100, that
 * have this property.
 */
public class PE168_Number_Rotations {
	public static void main(String[] args) {
		long start = System.nanoTime();

		final BigInteger TEN = BigInteger.TEN;
		final BigInteger HUNDRED_THOUSAND = BigInteger.valueOf(100000);
		BigInteger total = BigInteger.ZERO;

		for (int pow = 1; pow < 101; pow++) {
			for (BigInteger a = BigInteger.ONE; a.compareTo(TEN) < 0; a = a
					.add(BigInteger.ONE)) {
				for (BigInteger n = BigInteger.ONE; n.compareTo(TEN) < 0; n = n
						.add(BigInteger.ONE)) {
					BigInteger denom = ((TEN.pow(pow)).subtract(n)).multiply(a);
					BigInteger div = (TEN.multiply(n)).subtract(BigInteger.ONE);

					if (denom.mod(div).equals(BigInteger.ZERO)) {
						final BigInteger NUM = denom.divide(div);

						if (NUM.toString().length() == pow) {
							BigInteger value = new BigInteger("" + NUM + a);

							if (value.toString().length() <= 100) {
								total = total.add(value);
							}
						}
					}
				}
			}
		}

		BigInteger result = total.mod(HUNDRED_THOUSAND);

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
