package Solved_151_175;

import java.util.TreeSet;

/**
 * Intersections
 * Problem 165
 * 
 * A segment is uniquely defined by its two endpoints. By considering two line
 * segments in plane geometry there are three possibilities: the segments have
 * zero points, one point, or infinitely many points in common.
 * 
 * Moreover when two segments have exactly one point in common it might be the
 * case that that common point is an endpoint of either one of the segments or
 * of both. If a common point of two segments is not an endpoint of either of
 * the segments it is an interior point of both segments. We will call a common
 * point T of two segments L1 and L2 a true intersection point of L1 and L2 if T
 * is the only common point of L1 and L2 and T is an interior point of both
 * segments.
 * 
 * Consider the three segments L1, L2, and L3:
 * 
 * L1: (27, 44) to (12, 32)
 * L2: (46, 53) to (17, 62)
 * L3: (46, 70) to (22, 40)
 * 
 * It can be verified that line segments L2 and L3 have a true intersection
 * point. We note that as the one of the end points of L3: (22,40) lies on L1
 * this is not considered to be a true point of intersection. L1 and L2 have no
 * common point. So among the three line segments, we find one true intersection
 * point.
 * 
 * Now let us do the same for 5000 line segments. To this end, we generate 20000
 * numbers using the so-called "Blum Blum Shub" pseudo-random number generator.
 * 
 * s0 = 290797
 * sn+1 = sn×sn (modulo 50515093)
 * tn = sn (modulo 500)
 * 
 * To create each line segment, we use four consecutive numbers tn. That is, the
 * first line segment is given by:
 * 
 * (t1, t2) to (t3, t4)
 * 
 * The first four numbers computed according to the above generator should be:
 * 27, 144, 12 and 232. The first segment would thus be (27,144) to (12,232).
 * 
 * How many distinct true intersection points are found among the 5000 line
 * segments?
 */
public class PE165_Intersections {
	private static int[] data = new int[20000];
	private static Line[] lines = new Line[5000];
	private static double EPS = 1e-9;

	public static void main(String[] args) {
		long t0 = System.currentTimeMillis();
		genRandom();

		for (int n = 0; n < 4 * 5000; n += 4) {
			lines[n / 4] = new Line(data[n], data[n + 1], data[n + 2],
					data[n + 3]);
		}

		TreeSet<Point> ts = new TreeSet<Point>();

		for (int i = 0; i < 5000; i++) {
			for (int j = i + 1; j < 5000; j++) {
				if (lines[i].isTrueBoth(lines[j])) {
					ts.add(lines[i].point(lines[i].intersect(lines[j])));
				}
			}
		}

		System.out.println(ts.size());
		System.out.println("time:" + (System.currentTimeMillis() - t0) + "ms");
	}

	private static void genRandom() {
		long s = 290797;

		for (int i = 0; i < 20000; i++) {
			s = (s * s) % 50515093L;
			data[i] = (int) (s % 500L);
		}
	}

	public static class Line {
		public int x0, y0, x1, y1;

		public Line(int px0, int py0, int px1, int py1) {
			x0 = px0;
			y0 = py0;
			x1 = px1;
			y1 = py1;
		}

		public boolean isTrueBoth(Line line) {
			return this.isTrueIntersection(line)
					&& line.isTrueIntersection(this);
		}

		public boolean isTrueIntersection(Line line) {
			int mx = x1 - x0, my = y1 - y0;
			int kx = line.x1 - line.x0, ky = line.y1 - line.y0;
			int t = (kx * (y0 - line.y0) + ky * (line.x0 - x0));
			int b = (mx * ky - my * kx);

			if ((t == 0 || b == 0) || (b > 0 && t < 0) || (b < 0 && t > 0)) {
				return false;
			}

			if (b < 0) {
				return -t < -b;
			}

			return t < b;
		}

		public double intersect(Line line) {
			double mx = x1 - x0, my = y1 - y0;
			double kx = line.x1 - line.x0, ky = line.y1 - line.y0;

			return (kx * (y0 - line.y0) + ky * (line.x0 - x0))
					/ (mx * ky - my * kx);
		}

		public Point point(double t) {
			return new Point(x0 + (x1 - x0) * t, y0 + (y1 - y0) * t);
		}
	}

	public static class Point implements Comparable<Object> {
		double x, y;

		public Point(double px, double py) {
			x = px;
			y = py;
		}

		public int compareTo(Object o) {
			Point p = (Point) o;

			if (Math.abs(p.x - x) > EPS) {
				return (int) Math.signum(p.x - x);
			}

			if (Math.abs(p.y - y) > EPS) {
				return (int) Math.signum(p.y - y);
			}

			return 0;
		}
	}
}
