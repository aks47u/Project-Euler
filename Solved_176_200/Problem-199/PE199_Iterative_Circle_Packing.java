package Solved_176_200;

/**
 * Iterative Circle Packing
 * Problem 199
 * 
 * Three circles of equal radius are placed inside a larger circle such that
 * each pair of circles is tangent to one another and the inner circles do not
 * overlap. There are four uncovered "gaps" which are to be filled iteratively
 * with more tangent circles.
 * 
 * At each iteration, a maximally sized circle is placed in each gap, which
 * creates more gaps for the next iteration. After 3 iterations (pictured),
 * there are 108 gaps and the fraction of the area which is not covered by
 * circles is 0.06790342, rounded to eight decimal places.
 * 
 * What fraction of the area is not covered by circles after 10 iterations? Give
 * your answer rounded to eight decimal places using the format x.xxxxxxxx .
 */
public class PE199_Iterative_Circle_Packing {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int size = 10;
		double k1 = 1, k0 = k1 * (3 - 2 * Math.sqrt(3));
		double a0 = 1 / (k0 * k0), a1 = 3 / (k1 * k1);
		a1 += 3 * getArea(k0, k1, k1, size);
		a1 += getArea(k1, k1, k1, size);
		double result = (a0 - a1) / a0;

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.printf(String.format("%.6g%n", result));
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static double getArea(double k1, double k2, double k3, int depth) {
		if (depth == 0) {
			return 0.0;
		}

		double k4 = k1 + k2 + k3 + 2 * Math.sqrt(k1 * k2 + k2 * k3 + k3 * k1);
		double a = 1 / (k4 * k4);
		a += getArea(k1, k2, k4, depth - 1);
		a += getArea(k2, k3, k4, depth - 1);
		a += getArea(k3, k1, k4, depth - 1);

		return a;
	}
}
