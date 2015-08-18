package Solved_051_075;

import java.util.Arrays;

/**
 * Magic 5-gon ring
 * Problem 68
 * 
 * Consider the following "magic" 3-gon ring, filled with the numbers 1 to 6,
 * and each line adding to nine.
 * 
 * Working clockwise, and starting from the group of three with the numerically
 * lowest external node (4,3,2 in this example), each solution can be described
 * uniquely. For example, the above solution can be described by the set: 4,3,2;
 * 6,2,1; 5,1,3.
 * 
 * It is possible to complete the ring with four different totals: 9, 10, 11,
 * and 12. There are eight solutions in total.
 * Total	Solution Set
 * 	9		4,2,3; 5,3,1; 6,1,2
 * 	9		4,3,2; 6,2,1; 5,1,3
 * 	10		2,3,5; 4,5,1; 6,1,3
 * 	10		2,5,3; 6,3,1; 4,1,5
 * 	11		1,4,6; 3,6,2; 5,2,4
 * 	11		1,6,4; 5,4,2; 3,2,6
 * 	12		1,5,6; 2,6,4; 3,4,5
 * 	12		1,6,5; 3,5,4; 2,4,6
 * 
 * By concatenating each group it is possible to form 9-digit strings; the
 * maximum string for a 3-gon ring is 432621513.
 * 
 * Using the numbers 1 to 10, and depending on arrangements, it is possible to
 * form 16- and 17-digit strings. What is the maximum 16-digit string for a
 * "magic" 5-gon ring?
 */
public class PE068_Magic_5_gon_ring {
	public static void main(String[] args) {
		long start = System.nanoTime();

		long result = 0l;
		int[] a = new int[10];
		boolean[] p = new boolean[11];

		for (a[0] = 1; a[0] < 6; a[0]++) {
			for (a[1] = a[0] + 1; a[1] < 10; a[1]++) {
				for (a[2] = a[0] + 1; a[2] < 10; a[2]++) {
					if (a[2] == a[1]) {
						continue;
					}

					for (a[3] = a[0] + 1; a[3] < 10; a[3]++) {
						if (a[3] == a[1] || a[3] == a[2]) {
							continue;
						}

						for (a[4] = a[0] + 1; a[4] < 10; a[4]++) {
							if (a[4] == a[1] || a[4] == a[2] || a[4] == a[3]) {
								continue;
							}

							int sum = a[0] + a[1] + a[2] + a[3] + a[4];

							if (sum % 5 != 0) {
								continue;
							}

							int total = sum / 5 + 11;
							Arrays.fill(p, false);
							p[a[0]] = p[a[1]] = p[a[2]] = p[a[3]] = p[a[4]] = true;
							a[5] = total - a[0] - a[1];

							if (a[5] > 10) {
								continue;
							}

							if (p[a[5]]) {
								continue;
							}

							p[a[5]] = true;
							a[6] = total - a[1] - a[2];

							if (a[6] < 1) {
								continue;
							}

							if (p[a[6]]) {
								continue;
							}

							p[a[6]] = true;
							a[7] = total - a[2] - a[3];

							if (a[7] < 1) {
								continue;
							}

							if (p[a[7]]) {
								continue;
							}

							p[a[7]] = true;
							a[8] = total - a[3] - a[4];

							if (a[8] < 1) {
								continue;
							}

							if (p[a[8]]) {
								continue;
							}

							p[a[8]] = true;
							a[9] = total - a[4] - a[0];

							if (a[9] > 10) {
								continue;
							}

							if (p[a[9]]) {
								continue;
							}

							int min = 5;

							for (int i = 6; i < 10; i++) {
								if (a[i] < a[min]) {
									min = i;
								}
							}

							String s = null;

							switch (min) {
							case 5:
								s = "" + a[5] + a[0] + a[1] + a[6] + a[1]
										+ a[2] + a[7] + a[2] + a[3] + a[8]
												+ a[3] + a[4] + a[9] + a[4] + a[0];
								break;
							case 6:
								s = "" + a[6] + a[1] + a[2] + a[7] + a[2]
										+ a[3] + a[8] + a[3] + a[4] + a[9]
												+ a[4] + a[0] + a[5] + a[0] + a[1];
								break;
							case 7:
								s = "" + a[7] + a[2] + a[3] + a[8] + a[3]
										+ a[4] + a[9] + a[4] + a[0] + a[5]
												+ a[0] + a[1] + a[6] + a[1] + a[2];
								break;
							case 8:
								s = "" + a[8] + a[3] + a[4] + a[9] + a[4]
										+ a[0] + a[5] + a[0] + a[1] + a[6]
												+ a[1] + a[2] + a[7] + a[2] + a[3];
								break;
							case 9:
								s = "" + a[9] + a[4] + a[0] + a[5] + a[0]
										+ a[1] + a[6] + a[1] + a[2] + a[7]
												+ a[2] + a[3] + a[8] + a[3] + a[4];
								break;
							}

							long num = Long.parseLong(s);

							if (num > result) {
								result = num;
							}
						}
					}
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
