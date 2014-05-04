package Solved_026_050;

/**
 * Consecutive prime sum
 * Problem 50
 * 
 * The prime 41, can be written as the sum of six consecutive primes: 41 = 2 + 3
 * + 5 + 7 + 11 + 13
 * 
 * This is the longest sum of consecutive primes that adds to a prime below
 * one-hundred.
 * 
 * The longest sum of consecutive primes below one-thousand that adds to a
 * prime, contains 21 terms, and is equal to 953.
 * 
 * Which prime, below one-million, can be written as the sum of the most
 * consecutive primes?
 */
public class PE050_Consecutive_prime_sum {
	public static int[] primes;
	public static boolean[] isComposite;

	public static void main(String[] args) {
		long start = System.nanoTime();

		generatePrimes(1000000);
		int mostConsec = 0;
		int result = 0;

		for (int i = 0; i < 78498; i++) {
			int numConsec = 1;
			int sum = 0;
			while (sum < 1000000 && i + numConsec - 1 < 78498) {
				if (numConsec > mostConsec && !isComposite[sum]) {
					mostConsec = numConsec;
					result = sum;
				}
				sum += primes[i + numConsec - 1];
				numConsec++;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
	
	public static int[] generatePrimes(int max) {
		isComposite = new boolean[max + 1];

		for (int i = 2; i * i <= max; i++) {
			if (!isComposite[i]) {
				for (int j = i; i * j <= max; j++) {
					isComposite[i * j] = true;
				}
			}
		}

		int numPrimes = 0;

		for (int i = 2; i <= max; i++) {
			if (!isComposite[i]) {
				numPrimes++;
			}
		}

		primes = new int[numPrimes];
		int index = 0;

		for (int i = 2; i <= max; i++) {
			if (!isComposite[i]) {
				primes[index++] = i;
			}
		}

		return primes;
	}
}
