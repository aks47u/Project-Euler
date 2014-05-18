package Solved_176_200;

/**
 * Connectedness of a network
 * Problem 186
 * 
 * Here are the records from a busy telephone system with one million users:
 * RecNr	Caller	Called
 * 1		200007	100053
 * 2		600183	500439
 * 3		600863	701497
 * ...		...		...
 * 
 * The telephone number of the caller and the called number in record n are
 * Caller(n) = S2n-1 and Called(n) = S2n where S1,2,3,... come from the
 * "Lagged Fibonacci Generator":
 * 
 * For 1 <= k <= 55, Sk = [100003 - 200003k + 300007k^3] (modulo 1000000)
 * For 56 <= k, Sk = [Sk-24 + Sk-55] (modulo 1000000)
 * 
 * If Caller(n) = Called(n) then the user is assumed to have misdialled and the
 * call fails; otherwise the call is successful.
 * 
 * From the start of the records, we say that any pair of users X and Y are
 * friends if X calls Y or vice-versa. Similarly, X is a friend of a friend of Z
 * if X is a friend of Y and Y is a friend of Z; and so on for longer chains.
 * 
 * The Prime Minister's phone number is 524287. After how many successful calls,
 * not counting misdials, will 99% of the users (including the PM) be a friend,
 * or a friend of a friend etc., of the Prime Minister?
 */
public class PE186_Connectedness_of_a_network {
	private static int i = 0;
	private static int[] lf = new int[55];

	public static void main(String[] args) {
		long start = System.nanoTime();

		final int PM = 524287;
		UnionSet[] sets = new UnionSet[1000000];
		int result = 0;

		while (true) {
			int caller = next();
			int called = next();

			if (caller != called) {
				result++;

				// Add caller.
				UnionSet us1 = sets[caller];

				if (us1 == null) {
					us1 = sets[caller] = new UnionSet();
					us1.size++;
				}

				// Add called.
				UnionSet us2 = sets[called];

				if (us2 == null) {
					us2 = sets[called] = new UnionSet();
					us2.size++;
				}

				us1.union(us2);

				// Test end condition.
				if (sets[PM] != null && sets[PM].get().size >= 990000) {
					long end = System.nanoTime();
					long runtime = end - start;
					System.out.println(result);
					System.out.println("Runtime: " + runtime / 1000000 + "ms ("
							+ runtime + "ns)");

					return;
				}
			}
		}
	}

	private static class UnionSet {
		public UnionSet parent;
		public int size;

		public UnionSet get() {
			UnionSet rv = this;

			while (rv.parent != null) {
				rv = rv.parent;

			}

			// Optimisation
			UnionSet foo = this;

			while (foo != rv) {
				UnionSet bar = foo.parent;
				foo.parent = rv;
				foo = bar;
			}

			return rv;
		}

		public void union(UnionSet that) {
			UnionSet p1 = get(), p2 = that.get();

			if (p1 == p2 || p2 == this || p1 == that) {
				return;
			}

			// Unrelated.
			UnionSet rv = new UnionSet();
			rv.size = p1.size + p2.size;
			p1.parent = p2.parent = rv;
		}
	}

	private static int next() {
		i++;
		int rv;

		if (i <= 55) {
			long i3 = i * i * i;
			long rvi = (100003 - 200003 * i + 300007 * i3) % 1000000;
			rv = (int) rvi;

			if (rv < 0) {
				rv += 1000000;
			}
		} else {
			rv = (lf[(i - 24) % 55] + lf[i % 55]) % 1000000;
		}

		lf[i % 55] = rv;

		return rv;
	}
}
