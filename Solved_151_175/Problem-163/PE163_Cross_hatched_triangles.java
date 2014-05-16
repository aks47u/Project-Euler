package Solved_151_175;

import java.util.HashSet;
import java.util.Set;

/**
 * Cross-hatched triangles
 * Problem 163
 * 
 * Consider an equilateral triangle in which straight lines are drawn from each
 * vertex to the middle of the opposite side, such as in the size 1 triangle in
 * the sketch below.
 * 
 * Sixteen triangles of either different shape or size or orientation or
 * location can now be observed in that triangle. Using size 1 triangles as
 * building blocks, larger triangles can be formed, such as the size 2 triangle
 * in the above sketch. One-hundred and four triangles of either different shape
 * or size or orientation or location can now be observed in that size 2
 * triangle.
 * 
 * It can be observed that the size 2 triangle contains 4 size 1 triangle
 * building blocks. A size 3 triangle would contain 9 size 1 triangle building
 * blocks and a size n triangle would thus contain n2 size 1 triangle building
 * blocks.
 * 
 * If we denote T(n) as the number of triangles present in a triangle of size n,
 * then
 * 
 * T(1) = 16
 * T(2) = 104
 * 
 * Find T(36).
 */
public class PE163_Cross_hatched_triangles {
	private static int SIZE = 36;
	private static int MAX_NUM_LINES = SIZE * 3;
	@SuppressWarnings("unchecked")
	private static Set<Point>[][] linePoints = new HashSet[6][MAX_NUM_LINES];
	private static Point[][][][] intersection = new Point[6][MAX_NUM_LINES][6][MAX_NUM_LINES];

	public static void main(String[] args) {
		long start = System.nanoTime();

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < MAX_NUM_LINES; j++) {
				linePoints[i][j] = new HashSet<Point>();
			}
		}

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j <= i; j++) {
				Point p1 = new Point(i, i + j, j, SIZE - i + 2 * j, i - j, 2
						* i - j);
				Point p2 = new Point(i + 1, i + j + 1, j, SIZE - i - 1 + 2 * j,
						i - j + 1, 2 * i - j + 2);
				Point p3 = new Point(i + 1, i + j + 2, j + 1, SIZE - i + 1 + 2
						* j, i - j, 2 * i - j + 1);
				Point p4 = new Point(Point.INVALID, Point.INVALID, j,
						Point.INVALID, Point.INVALID, 2 * i - j + 1);
				Point p5 = new Point(Point.INVALID, i + j + 1, Point.INVALID,
						Point.INVALID, i - j, Point.INVALID);
				Point p6 = new Point(i + 1, Point.INVALID, Point.INVALID, SIZE
						- i + 2 * j, Point.INVALID, Point.INVALID);
				Point p7 = new Point(Point.INVALID, p2.line[1], Point.INVALID,
						p1.line[3], Point.INVALID, p3.line[5]);
				registerPoint(p1);
				registerPoint(p2);
				registerPoint(p3);
				registerPoint(p4);
				registerPoint(p5);
				registerPoint(p6);
				registerPoint(p7);

				if (i < SIZE - 1) {
					Point p8 = new Point(Point.INVALID, p7.line[1] + 1,
							Point.INVALID, p7.line[3], Point.INVALID,
							p7.line[5] + 1);
					registerPoint(p8);
				}
			}
		}

		long count = 0;

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < MAX_NUM_LINES; j++) {
				Object[] points = linePoints[i][j].toArray();

				for (int i1 = 0; i1 < points.length; i1++) {
					for (int i2 = i1 + 1; i2 < points.length; i2++) {
						Point p1 = (Point) points[i1];
						Point p2 = (Point) points[i2];

						for (int j1 = 0; j1 < 6; j1++) {
							if (j1 != i && p1.line[j1] != Point.INVALID) {
								for (int j2 = 0; j2 < 6; j2++) {
									if (j2 != i && j2 != j1
											&& p2.line[j2] != Point.INVALID) {
										if (intersection[j1][p1.line[j1]][j2][p2.line[j2]] != null) {
											count++;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		long result = count / 3;

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static void registerPoint(Point p) {
		for (int i = 0; i < 6; i++) {
			if (p.line[i] != Point.INVALID) {
				linePoints[i][p.line[i]].add(p);
			}
		}

		for (int i = 0; i < 6; i++) {
			if (p.line[i] != Point.INVALID) {
				for (int j = 0; j < 6; j++) {
					if (p.line[j] != Point.INVALID && i != j) {
						intersection[i][p.line[i]][j][p.line[j]] = p;
					}
				}
			}
		}
	}

	private static class Point {
		private static int INVALID = -1;
		private int[] line = new int[6];

		public Point(int l0, int l1, int l2, int l3, int l4, int l5) {
			line[0] = l0;
			line[1] = l1;
			line[2] = l2;
			line[3] = l3;
			line[4] = l4;
			line[5] = l5;
		}

		public boolean equals(Object o) {
			Point p = (Point) o;

			return p.line[0] == line[0] && p.line[1] == line[1]
					&& p.line[2] == line[2] && p.line[3] == line[3]
							&& p.line[4] == line[4] && p.line[5] == line[5];
		}

		public int hashCode() {
			int hash = line[0];
			hash = hash * 37 + line[1];
			hash = hash * 37 + line[2];
			hash = hash * 37 + line[3];
			hash = hash * 37 + line[4];
			hash = hash * 37 + line[5];

			return hash;
		}
	}
}
