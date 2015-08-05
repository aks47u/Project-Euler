package Solved_026_050;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Coded triangle numbers
 * Problem 42
 * 
 * The nth term of the sequence of triangle numbers is given by, t_n = ½n(n+1);
 * so the first ten triangle numbers are:
 * 
 * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
 * 
 * By converting each letter in a word to a number corresponding to its
 * alphabetical position and adding these values we form a word value. For
 * example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word value
 * is a triangle number then we shall call the word a triangle word.
 * 
 * Using words.txt (right click and 'Save Link/Target As...'), a 16K text file
 * containing nearly two-thousand common English words, how many are triangle
 * words?
 */
public class PE042_Coded_triangle_numbers {
	public static void main(String[] args) throws FileNotFoundException {
		long start = System.nanoTime();

		File file = new File("PE042_words.txt");
		Scanner scn = new Scanner(file);
		String[] words = scn.nextLine().replaceAll("\"", "").split(",");
		scn.close();
		int wordValue, result = 0;

		for (int loop = 0; loop < words.length; loop++) {
			wordValue = getWordValue(words[loop]);
			
			if (isTriangleNumber(wordValue)) {
				result++;
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static int getWordValue(String word) {
		int total = 0;

		for (int loop = 0; loop < word.length(); loop++) {
			total += word.charAt(loop) - 64;
		}

		return total;
	}

	private static boolean isTriangleNumber(int number) {
		int sqrt = (int) Math.sqrt(number * 2);
		
		return sqrt * (sqrt + 1) == number * 2;
	}
}
