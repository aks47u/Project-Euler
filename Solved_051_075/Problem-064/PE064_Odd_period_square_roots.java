package Solved_051_075;

import java.math.BigInteger;

/**
 * Odd period square roots
 * Problem 64
 * 
 * All square roots are periodic when written as continued fractions and can be
 * written in the form: sqrtN = a0 + 1 a1 + 1 a2 + 1 a3 + ...
 * 
 * For example, let us consider sqrt23: sqrt23 = 4 + sqrt23 - 4 = 4 + 1 = 4 + 1
 * 
 * 1 sqrt23-4 1 + sqrt23 - 3 7
 * 
 * If we continue we would get the following expansion: sqrt23 = 4 + 1 1 + 1 3 + 1
 * 1 + 1 8 + ...
 * 
 * The process can be summarised as follows: a0 = 4, 1 sqrt23-4 = sqrt23+4 7 = 1 +
 * sqrt23-3 7 a1 = 1, 7 sqrt23-3 = 7(sqrt23+3) 14 = 3 + sqrt23-3 2 a2 = 3, 2 sqrt23-3 =
 * 2(sqrt23+3) 14 = 1 + sqrt23-4 7 a3 = 1, 7 sqrt23-4 = 7(sqrt23+4) 7 = 8 + sqrt23-4 a4 = 8, 1
 * sqrt23-4 = sqrt23+4 7 = 1 + sqrt23-3 7 a5 = 1, 7 sqrt23-3 = 7(sqrt23+3) 14 = 3 + sqrt23-3 2 a6
 * = 3, 2 sqrt23-3 = 2(sqrt23+3) 14 = 1 + sqrt23-4 7 a7 = 1, 7 sqrt23-4 = 7(sqrt23+4) 7 = 8 +
 * sqrt23-4
 * 
 * It can be seen that the sequence is repeating. For conciseness, we use the
 * notation sqrt23 = [4;(1,3,1,8)], to indicate that the block (1,3,1,8) repeats
 * indefinitely.
 * 
 * The first ten continued fraction representations of (irrational) square roots
 * are:
 * 
 * sqrt2=[1;(2)], period=1 sqrt3=[1;(1,2)], period=2 sqrt5=[2;(4)], period=1
 * sqrt6=[2;(2,4)], period=2 sqrt7=[2;(1,1,1,4)], period=4 sqrt8=[2;(1,4)], period=2
 * sqrt10=[3;(6)], period=1 sqrt11=[3;(3,6)], period=2 sqrt12= [3;(2,6)], period=2
 * sqrt13=[3;(1,1,1,1,6)], period=5
 * 
 * Exactly four continued fractions, for N â‰¤ 13, have an odd period.
 * 
 * How many continued fractions for N â‰¤ 10000 have an odd period?
 */
public class PE064_Odd_period_square_roots {
	private int k = 0;

	public static void main(String[] args) throws Exception {
		long start = System.nanoTime();

		System.out.println(new PE064_Odd_period_square_roots().run());

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	public class Fr {
		public int a, b, c, n;

		public Fr(int na, int nb, int nc, int nn) {
			a = na;
			b = nb;
			c = nc;
			n = nn;

			while (n > (b + c) * (b + c)) {
				a++;
				b += c;
			}
		}

		public Fr(Fr o) {
			a = o.a;
			b = o.b;
			c = o.c;
			n = o.n;
		}

		public Fr next() {
			int d = (n - b * b);
			int g = (new BigInteger(Integer.toString(c))).gcd(
					new BigInteger(Integer.toString(d))).intValue();
			Fr result = new Fr(0, (c / g) * (-b), d / g, n);

			return result;
		}

		public String toString() {
			return a + "+(\\" + n + " - " + b + ") / " + c;
		}
	}

	public int run() throws Exception {
		int res = 0;

		for (int i = 2; i <= 10000; i++) {
			int s = (int) Math.sqrt(i);

			if (s * s == i) {
				continue;
			}

			if (sContRoot(i) % 2 != 0) {
				res++;
			}
		}

		return res;
	}

	public int sContRoot(int n) {
		contRoot(n);
		return k;
	}

	public String contRoot(int n) {
		k = 0;
		int a0 = (int) Math.floor(Math.sqrt(n));
		String res = "[" + a0 + ";";
		Fr fi = new Fr(a0, a0, 1, n);

		do {
			fi = fi.next();
			res += "," + (fi.a);
			k++;
		} while (fi.a != a0 * 2);

		return res + "]";
	}
}
