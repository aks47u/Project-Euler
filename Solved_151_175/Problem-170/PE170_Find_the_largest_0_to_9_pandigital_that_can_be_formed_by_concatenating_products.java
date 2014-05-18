package Solved_151_175;

/**
 * Find the largest 0 to 9 pandigital that can be formed by concatenating
 * products
 * Problem 170
 * 
 * Take the number 6 and multiply it by each of 1273 and 9854:
 * 
 * 6 × 1273 = 7638
 * 6 × 9854 = 59124
 * 
 * By concatenating these products we get the 1 to 9 pandigital 763859124. We
 * will call 763859124 the "concatenated product of 6 and (1273,9854)". Notice
 * too, that the concatenation of the input numbers, 612739854, is also 1 to 9
 * pandigital.
 * 
 * The same can be done for 0 to 9 pandigital numbers.
 * 
 * What is the largest 0 to 9 pandigital 10-digit concatenated product of an
 * integer with two or more other integers, such that the concatenation of the
 * input numbers is also a 0 to 9 pandigital 10-digit number?
 */
public class PE170_Find_the_largest_0_to_9_pandigital_that_can_be_formed_by_concatenating_products {
	public static void main(String[] args) {
		long start = System.nanoTime();

		for (long i0 = 9; i0 > 0; i0--) {
			for (long i1 = 9; i1 >= 0; i1--) {
				if (i1 != i0) {
					for (int i2 = 9; i2 >= 0; i2--) {
						if (i2 != i1 && i2 != i0) {
							for (long i3 = 9; i3 >= 0; i3--) {
								if (i3 != i2 && i3 != i1 && i3 != i0) {
									for (long i4 = 9; i4 >= 0; i4--) {
										if (i4 != i3 && i4 != i2 && i4 != i1
												&& i4 != i0) {
											for (long i5 = 9; i5 >= 0; i5--) {
												if (i5 != i4 && i5 != i3
														&& i5 != i2 && i5 != i1
														&& i5 != i0) {
													for (long i6 = 9; i6 >= 0; i6--) {
														if (i6 != i5
																&& i6 != i4
																&& i6 != i3
																&& i6 != i2
																&& i6 != i1
																&& i6 != i0) {
															for (long i7 = 9; i7 >= 0; i7--) {
																if (i7 != i6
																		&& i7 != i5
																		&& i7 != i4
																		&& i7 != i3
																		&& i7 != i2
																		&& i7 != i1
																		&& i7 != i0) {
																	for (long i8 = 9; i8 >= 0; i8--) {
																		if (i8 != i7
																				&& i8 != i6
																				&& i8 != i5
																				&& i8 != i4
																				&& i8 != i3
																				&& i8 != i2
																				&& i8 != i1
																				&& i8 != i0) {
																			for (long i9 = 9; i9 >= 0; i9--) {
																				if (i9 != i8
																						&& i9 != i7
																						&& i9 != i6
																						&& i9 != i5
																						&& i9 != i4
																						&& i9 != i3
																						&& i9 != i2
																						&& i9 != i1
																						&& i9 != i0) {
																					long result = i0
																							* 1000000000
																							+ i1
																							* 100000000
																							+ i2
																							* 10000000
																							+ i3
																							* 1000000
																							+ i4
																							* 100000
																							+ i5
																							* 10000
																							+ i6
																							* 1000
																							+ i7
																							* 100
																							+ i8
																							* 10
																							+ i9;

																					if (solve(result)) {
																						long end = System
																								.nanoTime();
																						long runtime = end
																								- start;
																						System.out
																						.println(result);
																						System.out
																						.println("Runtime: "
																								+ runtime
																								/ 1000000
																								+ "ms ("
																								+ runtime
																								+ "ns)");

																						return;
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
							}
						}
					}
				}
			}
		}
	}

	private static boolean solve(long val) {
		for (int i = 2; i < 100; i++) {
			if (val % i == 0) {
				String s = "" + (val / i);
				String arr[] = s.split("0");
				String check = "";

				for (int k = 0; k < arr.length - 1; k++) {
					check = "";
					int count = 0;

					for (int j = 0; j < arr.length; j++) {
						if (!arr[j].equals("")) {
							long l;

							if (k == j) {
								l = Long.parseLong(arr[j] + "0" + arr[j + 1]);
								j++;
							} else {
								l = Long.parseLong(arr[j]);
							}

							if (!(val + "").contains("" + l * i)) {
								break;
							}

							check += "" + l;
							count++;
						}

						if (pandigital(check + "" + i) && count >= 2) {
							return true;
						}
					}
				}

				for (int k = 0; k < arr.length; k++) {
					if (k != arr.length - 1 || s.endsWith("0")) {
						check = "";
						int count = 0;

						for (int j = 0; j < arr.length; j++) {
							if (!arr[j].equals("")) {
								long l;

								if (k == j) {
									l = Long.parseLong(arr[j] + "0");
								} else {
									l = Long.parseLong(arr[j]);
								}

								if (!(val + "").contains("" + l * i)) {
									break;
								}

								check += "" + l;
								count++;
							}

							if (pandigital(check + "" + i) && count >= 2) {
								return true;
							}
						}
					}
				}
			}
		}

		return false;
	}

	private static boolean pandigital(String s) {
		if (s.length() != 10) {
			return false;
		}

		if (s.contains("1") && s.contains("2") && s.contains("3")
				&& s.contains("4") && s.contains("5") && s.contains("6")
				&& s.contains("7") && s.contains("8") && s.contains("9")
				&& s.contains("0")) {
			return true;
		}

		return false;
	}
}
