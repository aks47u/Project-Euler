package Solved_001_025;

/**
 * Special Pythagorean triplet
 * Problem 9
 * 
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for
 * which, a2 + b2 = c2 For example, 32 + 42 = 9 + 16 = 25 = 52. There exists
 * exactly one Pythagorean triplet for which a + b + c = 1000. Find the product
 * abc.
 */
public class PE009_Special_Pythagorean_triplet {
	public static void main(String[] args) {
		long start = System.nanoTime();

		double c = 0.0;
		int result = 0;
		
		for (int a = 0; a < 1000; a++) {
			for (int b = 0; b < 1000; b++) {
				c = Math.sqrt((a * a) + (b * b));
				
				if (a + b + c == 1000 && a * b * c != 0) {
					result = (int) (a * b * c);
					break;
				}
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
