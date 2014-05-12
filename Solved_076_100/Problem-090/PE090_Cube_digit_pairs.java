package Solved_076_100;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Cube digit pairs
 * Problem 90
 * 
 * Each of the six faces on a cube has a different digit (0 to 9) written on it;
 * the same is done to a second cube. By placing the two cubes side-by-side in
 * different positions we can form a variety of 2-digit numbers.
 * 
 * For example, the square number 64 could be formed:
 * 
 * In fact, by carefully choosing the digits on both cubes it is possible to
 * display all of the square numbers below one-hundred: 01, 04, 09, 16, 25, 36,
 * 49, 64, and 81.
 * 
 * For example, one way this can be achieved is by placing {0, 5, 6, 7, 8, 9} on
 * one cube and {1, 2, 3, 4, 8, 9} on the other cube.
 * 
 * However, for this problem we shall allow the 6 or 9 to be turned upside-down
 * so that an arrangement like {0, 5, 6, 7, 8, 9} and {1, 2, 3, 4, 6, 7} allows
 * for all nine square numbers to be displayed; otherwise it would be impossible
 * to obtain 09.
 * 
 * In determining a distinct arrangement we are interested in the digits on each
 * cube, not the order.
 * 
 * {1, 2, 3, 4, 5, 6} is equivalent to {3, 6, 4, 1, 2, 5}
 * {1, 2, 3, 4, 5, 6} is distinct from {1, 2, 3, 4, 5, 9}
 * 
 * But because we are allowing 6 and 9 to be reversed, the two distinct sets in
 * the last example both represent the extended set {1, 2, 3, 4, 5, 6, 9} for
 * the purpose of forming 2-digit numbers.
 * 
 * How many distinct arrangements of the two cubes allow for all of the square
 * numbers to be displayed?
 */
public class PE090_Cube_digit_pairs {
	private int[] a, b;

	public static void main(String[] args) {
		Set<PE090_Cube_digit_pairs> set = new HashSet<PE090_Cube_digit_pairs>();

		long start = System.nanoTime();

		for (int $1 = 0; $1 <= 9; $1++) {
			for (int $2 = $1 + 1; $2 <= 9; $2++) {
				for (int $3 = $2 + 1; $3 <= 9; $3++) {
					for (int $4 = $3 + 1; $4 <= 9; $4++) {
						for (int $5 = $4 + 1; $5 <= 9; $5++) {
							for (int $6 = $5 + 1; $6 <= 9; $6++) {
								int[] a = { $1, $2, $3, $4, $5, $6 };

								for (int $7 = 0; $7 <= 9; $7++) {
									for (int $8 = $7 + 1; $8 <= 9; $8++) {
										for (int $9 = $8 + 1; $9 <= 9; $9++) {
											for (int $10 = $9 + 1; $10 <= 9; $10++) {
												for (int $11 = $10 + 1; $11 <= 9; $11++) {
													for (int $12 = $11 + 1; $12 <= 9; $12++) {
														int[] b = { $7, $8, $9,
																$10, $11, $12 };

														if (!Arrays
																.equals(a, b)) {
															PE090_Cube_digit_pairs item = new PE090_Cube_digit_pairs(
																	a, b);
															if (item.isValid()) {
																set.add(item);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(set.size());
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	public PE090_Cube_digit_pairs(int[] a, int[] b) {
		this.a = a;
		this.b = b;
	}

	public boolean equals(Object other) {
		if ((other == null) || (!(other instanceof PE090_Cube_digit_pairs))) {
			return false;
		} else {
			PE090_Cube_digit_pairs that = (PE090_Cube_digit_pairs) other;

			return (((Arrays.equals(that.a, this.a)) && (Arrays.equals(that.b,
					this.b))) || ((Arrays.equals(that.a, this.b)) && (Arrays
							.equals(that.b, this.a))));
		}
	}

	public int hashCode() {
		return Math.max(Arrays.hashCode(this.a), Arrays.hashCode(this.b));
	}

	public boolean isValid() {
		return ((((contains(this.a, 0)) && (contains(this.b, 1))) || ((contains(
				this.b, 0)) && (contains(this.a, 1))))
				&& (((contains(this.a, 0)) && (contains(this.b, 4))) || ((contains(
						this.b, 0)) && (contains(this.a, 4))))
						&& (((contains(this.a, 0)) && (contains(this.b, 9)))
								|| ((contains(this.b, 0)) && (contains(this.a, 9)))
								|| ((contains(this.a, 0)) && (contains(this.b, 6))) || ((contains(
										this.b, 0)) && (contains(this.a, 6))))
										&& (((contains(this.a, 1)) && (contains(this.b, 9)))
												|| ((contains(this.b, 1)) && (contains(this.a, 9)))
												|| ((contains(this.a, 1)) && (contains(this.b, 6))) || ((contains(
														this.b, 1)) && (contains(this.a, 6))))
														&& (((contains(this.a, 3)) && (contains(this.b, 9)))
																|| ((contains(this.b, 3)) && (contains(this.a, 9)))
																|| ((contains(this.a, 3)) && (contains(this.b, 6))) || ((contains(
																		this.b, 3)) && (contains(this.a, 6))))
																		&& (((contains(this.a, 4)) && (contains(this.b, 9)))
																				|| ((contains(this.b, 4)) && (contains(this.a, 9)))
																				|| ((contains(this.a, 4)) && (contains(this.b, 6))) || ((contains(
																						this.b, 4)) && (contains(this.a, 6))))
																						&& (((contains(this.a, 2)) && (contains(this.b, 5))) || ((contains(
																								this.b, 2)) && (contains(this.a, 5)))) && (((contains(
																										this.a, 8)) && (contains(this.b, 1))) || ((contains(this.b, 8)) && (contains(
																												this.a, 1)))));
	}

	private boolean contains(int[] array, int value) {
		for (int number : array) {
			if (number == value) {
				return true;
			}
		}

		return false;
	}
}
