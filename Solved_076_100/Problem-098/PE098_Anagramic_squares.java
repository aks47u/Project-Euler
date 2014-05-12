package Solved_076_100;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Anagramic squares
 * Problem 98
 * 
 * By replacing each of the letters in the word CARE with 1, 2, 9, and 6
 * respectively, we form a square number: 1296 = 362. What is remarkable is
 * that, by using the same digital substitutions, the anagram, RACE, also forms
 * a square number: 9216 = 962. We shall call CARE (and RACE) a square anagram
 * word pair and specify further that leading zeroes are not permitted, neither
 * may a different letter have the same digital value as another letter.
 * 
 * Using words.txt (right click and 'Save Link/Target As...'), a 16K text file
 * containing nearly two-thousand common English words, find all the square
 * anagram word pairs (a palindromic word is NOT considered to be an anagram of
 * itself).
 * 
 * What is the largest square number formed by any member of such a pair?
 * 
 * NOTE: All anagrams formed must be contained in the given text file.
 */
public class PE098_Anagramic_squares {
	public static void main(String[] args) throws Exception {
		long start = System.nanoTime();

		BufferedReader rdr = new BufferedReader(new FileReader(
				"PE098_words.txt"));
		String s = rdr.readLine();
		String[] s2 = s.split(",");
		rdr.close();
		ArrayList<String> wds = new ArrayList<String>();
		ArrayList<char[]> wdsarr = new ArrayList<char[]>();
		ArrayList<String> ana1 = new ArrayList<String>();
		ArrayList<String> ana2 = new ArrayList<String>();

		for (String s3 : s2) {
			String wd = s3.substring(1, s3.length() - 1);
			char[] ca = wd.toCharArray();
			Arrays.sort(ca);
			wds.add(wd);
			wdsarr.add(ca);
		}

		for (int i = 0; i < wds.size(); i++) {
			char[] ca1 = wdsarr.get(i);
			for (int j = i + 1; j < wds.size(); j++) {
				char[] ca2 = wdsarr.get(j);

				if (Arrays.equals(ca1, ca2)) {
					ana1.add(wds.get(i));
					ana2.add(wds.get(j));
				}
			}
		}

		int result = 0;

		for (int i = 0; i < ana1.size(); i++) {
			String pat1 = pattern(ana1.get(i));
			String sq = "";
			int cntr = 0;

			while (sq.length() <= pat1.length()) {
				cntr++;
				sq = "" + (cntr * cntr);

				if (sq.length() < pat1.length()) {
					continue;
				}

				String pat2 = pattern(sq);

				if (pat1.equals(pat2)) {
					String otherNum = translate(ana1.get(i), sq, ana2.get(i));

					if (isSquare(Integer.parseInt(otherNum))
							&& !otherNum.startsWith("0")
							&& Integer.parseInt(otherNum) > result) {
						result = Integer.parseInt(otherNum);
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

	private static boolean isSquare(long n) {
		int srt = (int) Math.sqrt(n);

		return srt * srt == n;
	}

	private static String pattern(String s) {
		String ret = "";
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		char cur = '0';

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			Character code = map.get(c);

			if (code == null) {
				code = cur++;
				map.put(c, code);
			}

			ret += code;
		}

		return ret;
	}

	private static String translate(String a, String b, String c) {
		// Assumes lengths, patterns are all equal a=>b, c=>?
		HashMap<Character, Character> map = new HashMap<Character, Character>();

		for (int i = 0; i < a.length(); i++) {
			char c1 = a.charAt(i);
			char c2 = b.charAt(i);
			map.put(c1, c2);
		}

		String ret = "";

		for (int i = 0; i < c.length(); i++) {
			char c3 = c.charAt(i);
			ret += map.get(c3);
		}

		return ret;
	}
}
