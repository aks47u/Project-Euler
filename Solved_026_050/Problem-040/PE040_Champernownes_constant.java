package Solved_026_050;

/**
 * Champernowne's constant
 * Problem 40
 * 
 * An irrational decimal fraction is created by concatenating the positive
 * integers:
 * 
 * 0.123456789101112131415161718192021...
 * 
 * It can be seen that the 12th digit of the fractional part is 1.
 * 
 * If d_n represents the nth digit of the fractional part, find the value of the
 * following expression.
 * 
 * d_1 × d_10 × d_100 × d_1000 × d_10000 × d_100000 × d_1000000
 */
public class PE040_Champernownes_constant {
	int[] digitsPerDecade = new int[6];

	public static void main(String[] args) {
		long start = System.nanoTime();

		PE040_Champernownes_constant f = new PE040_Champernownes_constant();
		int result = f.getAnswer();

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	public PE040_Champernownes_constant() {
	}

	public int getAnswer() {
		fillDigitsPerDecade();
		int product = digit(1) * digit(10) * digit(100) * digit(1000)
				* digit(10000) * digit(100000) * digit(1000000);
		return product;
	}

	private void fillDigitsPerDecade() {
		digitsPerDecade[0] = 9;
		digitsPerDecade[1] = 90 * 2;
		digitsPerDecade[2] = 900 * 3;
		digitsPerDecade[3] = 9000 * 4;
		digitsPerDecade[4] = 90000 * 5;
		digitsPerDecade[5] = 900000 * 6;
	}

	private int digit(int n) {
		int i = 0;
		int base = 1;
		int digitsPerNumber = 1;
		while (n > digitsPerDecade[i]) {
			n -= digitsPerDecade[i++];
			base *= 10;
			digitsPerNumber++;
		}
		int numberOrder = (n - 1) / digitsPerNumber + 1;
		int digitOrder = (n - 1) % digitsPerNumber;
		int number = base + numberOrder - 1;
		String rep = String.valueOf(number);
		return digitVal(rep.charAt(digitOrder));
	}

	private int digitVal(char ch) {
		return (int) ch - (int) '0';
	}
}
