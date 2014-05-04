package Solved_026_050;

/**
 * Integer right triangles
 * Problem 39
 * 
 * If p is the perimeter of a right angle triangle with integral length sides,
 * {a,b,c}, there are exactly three solutions for p = 120.
 * 
 * {20,48,52}, {24,45,51}, {30,40,50}
 * 
 * For which value of p <= 1000, is the number of solutions maximised?
 */
public class PE039_Integer_right_triangles {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int a, b, c;
		int total;
		int[] perimeter = new int[1000];

		for (int i = 2; i < 22; i++)
			for (int j = i % 2 + 1; j < i; j += 2)
				if (gcd(i, j) == 1) {
					a = i * i - j * j;
					b = 2 * i * j;
					c = i * i + j * j;
					total = a + b + c;

					for (int k = total; k < 1000; k += total) {
						perimeter[k]++;
					}
				}
		
		int max = -1;
		int result = -1;
		
		for (int i = 0; i < 1000; i++) {
			if (perimeter[i] > max) {
				max = perimeter[i];
				result = i;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static int gcd(int a, int b) {
		if (a == 1 || b == 1) {
			return 1;
		} else if (a % b == 0) {
			return b;
		} else if (b % a == 0) {
			return a;
		} else if (a > b) {
			return gcd(a - (a / b) * b, b);
		} else {
			return gcd(a, b - (b / a) * a);
		}
	}
}
