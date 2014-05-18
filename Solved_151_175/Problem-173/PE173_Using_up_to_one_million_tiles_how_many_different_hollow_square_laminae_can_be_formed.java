package Solved_151_175;

/**
 * Using up to one million tiles how many different "hollow" square laminae can
 * be formed?
 * Problem 173
 * 
 * We shall define a square lamina to be a square outline with a square "hole"
 * so that the shape possesses vertical and horizontal symmetry. For example,
 * using exactly thirty-two square tiles we can form two different square
 * laminae:
 * 
 * With one-hundred tiles, and not necessarily using all of the tiles at one
 * time, it is possible to form forty-one different square laminae.
 * 
 * Using up to one million tiles how many different square laminae can be
 * formed?
 */
public class PE173_Using_up_to_one_million_tiles_how_many_different_hollow_square_laminae_can_be_formed {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int i = 1, result = 0;

		while (i * i - (i - 2) * (i - 2) <= 1000000) {
			int j = i - 2;

			while (i * i - j * j <= 1000000 && j > 0) {
				result++;
				j -= 2;
			}

			i++;
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
