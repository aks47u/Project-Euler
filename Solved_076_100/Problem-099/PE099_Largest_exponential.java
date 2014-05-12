package Solved_076_100;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Largest exponential
 * Problem 99
 * 
 * Comparing two numbers written in index form like 2^11 and 3^7 is not
 * difficult, as any calculator would confirm that 2^11 = 2048 < 3^7 = 2187.
 * 
 * However, confirming that 632382^518061 > 519432^525806 would be much more
 * difficult, as both numbers contain over three million digits.
 * 
 * Using base_exp.txt (right click and 'Save Link/Target As...'), a 22K text
 * file containing one thousand lines with a base/exponent pair on each line,
 * determine which line number has the greatest numerical value.
 * 
 * NOTE: The first two lines in the file represent the numbers in the example
 * given above.
 */
public class PE099_Largest_exponential {
	public static void main(String[] args) {
		long start = System.nanoTime();

		String line = null;
		double e, max = 0.0;
		int n = 0, result = 0;

		try {
			File InFile = new File("PE099_base_exp.txt");
			FileReader fileReader = new FileReader(InFile);
			BufferedReader reader = new BufferedReader(fileReader);

			while ((line = reader.readLine()) != null) {
				n++;
				e = fGetExp(line);

				if (e > max) {
					max = e;
					result = n;
				}
			}
			reader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static double fGetExp(String line) {
		String[] tokens = line.split(",");
		int base = Integer.parseInt(tokens[0]);
		int exp = Integer.parseInt(tokens[1]);
		return exp * Math.log10(base);
	}
}
