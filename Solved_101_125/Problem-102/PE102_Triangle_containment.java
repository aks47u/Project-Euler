package Solved_101_125;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;

/**
 * Triangle containment
 * Problem 102
 * 
 * Three distinct points are plotted at random on a Cartesian plane, for which
 * -1000 <= x, y <= 1000, such that a triangle is formed.
 * 
 * Consider the following two triangles:
 * 
 * A(-340,495), B(-153,-910), C(835,-947)
 * 
 * X(-175,41), Y(-421,-714), Z(574,-645)
 * 
 * It can be verified that triangle ABC contains the origin, whereas triangle
 * XYZ does not.
 * 
 * Using triangles.txt (right click and 'Save Link/Target As...'), a 27K text
 * file containing the co-ordinates of one thousand "random" triangles, find the
 * number of triangles for which the interior contains the origin.
 * 
 * NOTE: The first two examples in the file represent the triangles in the
 * example given above.
 */
public class PE102_Triangle_containment {
	public static void main(String[] args) throws Exception {
		long start = System.nanoTime();
		
		int result = 0;
		BufferedReader br = new BufferedReader(new FileReader(
				"PE102_triangles.txt"));
		String line;

		while ((line = br.readLine()) != null) {
			String[] data = line.split(",");

			if (hasOrigin(Integer.parseInt(data[0]), Integer.parseInt(data[1]),
					Integer.parseInt(data[2]), Integer.parseInt(data[3]),
					Integer.parseInt(data[4]), Integer.parseInt(data[5]))) {
				result++;
			}
		}

		br.close();

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static boolean hasOrigin(int ax, int ay, int bx, int by, int cx,
			int cy) {
		double area1 = round(triangleArea(ax, ay, bx, by, 0, 0), 2,
				BigDecimal.ROUND_HALF_UP);
		double area2 = round(triangleArea(bx, by, cx, cy, 0, 0), 2,
				BigDecimal.ROUND_HALF_UP);
		double area3 = round(triangleArea(cx, cy, ax, ay, 0, 0), 2,
				BigDecimal.ROUND_HALF_UP);
		double fullArea = round(triangleArea(ax, ay, bx, by, cx, cy), 2,
				BigDecimal.ROUND_HALF_UP);

		if ((area1 + area2 + area3) == fullArea) {
			return true;
		} else {
			return false;
		}
	}

	private static double triangleArea(int ax, int ay, int bx, int by, int cx,
			int cy) {
		double a = Math.sqrt(Math.pow(cx - bx, 2) + Math.pow(cy - by, 2));
		double b = Math.sqrt(Math.pow(ax - cx, 2) + Math.pow(ay - cy, 2));
		double c = Math.sqrt(Math.pow(bx - ax, 2) + Math.pow(by - ay, 2));
		double s = (a + b + c) / 2;

		return Math.sqrt(s * (s - a) * (s - b) * (s - c)); // Heron's formula
	}

	private static double round(double unrounded, int precision, int roundingMode) {
		BigDecimal bd = new BigDecimal(unrounded);
		BigDecimal rounded = bd.setScale(precision, roundingMode);

		return rounded.doubleValue();
	}
}
