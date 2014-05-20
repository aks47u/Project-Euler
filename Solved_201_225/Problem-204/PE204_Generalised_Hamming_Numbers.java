package Solved_201_225;

/**
 * Generalised Hamming Numbers
 * Problem 204
 * 
 * A Hamming number is a positive number which has no prime factor larger than
 * 5. So the first few Hamming numbers are 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15.
 * There are 1105 Hamming numbers not exceeding 10^8.
 * 
 * We will call a positive number a generalised Hamming number of type n, if it
 * has no prime factor larger than n. Hence the Hamming numbers are the
 * generalised Hamming numbers of type 5.
 * 
 * How many generalised Hamming numbers of type 100 are there which don't exceed
 * 10^9?
 */
public class PE204_Generalised_Hamming_Numbers {
	private static int[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
		41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97 };

	public static void main(String[] args) {
		long start = System.nanoTime();

		int result = hamming(1000000000, primes.length - 1);

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static int hamming(int i, int j) {
		if (j == 0)
			return (int) (Math.log(i) / Math.log(2)) + 1;
		else {
			int result = 0;

			while (i > 0) {
				result += hamming(i, j - 1);
				i /= primes[j];
			}

			return result;
		}
	}
}
