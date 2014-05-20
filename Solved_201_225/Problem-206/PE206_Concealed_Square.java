package Solved_201_225;

/**
 * Concealed Square
 * Problem 206
 * 
 * Find the unique positive integer whose square has the form
 * 1_2_3_4_5_6_7_8_9_0, where each “_” is a single digit.
 */
public class PE206_Concealed_Square {
	public static void main(String args[]) {
		long start = System.nanoTime();

		long n2, result = 0;

		for (long n = 100000000; n < 200000000; n++) {
			if ((n % 10) != 3 && (n % 10) != 7) {
				continue;
			}

			n2 = n * n;

			if (n2 % 1000 >= 800 && n2 % 1000 < 900) {
				if (n2 % 100000 >= 70000 && n2 % 100000 < 80000) {
					if (n2 % 10000000 >= 6000000 && n2 % 10000000 < 7000000) {
						if (n2 % 1000000000 >= 500000000
								&& n2 % 1000000000 < 600000000) {
							if (n2 % 100000000000L >= 40000000000L
									&& n2 % 100000000000L < 50000000000L) {
								if (n2 % 10000000000000L >= 3000000000000L
										&& n2 % 10000000000000L < 4000000000000L) {
									if (n2 % 1000000000000000L >= 200000000000000L
											&& n2 % 1000000000000000L < 300000000000000L) {
										result = 10 * n;
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
}
