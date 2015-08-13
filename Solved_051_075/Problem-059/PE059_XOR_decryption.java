package Solved_051_075;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * XOR decryption
 * Problem 59
 * 
 * Each character on a computer is assigned a unique code and the preferred
 * standard is ASCII (American Standard Code for Information Interchange). For
 * example, uppercase A = 65, asterisk (*) = 42, and lowercase k = 107.
 * 
 * A modern encryption method is to take a text file, convert the bytes to
 * ASCII, then XOR each byte with a given value, taken from a secret key. The
 * advantage with the XOR function is that using the same encryption key on the
 * cipher text, restores the plain text; for example, 65 XOR 42 = 107, then 107
 * XOR 42 = 65.
 * 
 * For unbreakable encryption, the key is the same length as the plain text
 * message, and the key is made up of random bytes. The user would keep the
 * encrypted message and the encryption key in different locations, and without
 * both "halves", it is impossible to decrypt the message.
 * 
 * Unfortunately, this method is impractical for most users, so the modified
 * method is to use a password as a key. If the password is shorter than the
 * message, which is likely, the key is repeated cyclically throughout the
 * message. The balance for this method is using a sufficiently long password
 * key for security, but short enough to be memorable.
 * 
 * Your task has been made easy, as the encryption key consists of three lower
 * case characters. Using cipher1.txt (right click and 'Save Link/Target
 * As...'), a file containing the encrypted ASCII codes, and the knowledge that
 * the plain text must contain common English words, decrypt the message and
 * find the sum of the ASCII values in the original text.
 */
public class PE059_XOR_decryption {
	private int[] cipher;
	private int[] key = new int[3];
	private int[] plain;
	private String alphabet = " ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static long start;

	public static void main(String[] args) {
		start = System.nanoTime();

		@SuppressWarnings("unused")
		PE059_XOR_decryption c = new PE059_XOR_decryption();

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	public PE059_XOR_decryption() {
		String[] items = new String[0];

		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"PE059_cipher1.txt"));
			items = reader.readLine().split(",");
			reader.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		cipher = new int[items.length];
		plain = new int[items.length];

		for (int i = 0; i < items.length; i++) {
			cipher[i] = readInt(items[i]);
		}

		for (key[0] = 'a'; key[0] <= 'z'; key[0]++) {
			for (key[1] = 'a'; key[1] <= 'z'; key[1]++) {
				for (key[2] = 'a'; key[2] <= 'z'; key[2]++) {
					decrypt(cipher, key, plain);

					if (isSufficientlyAlphabetical(plain)) {
						writeResult(key, plain);
						System.exit(0);
					}
				}
			}
		}
	}

	private int readInt(String s) {
		int result = 0;

		for (int i = 0; i < s.length(); i++) {
			result = 10 * result + (int) s.charAt(i) - (int) '0';
		}

		return result;
	}

	private void decrypt(int[] cipher, int[] key, int[] plain) {
		for (int i = 0; i < cipher.length; i++) {
			int j = i % key.length;
			plain[i] = xor(cipher[i], key[j]);
		}
	}

	private int xor(int a, int b) {
		int bit = 1;
		int result = 0;

		while (a > 0 || b > 0) {
			if (a % 2 == 0 ^ b % 2 == 0) {
				result += bit;
			}

			a /= 2;
			b /= 2;
			bit *= 2;
		}

		return result;
	}

	private void writeResult(int[] key, int[] plain) {
		System.out.println(sum(plain));

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private boolean isSufficientlyAlphabetical(int[] plain) {
		int count = 0;

		for (int i = 0; i < plain.length; i++) {
			if (alphabet.indexOf((char) plain[i]) >= 0) {
				count++;
			}
		}

		return count * 10 > plain.length * 9;
	}

	private int sum(int[] a) {
		int s = 0;

		for (int i = 0; i < a.length; i++) {
			s += a[i];
		}

		return s;
	}
}
