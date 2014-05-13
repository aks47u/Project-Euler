package Solved_126_150;

/**
 * Cuboid layers
 * Problem 126
 * 
 * The minimum number of cubes to cover every visible face on a cuboid measuring
 * 3 x 2 x 1 is twenty-two.
 * 
 * If we then add a second layer to this solid it would require forty-six cubes
 * to cover every visible face, the third layer would require seventy-eight
 * cubes, and the fourth layer would require one-hundred and eighteen cubes to
 * cover every visible face.
 * 
 * However, the first layer on a cuboid measuring 5 x 1 x 1 also requires
 * twenty-two cubes; similarly the first layer on cuboids measuring 5 x 3 x 1, 7
 * x 2 x 1, and 11 x 1 x 1 all contain forty-six cubes.
 * 
 * We shall define C(n) to represent the number of cuboids that contain n cubes
 * in one of its layers. So C(22) = 2, C(46) = 4, C(78) = 5, and C(118) = 8.
 * 
 * It turns out that 154 is the least value of n for which C(n) = 10.
 * 
 * Find the least value of n for which C(n) = 1000.
 */
public class PE126_Cuboid_layers {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int[] c = new int[100000];

		for (int i = 1; i < 100; i++) {
			for (int j = i; j < 100; j++) {
				for (int k = j; k < 10000; k++) {
					int a = 2 * (i * j + i * k + j * k);

					if (a < 100000) {
						c[a]++;
					}

					for (int kk = 0; kk < 100; kk++) {
						a += ((i + j + k + (kk << 1)) << 2);

						if (a >= 100000) {
							break;
						}

						c[a]++;
					}
				}
			}
		}

		int result = 0;
		
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 1000) {
				result = i;
				break;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
