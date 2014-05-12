package Solved_076_100;

/**
 * Square digit chains
 * Problem 92
 * 
 * A number chain is created by continuously adding the square of the digits in
 * a number to form a new number until it has been seen before.
 * 
 * For example,
 * 
 * 44 -> 32 -> 13 -> 10 -> 1 -> 1
 * 85 -> 89 -> 145 -> 42 -> 20 -> 4 -> 16 -> 37 -> 58 -> 89
 * 
 * Therefore any chain that arrives at 1 or 89 will become stuck in an endless
 * loop. What is most amazing is that EVERY starting number will eventually
 * arrive at 1 or 89.
 * 
 * How many starting numbers below ten million will arrive at 89?
 */
public class PE092_Square_digit_chains {
	private static int[] table = new int[10000000];

	public static void main(String Arg[]) {
		long start = System.nanoTime();

		int result = 0;
		for (int i = 1; i < 10000000; i++) {
			int invalue = arrivesAt(i);

			while (invalue != 1 && invalue != 89) {
				invalue = arrivesAt(invalue);
			}

			if (invalue == 89) {
				table[i] = 89;
				result++;
			} else {
				table[i] = 1;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static int arrivesAt(int nr) {
		if (table[nr] == 89) {
			return 89;
		}

		if (table[nr] == 1) {
			return 1;
		}

		String string = new Integer(nr).toString();
		int a = 0;

		for (int i = 0; i < string.length(); i++) {
			int b = new Integer(string.substring(i, i + 1)).intValue();
			a += b * b;
		}

		return a;
	}
}
