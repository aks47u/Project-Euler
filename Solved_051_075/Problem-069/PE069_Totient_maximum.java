package Solved_051_075;

/**
 * Totient maximum
 * Problem 69
 * 
 * Euler's Totient function, phi(n) [sometimes called the phi function], is used
 * to determine the number of numbers less than n which are relatively prime to
 * n. For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and
 * relatively prime to nine, phi(9)=6.
 * n	Relatively Prime	phi(n)	n/phi(n)
 * 2	1			1	2
 * 3	1,2			2	1.5
 * 4	1,3			2	2
 * 5	1,2,3,4			4	1.25
 * 6	1,5			2	3
 * 7	1,2,3,4,5,6		6	1.1666...
 * 8	1,3,5,7			4	2
 * 9	1,2,4,5,7,8		6	1.5
 * 10	1,3,7,9			4	2.5
 * 
 * It can be seen that n=6 produces a maximum n/phi(n) for n <= 10.
 * 
 * Find the value of n <= 1,000,000 for which n/phi(n) is a maximum.
 */
public class PE069_Totient_maximum {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int limit = 1000000, product = 1, i = 1;

		while (product < limit) {
			if (isPrime(i)) {
				product *= i;
			}

			i++;
		}

		System.out.println(product / (--i));

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	public static boolean isPrime(int n) {
		if (n == 2) {
			return true;
		} else if (n < 2 || n % 2 == 0) {
			return false;
		} else {
			for (int i = 3; i <= Math.sqrt(n); i += 2) {
				if (n % i == 0) {
					return false;
				}
			}

			return true;
		}
	}
}
