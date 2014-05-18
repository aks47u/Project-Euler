package Solved_176_200;

/**
 * Investigating in how many ways objects of two different colours can be
 * grouped
 * Problem 181
 * 
 * Having three black objects B and one white object W they can be grouped in 7
 * ways like this:
 * (BBBW) (B,BBW) (B,B,BW) (B,B,B,W) (B,BB,W) (BBB,W) (BB,BW)
 * 
 * In how many ways can sixty black objects B and forty white objects W be thus
 * grouped?
 */
public class PE181_Investigating_in_how_many_ways_objects_of_two_different_colours_can_be_grouped {
	private static int maxb = 60;
	private static int maxw = 40;
	private static long[][][] onesize = new long[maxb + 1][maxw + 1][maxb
	                                                                 + maxw + 1];
	private static long[][][] anysize = new long[maxb + 1][maxw + 1][maxb
	                                                                 + maxw + 1];

	public static void main(String[] args) {
		long start = System.nanoTime();

		for (int s = 1; s <= maxw + maxb; s++) {
			onesize[0][0][s]++;

			for (int db = 0; db <= s; db++) {
				for (int b = 0; b <= maxb - db; b++) {
					for (int w = 0; w <= maxw - (s - db); w++) {
						onesize[b + db][w + s - db][s] += onesize[b][w][s];
					}
				}
			}
		}

		anysize[0][0][0] = 1;

		for (int s = 0; s <= maxw + maxb; s++) {
			for (int b = 0; b <= maxb; b++) {
				for (int w = 0; w <= maxw; w++) {
					if (b + w >= s) {
						for (int ds = s + 1; ds <= maxb + maxw; ds++) {
							for (int sumbw = ds; b + w + sumbw <= maxb + maxw; sumbw += ds) {
								for (int db = Math.max(0, w + sumbw - maxw); db <= sumbw
										&& b + db <= maxb; db++) {
									int dw = sumbw - db;
									anysize[b + db][w + dw][ds] += anysize[b][w][s]
											* onesize[db][dw][ds];
								}
							}
						}
					}
				}
			}
		}

		long result = 0;

		for (int s = 0; s <= maxb + maxw; s++) {
			result += anysize[maxb][maxw][s];
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
