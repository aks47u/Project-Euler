package Solved_201_225;

import java.util.ArrayList;

/**
 * Laserbeam
 * Problem 202
 * 
 * Three mirrors are arranged in the shape of an equilateral triangle, with
 * their reflective surfaces pointing inwards. There is an infinitesimal gap at
 * each vertex of the triangle through which a laser beam may pass.
 * 
 * Label the vertices A, B and C. There are 2 ways in which a laser beam may
 * enter vertex C, bounce off 11 surfaces, then exit through the same vertex:
 * one way is shown below; the other is the reverse of that.
 * 
 * There are 80840 ways in which a laser beam may enter vertex C, bounce off
 * 1000001 surfaces, then exit through the same vertex.
 * 
 * In how many ways can a laser beam enter at vertex C, bounce off 12017639147
 * surfaces, then exit through the same vertex?
 */
public class PE202_Laserbeam {
	private static long b = 12017639147l;
	private static long d;
	private static long m;
	private static long[][] p;
	private static ArrayList<Long>[] pTemp;
	private static long[] primeFac;
	private static int n;
	private static long m2;

	public static void main(String[] args) {
		long start = System.nanoTime();

		init();
		primeFactor();
		computeComb();
		long result = momentDeGloire();

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static void init() {
		d = (b + 3) / 2;

		if (d % 3 == 0 || d < 5) {
			throw new ArithmeticException("There is no posible solution");
		}

		if (d % 2 == 0) {
			if (d % 4 == 0) {
				m = -(long) Math.floor((-d + 6l) / 12.0);
				m2 = 2 * m - 1;
			} else {
				m = -(long) Math.floor((-d + 12l) / 12.0);
				m2 = m;
			}
		} else {
			m = -(long) Math.floor((-d + 3) / 6.0);
			m2 = 2 * m - 1;
		}
	}

	private static void primeFactor() {
		long prime = 5, r = d;
		boolean isPresent = false;
		ArrayList<Long> a = new ArrayList<Long>();
		ArrayList<Long> pl = new ArrayList<Long>();
		pl.add(3l);
		pl.add(prime);

		while (r % 2 == 0) {
			r /= 2;
		}

		do {
			if (r % prime == 0) {
				if (!isPresent) {
					a.add(prime);
					isPresent = true;
				}

				r /= prime;
			} else {
				isPresent = false;
				boolean isPrime = false;

				while (!isPrime) {
					prime += 2;
					isPrime = true;
					long sqrt = (long) Math.sqrt(prime) + 1;

					for (int i = 0; i < pl.size() && pl.get(i) <= sqrt; i++) {
						isPrime &= (prime % pl.get(i) != 0);
					}
				}

				pl.add(prime);
			}
		} while (r != 1);

		n = a.size();
		primeFac = new long[n];
		int i = 0;

		for (Long l : a) {
			primeFac[i++] = l;
		}
	}

	@SuppressWarnings("unchecked")
	private static void computeComb() {
		if (d % 4 != 2) {
			long[] temp = new long[n + 1];
			temp[0] = 2;
			System.arraycopy(primeFac, 0, temp, 1, n++);
			primeFac = temp;
		}

		p = new long[n][];
		pTemp = new ArrayList[n];

		for (int i = 0; i < n; i++) {
			pTemp[i] = new ArrayList<Long>();
		}

		recurse(1, 0, 0);
		int j = 0;

		for (ArrayList<Long> al : pTemp) {
			p[j] = new long[al.size()];
			int k = 0;

			for (long l : al) {
				p[j][k++] = l;
			}

			j++;
		}
	}

	private static void recurse(long result, int index, int level) {
		if (index >= n) {
			return;
		}

		for (int i = index; i < n; i++) {
			long temp = result * primeFac[i];
			pTemp[level].add(temp);
			recurse(temp, i + 1, level + 1);
		}
	}

	private static long momentDeGloire() {
		long temp = m2;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < p[i].length; j++) {
				temp += (-1 + 2 * (i % 2))
						* (long) Math.floor(m2 * 1.0 / p[i][j]);
			}
		}

		return temp * 2;
	}
}
