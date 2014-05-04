package Solved_076_100;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Passcode derivation
 * Problem 79
 * 
 * A common security method used for online banking is to ask the user for three
 * random characters from a passcode. For example, if the passcode was 531278,
 * they may ask for the 2nd, 3rd, and 5th characters; the expected reply would
 * be: 317.
 * 
 * The text file, keylog.txt, contains fifty successful login attempts.
 * 
 * Given that the three characters are always asked for in order, analyse the
 * file so as to determine the shortest possible secret passcode of unknown
 * length.
 */
public class PE079_Passcode_derivation {
	public static void main(String[] args) throws IOException {
		long start = System.nanoTime();
		
		String result = "........";
		boolean[][] grid = new boolean[10][8];
		int[] results = new int[10];
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 8; j++) {
				grid[i][j] = true;
			}
		}
		
		for (int i = 0; i < 8; i++) {
			grid[4][i] = false;
			grid[5][i] = false;
		}
		
		BufferedReader input = new BufferedReader(new FileReader(
				"PE079_keylog.txt"));
		String line;
		
		while ((line = input.readLine()) != null) {
			int a = line.charAt(0) - '0';
			int b = line.charAt(1) - '0';
			int c = line.charAt(2) - '0';
			int i = 0;
			
			while (i < 8 && grid[a][i] == false) {
				i++;
			}
			
			if (i > 7) {
				i = results[a];
			}
			
			for (int j = 0; j <= i && j < 8; j++) {
				grid[b][j] = false;
			}
			
			for (int j = 0; j <= i + 1 && j < 8; j++) {
				grid[c][j] = false;
			}
			
			i = 7;
			
			while (i > -1 && grid[c][i] == false) {
				i--;
			}
			
			if (i < 0) {
				i = results[c];
			}
			
			for (int j = 7; j >= i && j > -1; j--) {
				grid[b][j] = false;
			}
			
			for (int j = 7; j >= i - 1 && j > -1; j--) {
				grid[a][j] = false;
			}
			
			boolean dirty;
			
			do {
				dirty = false;
				
				for (int j = 0; j < 10; j++) {
					int count = 0;
					int ind = 0;
					
					for (int k = 0; k < 8; k++) {
						if (grid[j][k] == true) {
							ind = k;
							count++;
						}
					}
					
					if (count == 1) {
						dirty = true;
						char d = (char) (j + '0');
						result = result.substring(0, ind) + d + result.substring(ind + 1);
						results[j] = d;
						
						for (int k = 0; k < 8; k++) {
							grid[j][k] = false;
						}
						
						for (int k = 0; k < 10; k++) {
							grid[k][ind] = false;
						}
					}
				}
			} while (dirty);
		}
		
		input.close();
		
		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
