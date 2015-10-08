package Solved_151_175;

/**
 * Hexadecimal numbers
 * Problem 162
 * 
 * In the hexadecimal number system numbers are represented using 16 different
 * digits: 0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F
 * 
 * The hexadecimal number AF when written in the decimal number system equals
 * 10x16+15=175.
 * 
 * In the 3-digit hexadecimal numbers 10A, 1A0, A10, and A01 the digits 0,1 and
 * A are all present. Like numbers written in base ten we write hexadecimal
 * numbers without leading zeroes.
 * 
 * How many hexadecimal numbers containing at most sixteen hexadecimal digits
 * exist with all of the digits 0,1, and A present at least once? Give your
 * answer as a hexadecimal number.
 * 
 * (A,B,C,D,E and F in upper case, without any leading or trailing code that
 * marks the number as hexadecimal and without leading zeroes , e.g. 1A3F and
 * not: 1a3f and not 0x1a3f and not $1A3F and not #1A3F and not 0000001A3F)
 */
public class PE162_Hexadecimal_numbers {
	private static final int _NIL = 0, _0 = 1, _1 = 2, _A = 4;
	private static final long[] _POWERS = new long[15];

	public static void main(String[] args) {
		long start = System.nanoTime();

		String result = Long.toHexString(count(_NIL, false, 16)).toUpperCase();

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println(
				"Runtime: " + runtime / 1000000 + "ms (" + runtime + "ns)");
	}

	static {
		_POWERS[0] = 16L;

		for (int k = 1; k < _POWERS.length; k++) {
			_POWERS[k] = 16 * _POWERS[k - 1];
		}
	}

	private static long count(int flags, boolean allowLeadingZero, int digits) {
		if (digits == 0) {
			if (flags == _0 + _1 + _A) {
				return 1;
			}

			return 0;
		}

		int k = digits - 1;

		switch (flags) {
			case _NIL:
				return (13 * count(_NIL, true, k))
						+ count(allowLeadingZero ? _0 : _NIL, allowLeadingZero,
								k)
						+ count(_1, true, k) + count(_A, true, k);
			case _0:
				return (14 * count(_0, true, k)) + count(_0 + _1, true, k)
				+ count(_0 + _A, true, k);
			case _1:
				return (14 * count(_1, true, k)) + count(_1 + _0, true, k)
				+ count(_1 + _A, true, k);
			case _0 + _1:
				return (15 * count(_0 + _1, true, k))
						+ count(_0 + _1 + _A, true, k);
			case _A:
				return (14 * count(_A, true, k)) + count(_A + _0, true, k)
				+ count(_A + _1, true, k);
			case _0 + _A:
				return (15 * count(_0 + _A, true, k))
						+ count(_0 + _1 + _A, true, k);
			case _1 + _A:
				return (15 * count(_1 + _A, true, k))
						+ count(_0 + _1 + _A, true, k);
			case _0 + _1 + _A:
				return _POWERS[k];
		}

		return 0L;
	}
}
