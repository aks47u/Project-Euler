package Solved_201_225;

/**
 * Obtuse Angled Triangles
 * Problem 210
 * 
 * Consider the set S(r) of points (x,y) with integer coordinates satisfying |x|
 * + |y| <= r. Let O be the point (0,0) and C the point (r/4,r/4). Let N(r) be
 * the number of points B in S(r), so that the triangle OBC has an obtuse angle,
 * i.e. the largest angle alpha satisfies 90°<alpha<180°. So, for example,
 * N(4)=24 and N(8)=100.
 * 
 * What is N(1,000,000,000)?
 */
public class PE210_Obtuse_Angled_Triangles {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long r = 1000000000, result = 0;
		result += 2 * r * (r + 1) + 1;
		result -= (2 * r + 1) * r / 4 + r + 1;
		result -= r / 2 + r / 4;
		result += countCircle(r * r / 32);
		result -= r / 4 - 1;

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long countCircle(long r2) {
		long x = 0, x2 = 1;

		while (x2 < r2) {
			x2 += x + x + 1;
			x++;
		}

		long total = 0, y = 0, y2next = 1;

		while (true) {
			x--;
			x2 -= x + x + 1;

			while (x2 + y2next < r2) {
				y++;
				y2next += y + y + 1;
			}

			if (x < y) {
				break;
			}

			total += 8 * y + 4;
		}

		total += (2 * x + 1) * (2 * x + 1);

		return total;
	}
}
