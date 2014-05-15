package Solved_126_150;

/**
 * Pythagorean tiles
 * Problem 139
 * 
 * Let (a, b, c) represent the three sides of a right angle triangle with
 * integral length sides. It is possible to place four such triangles together
 * to form a square with length c.
 * 
 * For example, (3, 4, 5) triangles can be placed together to form a 5 by 5
 * square with a 1 by 1 hole in the middle and it can be seen that the 5 by 5
 * square can be tiled with twenty-five 1 by 1 squares.
 * 
 * However, if (5, 12, 13) triangles were used then the hole would measure 7 by
 * 7 and these could not be used to tile the 13 by 13 square.
 * 
 * Given that the perimeter of the right triangle is less than one-hundred
 * million, how many Pythagorean triangles would allow such a tiling to take
 * place?
 */
public class PE139_Pythagorean_tiles {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int result = 0;

		for (int i = 1; i < 20000; i++)
			for (int j = i + 1; j < 20000; j++) {
				int a = j * j - i * i;
				int b = 2 * i * j;
				int c = j * j + i * i;
				int d = a > b ? a - b : b - a;

				if ((d == 0) || (c % d > 0)) {
					continue;
				}

				if (gcd(a, b) > 1) {
					continue;
				}

				int sum = a + b + c;

				if (sum >= 100000000) {
					break;
				}

				result += 99999999 / sum;
			}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static int gcd(int a, int b) {
		if (a < b) {
			return gcd(b, a);
		}
		
		return (a % b == 0) ? b : gcd(b, a % b);
	}
}
