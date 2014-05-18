package Solved_151_175;

/**
 * Counting the number of "hollow" square laminae that can form one, two, three,
 * ... distinct arrangements
 * Problem 174
 * 
 * We shall define a square lamina to be a square outline with a square "hole"
 * so that the shape possesses vertical and horizontal symmetry.
 * 
 * Given eight tiles it is possible to form a lamina in only one way: 3x3 square
 * with a 1x1 hole in the middle. However, using thirty-two tiles it is possible
 * to form two distinct laminae.
 * 
 * If t represents the number of tiles used, we shall say that t = 8 is type
 * L(1) and t = 32 is type L(2).
 * 
 * Let N(n) be the number of t <= 1000000 such that t is type L(n); for example,
 * N(15) = 832.
 * 
 * What is SUM N(n) for 1 <= n <= 10?
 */
public class PE174_Counting_the_number_of_hollow_square_laminae_that_can_form_one_two_three__distinct_arrangements {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int[] laminae = new int[1000001];
		int i = 1, result = 0;

		while (i * i - (i - 2) * (i - 2) <= 1000000) {
			int j = i - 2;

			while (i * i - j * j <= 1000000 && j > 0) {
				laminae[i * i - j * j]++;
				j -= 2;
			}

			i++;
		}

		for (int k = 1; k <= 1000000; k++) {
			if (laminae[k] >= 1 && laminae[k] <= 10) {
				result++;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
