package Solved_176_200;

/**
 * RSA encryption
 * Problem 182
 * 
 * The RSA encryption is based on the following procedure:
 * 
 * Generate two distinct primes p and q. Compute n=pq and phi=(p-1)(q-1). Find
 * an integer e, 1<e<phi, such that gcd(e,phi)=1.
 * 
 * A message in this system is a number in the interval [0,n-1]. A text to be
 * encrypted is then somehow converted to messages (numbers in the interval
 * [0,n-1]). To encrypt the text, for each message, m, c=m^e mod n is
 * calculated.
 * 
 * To decrypt the text, the following procedure is needed: calculate d such that
 * ed=1 mod phi, then for each encrypted message, c, calculate m=c^d mod n.
 * 
 * There exist values of e and m such that m^e mod n=m. We call messages m for
 * which me mod n=m unconcealed messages.
 * 
 * An issue when choosing e is that there should not be too many unconcealed
 * messages. For instance, let p=19 and q=37. Then n=19*37=703 and
 * phi=18*36=648. If we choose e=181, then, although gcd(181,648)=1 it turns out
 * that all possible messages m (0<=m<=n-1) are unconcealed when calculating m^e
 * mod n. For any valid choice of e there exist some unconcealed messages. It's
 * important that the number of unconcealed messages is at a minimum.
 * 
 * Choose p=1009 and q=3643. Find the sum of all values of e, 1<e<phi(1009,3643)
 * and gcd(e,phi)=1, so that the number of unconcealed messages for this value
 * of e is at a minimum.
 */
public class PE182_RSA_encryption {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int p = 1009;
		int q = 3643;
		int f = (p - 1) * (q - 1);
		long result = 0;

		for (int e = 2; e < f; e++) {
			if (gcd(e, f) == 1 && gcd(e - 1, f) == 2) {
				result += e;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
}
