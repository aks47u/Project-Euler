package Solved_051_075;

/**
 * Prime pair sets
 * Problem 60
 * 
 * The primes 3, 7, 109, and 673, are quite remarkable. By taking any two primes
 * and concatenating them in any order the result will always be prime. For
 * example, taking 7 and 109, both 7109 and 1097 are prime. The sum of these
 * four primes, 792, represents the lowest sum for a set of four primes with
 * this property.
 * 
 * Find the lowest sum for a set of five primes for which any two primes
 * concatenate to produce another prime.
 */
public class PE060_Prime_pair_sets {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int[] primes = new int[1000000];
		int max = 10000, result = 0;
		primes[0] = 2;
		int amount = 1;

		for (int i = 3; i <= max; i += 2) {
			if (isPrime(i)) {
				primes[amount] = i;
				amount++;
			}
		}

		boolean[][] related = new boolean[1229][1229];

		for (int i = 0; i < amount; i++) {
			for (int j = i + 1; j < amount; j++) {
				if (i == j) {
					continue;
				}

				int a = Integer.valueOf(primes[i] + "" + primes[j]);
				int b = Integer.valueOf(primes[j] + "" + primes[i]);

				if (isPrime(a) && isPrime(b)) {
					related[i][j] = true;
					related[j][i] = true;
				}
			}
		}

		for (int i = 0; i < amount; i++) {
			for (int j = i + 1; j < amount; j++) {
				if (related[i][j]) {
					for (int k = j + 1; k < amount; k++) {
						if (related[i][k] && related[j][k]) {
							for (int l = k + 1; l < amount; l++) {
								if (related[i][l] && related[j][l]
										&& related[k][l]) {
									for (int m = l + 1; m < amount; m++) {
										if (related[i][m] && related[j][m]
												&& related[k][m]
														&& related[l][m]) {
											result = primes[i] + primes[j]
													+ primes[k] + primes[l]
															+ primes[m];
										}
									}
								}
							}
						}
					}
				}
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	public static boolean isPrime(int n) {
		if (n <= 1) {
			return false;
		}

		if (n == 2) {
			return true;
		}

		if (n % 2 == 0) {
			return false;
		}

		int max = (int) Math.sqrt(n);

		for (int i = 3; i <= max; i += 2) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}
}
