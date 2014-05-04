package Solved_026_050;

/**
 * Goldbach's other conjecture
 * Problem 46
 * 
 * It was proposed by Christian Goldbach that every odd composite number can be
 * written as the sum of a prime and twice a square.
 * 
 * 9 = 7 + 2×1^2
 * 15 = 7 + 2×2^2
 * 21 = 3 + 2×3^2
 * 25 = 7 + 2×3^2
 * 27 = 19 + 2×2^2
 * 33 = 31 + 2×1^2
 * 
 * It turns out that the conjecture was false.
 * 
 * What is the smallest odd composite that cannot be written as the sum of a
 * prime and twice a square?
 */
public class PE046_Goldbachs_other_conjecture {
	public static int[] primes;
	public static boolean[] isComposite;

	public static void main(String[] args) {
		long start = System.nanoTime();

		generatePrimes(10000);

		for (int i = 1; i <= 10000; i += 2) {
			if (isComposite[i]) {
				boolean foundSolution = false;

				for (int prime : primes) {
					double test = Math.sqrt((i - prime) / 2);

					if (test == (int) test) {
						foundSolution = true;
						break;
					}
				}
				if (!foundSolution) {
					long end = System.nanoTime();
					long runtime = end - start;
					System.out.println(i);
					System.out.println("Runtime: " + runtime / 1000000 + "ms ("
							+ runtime + "ns)");
					break;
				}
			}
		}
	}

	public static int[] generatePrimes(int max) {
		isComposite = new boolean[max + 1];

		for (int i = 2; i * i <= max; i++)
			if (!isComposite[i]) {
				for (int j = i; i * j <= max; j++) {
					isComposite[i * j] = true;
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
