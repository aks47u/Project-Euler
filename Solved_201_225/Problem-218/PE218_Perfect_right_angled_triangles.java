package Solved_201_225;

/**
 * Perfect right-angled triangles
 * Problem 218
 * 
 * Consider the right angled triangle with sides a=7, b=24 and c=25. The area of
 * this triangle is 84, which is divisible by the perfect numbers 6 and 28.
 * Moreover it is a primitive right angled triangle as gcd(a,b)=1 and
 * gcd(b,c)=1. Also c is a perfect square.
 * 
 * We will call a right angled triangle perfect if
 * 	-it is a primitive right angled triangle
 * 	-its hypotenuse is a perfect square
 * 
 * We will call a right angled triangle super-perfect if
 * 	-it is a perfect right angled triangle and
 * 	-its area is a multiple of the perfect numbers 6 and 28.
 * 
 * How many perfect right-angled triangles with c<=10^16 exist that are not
 * super-perfect?
 */
public class PE218_Perfect_right_angled_triangles {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long perfect = 0, superperfect = 0;
		long result = 0;
		long max = 10000000000000000L;

		for (long t = 1; t * t * t * t <= max; ++t)
			for (long s = t + 1; s * s * s * s + t * t * t * t + 2 * s * s * t
					* t <= max; ++s) {
				if ((s & 1) == (t & 1)) {
					continue;
				}

				if (gcd(s, t) != 1) {
					continue;
				}

				long m = 2 * s * t;
				long n = s * s - t * t;

				if (m < n) {
					long temp = m;
					m = n;
					n = temp;
				}

				long a = 2 * m * n;
				long b = m * m - n * n;
				++perfect;

				if (((a % 3) != 0) && ((b % 3) != 0)) {
					continue;
				}

				if (((a % 7) != 0) && ((b % 7) != 0)) {
					continue;
				}

				a /= 2;

				if (((a & 1) != 0) && ((b & 1) != 0)) {
					continue;
				}

				++superperfect;
			}

		result = perfect - superperfect;

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long gcd(long a, long b) {
		return b == 0 ? a : gcd(b, a % b);
	}
}
