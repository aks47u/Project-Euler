package Solved_051_075;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Poker hands
 * Problem 54
 * 
 * In the card game poker, a hand consists of five cards and are ranked, from
 * lowest to highest, in the following way:
 * 
 * High Card: Highest value card.
 * One Pair: Two cards of the same value.
 * Two Pairs: Two different pairs.
 * Three of a Kind: Three cards of the same value.
 * Straight: All cards are consecutive values.
 * Flush: All cards of the same suit.
 * Full House: Three of a kind and a pair.
 * Four of a Kind: Four cards of the same value.
 * Straight Flush: All cards are consecutive values of same suit.
 * Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
 * 
 * The cards are valued in the order:
 * 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.
 * 
 * If two players have the same ranked hands then the rank made up of the
 * highest value wins; for example, a pair of eights beats a pair of fives (see
 * example 1 below). But if two ranks tie, for example, both players have a pair
 * of queens, then highest cards in each hand are compared (see example 4
 * below); if the highest cards tie then the next highest cards are compared,
 * and so on.
 * 
 * Consider the following five hands dealt to two players:
 * Hand		Player 1			Player 2				Winner
 * 1		5H 5C 6S 7S KD		2C 3S 8S 8D TD			Player 2
 * 			Pair of Fives		Pair of Eights
 * 2		5D 8C 9S JS AC		2C 5C 7D 8S QH			Player 1
 * 			Highest card Ace	Highest card Queen
 * 3		2D 9C AS AH AC		3D 6D 7D TD QD			Player 2
 * 			Three Aces			Flush with Diamonds
 * 4		4D 6S 9H QH QC		3D 6D 7H QD QS			Player 1
 * 			Pair of Queens		Pair of Queens
 * 			Highest card Nine	Highest card Seven
 * 5		2H 2D 4C 4D 4S		3C 3D 3S 9S 9D			Player 1
 * 			Full House			Full House
 * 			With Three Fours	with Three Threes
 * 
 * The file, poker.txt, contains one-thousand random hands dealt to two players.
 * Each line of the file contains ten cards (separated by a single space): the
 * first five are Player 1's cards and the last five are Player 2's cards. You
 * can assume that all hands are valid (no invalid characters or repeated
 * cards), each player's hand is in no specific order, and in each hand there is
 * a clear winner.
 * 
 * How many hands does Player 1 win?
 */
public class PE054_Poker_hands {
	private static final long ROYAL_FLUSH_MASK = cardCode("TH")
			| cardCode("JH") | cardCode("QH") | cardCode("KH") | cardCode("AH");
	private static final long FLUSH_MASK = cardCode("2H") | cardCode("3H")
			| cardCode("4H") | cardCode("5H") | cardCode("6H");
	private static final long FOUR_OF_A_KIND = cardCode("2H") | cardCode("2C")
			| cardCode("2S") | cardCode("2D");

