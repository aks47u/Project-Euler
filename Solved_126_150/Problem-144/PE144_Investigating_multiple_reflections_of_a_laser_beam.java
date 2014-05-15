package Solved_126_150;

/**
 * Investigating multiple reflections of a laser beam
 * Problem 144
 * 
 * In laser physics, a "white cell" is a mirror system that acts as a delay line
 * for the laser beam. The beam enters the cell, bounces around on the mirrors,
 * and eventually works its way back out.
 * 
 * The specific white cell we will be considering is an ellipse with the
 * equation 4x2 + y2 = 100
 * 
 * The section corresponding to -0.01 <= x <= +0.01 at the top is missing,
 * allowing the light to enter and exit through the hole.
 * 
 * The light beam in this problem starts at the point (0.0,10.1) just outside
 * the white cell, and the beam first impacts the mirror at (1.4,-9.6).
 * 
 * Each time the laser beam hits the surface of the ellipse, it follows the
 * usual law of reflection "angle of incidence equals angle of reflection." That
 * is, both the incident and reflected beams make the same angle with the normal
 * line at the point of incidence.
 * 
 * In the figure on the left, the red line shows the first two points of contact
 * between the laser beam and the wall of the white cell; the blue line shows
 * the line tangent to the ellipse at the point of incidence of the first
 * bounce.
 * 
 * The slope m of the tangent line at any point (x,y) of the given ellipse is: m
 * = -4x/y
 * 
 * The normal line is perpendicular to this tangent line at the point of
 * incidence.
 * 
 * The animation on the right shows the first 10 reflections of the beam.
 * 
 * How many times does the beam hit the internal surface of the white cell
 * before exiting?
 */
public class PE144_Investigating_multiple_reflections_of_a_laser_beam {
	public static final double EPS = .0001;
	public static final int INF = Integer.MAX_VALUE >> 2;

	public static void main(String[] args) {
		long start = System.nanoTime();

		double prevx = 0, prevy = 10.1;
		double curx = 1.4, cury = -9.6;
		double nextx = 0, nexty = 0;
		int result = 0;

		while (true) {
			if (nextx >= -0.01 && nextx <= 0.01 && nexty > 0) {
				break;
			}

			Point p = new Point(curx, cury);
			Line m0 = new Line(p, cury / (4. * curx));
			Point p1 = m0.closest_Point(new Point(prevx, prevy));
			Point p2 = new Point(2. * p1.x - prevx, 2. * p1.y - prevy);
			Line m1 = new Line(p, p2);
			double x = 0, y = 0;

			if (m1.b == 0) {
				x = curx;
				y = -cury;
			} else {
				double m = -m1.a / m1.b, d = -m1.c / m1.b;
				double a = 4 + sq(m), b = 2 * m * d, c = sq(d) - 100;
				x = (-b + Math.sqrt(sq(b) - 4 * a * c)) / (2. * a);
				y = m * x + d;

				if (Math.abs(x - curx) <= EPS && Math.abs(y - cury) <= EPS) {
					x = (-b - Math.sqrt(sq(b) - 4 * a * c)) / (2. * a);
					y = m * x + d;
				}
			}

			nextx = x;
			nexty = y;
			prevx = curx;
			prevy = cury;
			curx = nextx;
			cury = nexty;
			result++;
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static double sq(double x) {
		return x * x;
	}

	static class Point {
		public double x, y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Line {
		public double a, b, c;

		public Line(double a, double b, double c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		public Line(Point p1, Point p2) {
			if (p1.x == p2.x) {
				a = 1;
				b = 0;
				c = -p1.x;
			} else {
				b = 1;
				a = -(p1.y - p2.y) / (p1.x - p2.x);
				c = -(a * p1.x) - (b * p1.y);
			}
		}

		public Line(Point p, double m) {
			a = -m;
			b = 1;
			c = -((a * p.x) + (b * p.y));
		}

		public boolean parallel(Line l) {
			return (Math.abs(a - l.a) <= EPS && Math.abs(b - l.b) <= EPS);
		}

		public boolean same_line(Line l) {
			return (parallel(l) && Math.abs(c - l.c) <= EPS);
		}

		public Point intersection_point(Line l) {
			Point p = new Point(INF, INF);

			if (same_line(l)) {
				p.x = p.y = 0;

				return p;
			}

			if (parallel(l)) {
				return p;
			}

			p.x = (l.b * c - b * l.c) / (l.a * b - a * l.b);

			if (Math.abs(b) > EPS) {
				p.y = -(a * p.x + c) / b;
			} else {
				p.y = -(l.a * p.x + l.c) / l.b;
			}

			return p;
		}

		public Point closest_Point(Point p) {
			Point p_c = new Point(INF, INF);

			if (Math.abs(b) <= EPS) {
				p_c.x = -c;
				p_c.y = p.y;

				return p_c;
			}

			if (Math.abs(a) <= EPS) {
				p_c.x = p.x;
				p_c.y = -c;

				return p_c;
			}

			Line perp = new Line(p, 1 / a);

			return intersection_point(perp);
		}
	}
}
