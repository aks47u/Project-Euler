package Solved_176_200;

import java.util.Set;
import java.util.TreeSet;

/**
 * Rational zeros of a function of three variables
 * Problem 180
 * 
 * For any integer n, consider the three functions
 * 
 * f1,n(x,y,z) = xn+1 + yn+1 - zn+1
 * f2,n(x,y,z) = (xy + yz + zx)*(xn-1 + yn-1 - zn-1)
 * f3,n(x,y,z) = xyz*(xn-2 + yn-2 - zn-2)
 * 
 * and their combination
 * 
 * fn(x,y,z) = f1,n(x,y,z) + f2,n(x,y,z) - f3,n(x,y,z)
 * 
 * We call (x,y,z) a golden triple of order k if x, y, and z are all rational
 * numbers of the form a / b with 0 < a < b <= k and there is (at least) one
 * integer n, so that fn(x,y,z) = 0.
 * 
 * Let s(x,y,z) = x + y + z.
 * Let t = u / v be the sum of all distinct s(x,y,z) for all golden triples
 * (x,y,z) of order 35.
 * All the s(x,y,z) and t must be in reduced form.
 * 
 * Find u + v.
 */
public class PE180_Rational_zeros_of_a_function_of_three_variables {
	private int size = 35;
	private Set<Rational> sset = new TreeSet<Rational>();
	private static long result;

	public static void main(String[] args) {
		long start = System.nanoTime();

		@SuppressWarnings("unused")
		PE180_Rational_zeros_of_a_function_of_three_variables p = new PE180_Rational_zeros_of_a_function_of_three_variables();

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	PE180_Rational_zeros_of_a_function_of_three_variables() {
		for (long za = 1; za <= size; za++) {
			for (long zb = za + 1; zb <= size; zb++) {
				if (gcd(za, zb) == 1) {
					Rational z = new Rational(za, zb);

					for (long xa = 1; xa <= size; xa++) {
						for (long xb = xa + 1; xb <= size; xb++) {
							if (gcd(xa, xb) == 1) {
								Rational x = new Rational(xa, xb);
								Rational y = new Rational(z);
								y.sub(x);

								if (y.isValid()) {
									addSol(x, y, z, 1);
								}

								Rational x2 = new Rational(x);
								x2.mult(x);
								Rational z2 = new Rational(z);
								z2.mult(z);
								y = new Rational(z2);
								y.sub(x2);

								if (y.trySqrt() && y.isValid()) {
									addSol(x, y, z, 2);
								}

								x2 = new Rational(x);
								x2.recip();
								z2 = new Rational(z);
								z2.recip();
								y = new Rational(z2);
								y.sub(x2);
								y.recip();

								if (y.isValid()) {
									addSol(x, y, z, -1);
								}

								x2 = new Rational(x);
								x2.recip();
								x2.mult(x2);
								z2 = new Rational(z);
								z2.recip();
								z2.mult(z2);
								y = new Rational(z2);
								y.sub(x2);
								y.recip();

								if (y.trySqrt() && y.isValid()) {
									addSol(x, y, z, -2);
								}
							}
						}
					}
				}
			}
		}

		Rational t = new Rational(0, 1);
		
		for (Rational s : sset) {
			t.add(s);
		}

		result = t.getDen() + t.getNum();
	}

	long gcd(long a, long b) {
		if (a == 0 || b == 0) {
			return a + b;
		}

		long a2 = a < 0 ? -a : a;
		long b2 = b < 0 ? -b : b;

		return (a2 > b2) ? gcd(a2 % b2, b2) : gcd(a2, b2 % a2);
	}

	void addSol(Rational x, Rational y, Rational z, int n) {
		Rational s = new Rational(x);
		s.add(y);
		s.add(z);

		if (sset.add(s)) {
		}
	}

	class Rational implements Comparable<Rational> {
		private long num, den;

		public Rational(long x, long y) {
			num = x;
			den = y;
			reduce();
		}

		public Rational(Rational r2) {
			num = r2.num;
			den = r2.den;
		}

		public void reduce() {
			long g = gcd(den, num);
			num /= g;
			den /= g;
		}

		public void add(Rational r2) {
			long g = gcd(den, r2.den);
			num = num * (r2.den / g) + r2.num * (den / g);
			den *= r2.den / g;
			reduce();
		}

		public void sub(Rational r2) {
			long g = gcd(den, r2.den);
			num = num * (r2.den / g) - r2.num * (den / g);
			den *= r2.den / g;
			reduce();
		}

		public void mult(Rational r2) {
			num *= r2.num;
			den *= r2.den;
			reduce();
		}

		public void recip() {
			long t = num;
			num = den;
			den = t;
		}

		public boolean trySqrt() {
			if (num < 0) {
				return false;
			}

			long n = (long) (.5 + Math.sqrt(num));

			if (n * n != num) {
				return false;
			}

			long d = (long) (.5 + Math.sqrt(den));

			if (d * d != den) {
				return false;
			}

			num = n;
			den = d;

			return true;
		}

		public int compareTo(Rational r2) {
			long g = gcd(den, r2.den);
			long d = num * (r2.den / g) - (den / g) * r2.num;

			if (d < 0) {
				return -1;
			}

			if (d > 0) {
				return 1;
			}

			return 0;
		}

		public boolean isValid() {
			return num > 0 && den > num && den <= size;
		}

		public String toString() {
			return num + "/" + den;
		}

		public long getNum() {
			return num;
		}

		public long getDen() {
			return den;
		}
	}
}