	public static void main(String[] args) throws FileNotFoundException,
	IOException {
		long start = System.nanoTime();

		File input = new File("PE054_poker.txt");
		assert (input.canRead());
		int result = 0;
		BufferedReader in = new BufferedReader(new FileReader(input));
		String line;

		while (null != (line = in.readLine())) {
			long hands[] = parseHands(line);

			if (winner(hands) == 1) {
				result++;
			}
		}

		in.close();

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long[] parseHands(String line) {
		String[] cards = line.split(" ");
		long[] r = new long[2];

		for (int card = 0; card < 10; card++) {
			r[card / 5] |= cardCode(cards[card]);
		}

		return r;
	}

	private static int winner(long[] hands) {
		boolean b1 = royalFlush(hands[0]);
		boolean b2 = royalFlush(hands[1]);
		int v1, v2;

		if (b1 && !b2) {
			return 1;
		} else if (b2 && !b1) {
			return 2;
		} else if (b1 & b2) {
			return 0;
		}

		v1 = straightFlush(hands[0]);
		v2 = straightFlush(hands[1]);

		if (v1 + v2 != 0) {
			return whoWins(v1, v2, hands);
		}

		v1 = fourOfAKind(hands[0]);
		v2 = fourOfAKind(hands[1]);

		if (v1 + v2 != 0) {
			return whoWins(v1, v2, hands);
		}

		v1 = fullHouse(hands[0]);
		v2 = fullHouse(hands[1]);

		if (v1 + v2 != 0) {
			return whoWins(v1, v2, hands);
		}

		v1 = flush(hands[0]);
		v2 = flush(hands[1]);

		if (v1 + v2 != 0) {
			return whoWins(v1, v2, hands);
		}

		v1 = straight(hands[0]);
		v2 = straight(hands[1]);

		if (v1 + v2 != 0) {
			return whoWins(v1, v2, hands);
		}

		v1 = threeOfAKind(hands[0]);
		v2 = threeOfAKind(hands[1]);

		if (v1 + v2 != 0) {
			return whoWins(v1, v2, hands);
		}

		v1 = twoPairs(hands[0]);
		v2 = twoPairs(hands[1]);

		if (v1 + v2 != 0) {
			return whoWins(v1, v2, hands);
		}

		v1 = onePair(hands[0]);
		v2 = onePair(hands[1]);

		if (v1 + v2 != 0) {
			return whoWins(v1, v2, hands);
		}

		return playerWithHigherCards(hands);
	}

	private static long cardCode(String card) {
		String suitTexts = "HCSD";
		int suitCode = suitTexts.indexOf(card.charAt(1));
		final char C = card.charAt(0);
		int valueCode = "TJQKA".indexOf(C);
		int value = (valueCode == -1) ? (C - '0') - 2 : 8 + valueCode;

		return 1L << (4 * value + suitCode);
	}

	private static boolean royalFlush(long hand) {
		return hand == ROYAL_FLUSH_MASK || hand == (ROYAL_FLUSH_MASK << 1)
				|| hand == (ROYAL_FLUSH_MASK << 2)
				|| hand == (ROYAL_FLUSH_MASK << 3);
	}

	private static int straightFlush(long hand) {
		int value = 2;

		while (0 == (hand & 15)) {
			hand >>= 4;
				value++;
		}

		while (0 == (hand & 1)) {
			hand >>= 1;
		}

		return hand == FLUSH_MASK ? value : 0;
	}

	private static int fourOfAKind(long hand) {
		long mask = FOUR_OF_A_KIND;

		for (int value = 2; value <= 14; value++) {
			if (mask == (hand & mask)) {
				return value;
			}

			mask <<= 4;
		}

		return 0;
	}

	private static int fullHouse(long hand) {
		int value3 = 0;
		int value2 = 0;
		int currentValue = 2;

		for (int i = 0; i < 2; i++) {
			while ((hand & 15) == 0) {
				currentValue++;
				hand >>= 4;
			}

			switch (Long.bitCount(hand & 15)) {
			case 2:
				value2 = currentValue;
				break;
			case 3:
				value3 = currentValue;
				break;
			default:
				return 0;
			}

			currentValue++;
			hand >>= 4;
		}

		if (value3 == 0 || value2 == 0) {
			return 0;
		}

		return 100 * value3 + value2;
	}

	private static int threeOfAKind(long hand) {
		for (int value = 2; value <= 14; value++) {
			long slot = hand >> 4 * (value - 2) & 15;

			if (slot == 0b0111 || slot == 0b1011 || slot == 0b1101
				|| slot == 0b1110) {
				return value;
			}
		}

		return 0;
	}

	private static int twoPairs(long hand) {
		int pairs = 0;
		int resultValue = 0;

		for (int value = 14; value >= 2; value--) {
			long slot = hand >> 4 * (value - 2) & 15;

			if (Long.bitCount(slot) == 2) {
				resultValue = resultValue * 100 + value;
				pairs++;
			}
		}

		return pairs == 2 ? resultValue : 0;
	}

	private static int flush(long hand) {
		int result = 0;
		int pot = 1;
		long check = 0;

		for (int value = 2; value <= 14; value++) {
			long slot = hand >> 4 * (value - 2) & 15;

			if (slot != 0) {
				result += value * pot;
				check = (check << 4) | slot;
				pot *= 16;
			}
		}

		while ((check & 1) == 0) {
			check >>= 1;
		}

		return (check == 0b00010001000100010001) ? result : 0;
	}

	private static int straight(long hand) {
		int value = 2;
		long slot;

		while (0 == (hand >> 4 * (value - 2) & 15)) {
			value++;
		}

		for (int i = 2; i <= 5; i++) {
			value++;
			slot = hand >> 4 * (value - 2) & 15;

			if (slot == 0) {
				return 0;
			}
		}

		return value;
	}

	private static int onePair(long hand) {
		if (threeOfAKind(hand) != 0 || twoPairs(hand) != 0) {
			return 0;
		}

		for (int value = 14; value >= 2; value--) {
			long slot = hand >> 4 * (value - 2) & 15;

			if (Long.bitCount(slot) == 2) {
				return value;
			}
		}

		return 0;
	}

	private static int highCard(long hand) {
		int result = 0;

		for (int value = 14; value >= 2; value--) {
			long slot = (hand >> 4 * (value - 2)) & 15;

			if (slot > 0) {
				result = result * 16 + value;
			}
		}

		return result;
	}

	private static int playerWithHigherCards(long[] hands) {
		switch (Integer.compare(highCard(hands[0]), highCard(hands[1]))) {
		case -1:
			return 2;
		case 1:
			return 1;
		default:
			return 0;
		}
	}

	private static int whoWins(int v1, int v2, long[] hands) {
		if (v1 > v2) {
			return 1;
		} else if (v1 < v2) {
			return 2;
		} else {
			return playerWithHigherCards(hands);
		}
	}
}
