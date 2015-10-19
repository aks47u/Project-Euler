package Solved_176_200;

/**
 * Prize Strings
 * Problem 191
 * 
 * A particular school offers cash rewards to children with good attendance and
 * punctuality. If they are absent for three consecutive days or late on more
 * than one occasion then they forfeit their prize.
 * 
 * During an n-day period a trinary string is formed for each child consisting
 * of L's (late), O's (on time), and A's (absent).
 * 
 * Although there are eighty-one trinary strings for a 4-day period that can be
 * formed, exactly forty-three strings would lead to a prize:
 * 
 * OOOO OOOA OOOL OOAO OOAA OOAL OOLO OOLA OAOO OAOA OAOL OAAO OAAL OALO OALA
 * OLOO OLOA OLAO OLAA AOOO AOOA AOOL AOAO AOAA AOAL AOLO AOLA AAOO AAOA AAOL
 * AALO AALA ALOO ALOA ALAO ALAA LOOO LOOA LOAO LOAA LAOO LAOA LAAO
 * 
 * How many "prize" strings exist over a 30-day period?
 */
public class PE191_Prize_Strings {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int days = 30;
		long[][][][][] array = new long[days][3][3][3][2];
		array[0][0][0][0][0] = 1;
		array[0][1][0][0][0] = 1;
		array[0][2][0][0][1] = 1;
		long result = 0;

		for (int i = 1; i < days; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					for (int l = 0; l < 3; l++) {
						if (j != 2) {
							array[i][j][k][l][0] = array[i - 1][k][l][0][0]
									+ array[i - 1][k][l][1][0];
							array[i][j][k][l][1] = array[i - 1][k][l][0][1]
									+ array[i - 1][k][l][1][1]
											+ array[i - 1][k][l][2][1];
						} else {
							array[i][j][k][l][1] = array[i - 1][k][l][0][0]
									+ array[i - 1][k][l][1][0];
						}

						if (j == 1 && k == 1 && l == 1) {
							array[i][j][k][l][0] = 0;
							array[i][j][k][l][1] = 0;
						}

						if (i == days - 1) {
							result += (array[i][j][k][l][0] + array[i][j][k][l][1]);
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
