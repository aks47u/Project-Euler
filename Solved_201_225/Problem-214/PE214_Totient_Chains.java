package Solved_201_225;

/**
 * Totient Chains
 * Problem 214
 * 
 * Let phi be Euler's totient function, i.e. for a natural number n, phi(n) is
 * the number of k, 1 <= k <= n, for which gcd(k,n) = 1.
 * 
 * By iterating phi, each positive integer generates a decreasing chain of
 * numbers ending in 1. E.g. if we start with 5 the sequence 5,4,2,1 is
 * generated. Here is a listing of all chains with length 4:
 * 5,4,2,1
 * 7,6,2,1
 * 8,4,2,1
 * 9,6,2,1
 * 10,4,2,1
 * 12,4,2,1
 * 14,6,2,1
 * 18,6,2,1
 * 
 * Only two of these chains start with a prime, their sum is 12.
 * 
 * What is the sum of all primes less than 40000000 which generate a chain of
 * length 25?
 */
public class PE214_Totient_Chains {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int limit = 40000000, chain = 25;
		int[] totients = totient(limit);
		int[] chains = new int[limit];
		long result = 0;
		chains[1] = 1;
		chains[2] = 2;

		for (int i = 3; i < chains.length; i++) {
			chains[i] = chains[totients[i]] + 1;

			if (chains[i] == chain && totients[i] == i - 1) {
				result += i;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static int[] totient(int limit) {
		int[] totient = new int[limit];

		for (int i = 1; i < totient.length; i++) {
			totient[i] = i;
		}

		for (int i = 2; i < limit; i++) {
			if (totient[i] == i) {
				int count = i;

				while (count < limit) {
					totient[count] = (int) (totient[count] * (1 - ((1.0 / (double) i))));
					count += i;
				}
			}
		}

		return totient;
	}
}
