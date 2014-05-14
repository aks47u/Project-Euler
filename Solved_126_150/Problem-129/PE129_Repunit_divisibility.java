package Solved_126_150;

/**
 * Repunit divisibility
 * Problem 129
 * 
 * A number consisting entirely of ones is called a repunit. We shall define
 * R(k) to be a repunit of length k; for example, R(6) = 111111.
 * 
 * Given that n is a positive integer and GCD(n, 10) = 1, it can be shown that
 * there always exists a value, k, for which R(k) is divisible by n, and let
 * A(n) be the least such value of k; for example, A(7) = 6 and A(41) = 5.
 * 
 * The least value of n for which A(n) first exceeds ten is 17.
 * 
 * Find the least value of n for which A(n) first exceeds one-million.
 */
public class PE129_Repunit_divisibility {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int result = 0;

		for (int n = 1000000;; n++) {
			if ((n % 5 != 0 && n % 2 != 0 && calcA(n) > 1000000)) {
				result = n;
				break;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	public static int calcA(int p) {
		for (int a = 1, q = 1; true; a++) {
			if (q == 0) {
				return a;
			}

			q = (q * 10 + 1) % p;
		}
	}
}
