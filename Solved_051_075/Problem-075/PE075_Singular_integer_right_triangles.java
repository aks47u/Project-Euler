package Solved_051_075;

/**
 * Singular integer right triangles
 * Problem 75
 * 
 * It turns out that 12 cm is the smallest length of wire that can be bent to
 * form an integer sided right angle triangle in exactly one way, but there are
 * many more examples.
 * 
 * 12 cm: (3,4,5)
 * 24 cm: (6,8,10)
 * 30 cm: (5,12,13)
 * 36 cm: (9,12,15)
 * 40 cm: (8,15,17)
 * 48 cm: (12,16,20)
 * 
 * In contrast, some lengths of wire, like 20 cm, cannot be bent to form an
 * integer sided right angle triangle, and other lengths allow more than one
 * solution to be found; for example, using 120 cm it is possible to form
 * exactly three different integer sided right angle triangles.
 * 
 * 120 cm: (30,40,50), (20,48,52), (24,45,51)
 * 
 * Given that L is the length of the wire, for how many values of L <= 1,500,000
 * can exactly one integer sided right angle triangle be formed?
 */
public class PE075_Singular_integer_right_triangles {
	private static int[] array = new int[1500001];

	public static void main(String[] args) {
		long start = System.nanoTime();
		
		for (int m = 1; m < 1000; m++) {
			for (int i = 1; i < m; i++) {
				if (gcd(m, i) > 1) {
					continue;
				}

				int k = sumTriple(m, i);

				if (k > 1500000) {
					break;
				}

				increase(k);
			}
		}

		int result = 0;

		for (int i = 1; i <= 1500000; i++) {
			if (array[i] == 1) {
				result++;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	public static void increase(int n) {
		if (n == 0) {
			return;
		}

		for (int j = n; j <= 1500000; j += n) {
			array[j]++;
		}
	}

	public static int gcd(int m, int n) {
		if (n > m) {
			return gcd(n, m);
		}

		if (m % n == 0) {
			return n;
		}

		return gcd(n, m - n * (m / n));
	}

	public static int sumTriple(int m, int n) {
		if (n > m) {
			return sumTriple(n, m);
		}

		if (gcd(m * m + n * n, m * m - n * n) != 1) {
			return 0;
		}

		int k = 2 * (m * m + m * n);

		return k;
	}
}
