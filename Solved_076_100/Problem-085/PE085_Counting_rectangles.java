package Solved_076_100;

/**
 * Counting rectangles
 * Problem 85
 * 
 * By counting carefully it can be seen that a rectangular grid measuring 3 by 2
 * contains eighteen rectangles:
 * 
 * Although there exists no rectangular grid that contains exactly two million
 * rectangles, find the area of the grid with the nearest solution.
 */
public class PE085_Counting_rectangles {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int minDiff = 2000000, result = 0;

		for (int a = 1; a * (a + 1) / 2 < Math.sqrt(2000000); a++) {
			double t = (double) 2000000 / (0.5 * a * (a + 1));
			int b0 = (int) (0.5 + 0.5 * Math.sqrt(1.0 + 8 * t));

			for (int b = b0 - 1; b - b0 < 2; b++) {
				int area = a * b;
				int nRect = a * (a + 1) * b * (b + 1) / 4;

				if (Math.abs(2000000 - nRect) < minDiff) {
					result = area;
					minDiff = Math.abs(2000000 - nRect);
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
