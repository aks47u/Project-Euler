package Solved_076_100;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Roman numerals
 * Problem 89
 * 
 * The rules for writing Roman numerals allow for many ways of writing each
 * number (see About Roman Numerals...). However, there is always a "best" way
 * of writing a particular number.
 * 
 * For example, the following represent all of the legitimate ways of writing
 * the number sixteen:
 * 
 * IIIIIIIIIIIIIIII
 * VIIIIIIIIIII
 * VVIIIIII
 * XIIIIII
 * VVVI
 * XVI
 * 
 * The last example being considered the most efficient, as it uses the least
 * number of numerals.
 * 
 * The 11K text file, roman.txt (right click and 'Save Link/Target As...'),
 * contains one thousand numbers written in valid, but not necessarily minimal,
 * Roman numerals; that is, they are arranged in descending units and obey the
 * subtractive pair rule (see About Roman Numerals... for the definitive rules
 * for this problem).
 * 
 * Find the number of characters saved by writing each of these in their minimal
 * form.
 * 
 * Note: You can assume that all the Roman numerals in the file contain no more
 * than four consecutive identical units.
 */
public class PE089_Roman_numerals {
	public static void main(String[] args) {
		long start = System.nanoTime();
		
		String line = "", opt = "";
		int result = 0;
		String[] symbols = { "VIIII", "IIII", "LXXXX", "XXXX", "DCCCC", "CCCC" };
		String[] optSymb = { "IX", "IV", "XC", "XL", "CM", "CD" };

		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"PE089_roman.txt"));
			while (br.ready()) {
				line = br.readLine();
				opt = "";

				Out: for (int i = 0; i < line.length(); i++) {
					for (int j = 0; j < symbols.length; j++) {
						if (i + (symbols[j].length()) <= line.length()) {
							if (line.substring(i, i + (symbols[j].length()))
									.equals(symbols[j])) {
								opt += optSymb[j];
								i += (symbols[j].length() - 1);
								continue Out;
							}
						}
					}

					opt += line.substring(i, i + 1);
				}

				result += (line.length() - opt.length());
			}

			br.close();
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
