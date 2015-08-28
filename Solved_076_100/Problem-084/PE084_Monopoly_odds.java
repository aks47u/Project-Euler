package Solved_076_100;

import java.util.Arrays;
import java.util.Random;

/**
 * Monopoly odds
 * Problem 84
 * 
 * In the game, Monopoly, the standard board is set up in the following way: GO
 * A1 CC1 A2 T1 R1 B1 CH1 B2 B3 JAIL H2 C1 T2 U1 H1 C2 CH3 C3 R4 R2 G3 D1 CC3
 * CC2 G2 D2 G1 D3 G2J F3 U2 F2 F1 R3 E3 E2 CH2 E1 FP
 * 
 * A player starts on the GO square and adds the scores on two 6-sided dice to
 * determine the number of squares they advance in a clockwise direction.
 * Without any further rules we would expect to visit each square with equal
 * probability: 2.5%. However, landing on G2J (Go To Jail), CC (community
 * chest), and CH (chance) changes this distribution.
 * 
 * In addition to G2J, and one card from each of CC and CH, that orders the
 * player to go directly to jail, if a player rolls three consecutive doubles,
 * they do not advance the result of their 3rd roll. Instead they proceed
 * directly to jail.
 * 
 * At the beginning of the game, the CC and CH cards are shuffled. When a player
 * lands on CC or CH they take a card from the top of the respective pile and,
 * after following the instructions, it is returned to the bottom of the pile.
 * There are sixteen cards in each pile, but for the purpose of this problem we
 * are only concerned with cards that order a movement; any instruction not
 * concerned with movement will be ignored and the player will remain on the
 * CC/CH square.
 * 
 * Community Chest (2/16 cards):
 * 	Advance to GO
 * 	Go to JAIL
 * Chance (10/16 cards):
 * 	Advance to GO
 * 	Go to JAIL
 * 	Go to C1
 * 	Go to E3
 * 	Go to H2
 * 	Go to R1
 * 	Go to next R (railway company)
 * 	Go to next R
 * 	Go to next U (utility company)
 * 	Go back 3 squares.
 * 
 * The heart of this problem concerns the likelihood of visiting a particular
 * square. That is, the probability of finishing at that square after a roll.
 * For this reason it should be clear that, with the exception of G2J for which
 * the probability of finishing on it is zero, the CH squares will have the
 * lowest probabilities, as 5/8 request a movement to another square, and it is
 * the final square that the player finishes at on each roll that we are
 * interested in. We shall make no distinction between "Just Visiting" and being
 * sent to JAIL, and we shall also ignore the rule about requiring a double to
 * "get out of jail", assuming that they pay to get out on their next turn.
 * 
 * By starting at GO and numbering the squares sequentially from 00 to 39 we can
 * concatenate these two-digit numbers to produce strings that correspond with
 * sets of squares.
 * 
 * Statistically it can be shown that the three most popular squares, in order,
 * are JAIL (6.24%) = Square 10, E3 (3.18%) = Square 24, and GO (3.09%) = Square
 * 00. So these three most popular squares can be listed with the six-digit
 * modal string: 102400.
 * 
 * If, instead of using two 6-sided dice, two 4-sided dice are used, find the
 * six-digit modal string.
 */
public class PE084_Monopoly_odds {
	private static int[] squares = new int[40];
	private static Random generator = new Random();
	private static int cc = 0, ch = 0;

	public static void main(String[] args) {
		long start = System.nanoTime();

		playGame(1000000);
		int[] temp = new int[40], threeBest = new int[3];

		for (int i = 0; i < 40; i++) {
			temp[i] = squares[i];
		}

		Arrays.sort(temp);
		threeBest[0] = temp[39];
		threeBest[1] = temp[38];
		threeBest[2] = temp[37];
		String result = "";

		for (int i = 0; i < 40; i++) {
			for (int j = 0; j < 3; j++) {
				if (squares[i] == threeBest[j]) {
					result += i;
				}
			}
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static int roll() {
		return generator.nextInt(4) + 1;
	}

	private static int getCommunityChest(int current) {
		if (cc == 0) {
			cc = (cc + 1) % 16;

			return 0;
		} else if (cc == 1) {
			cc = (cc + 1) % 16;

			return 10;
		} else {
			cc = (cc + 1) % 16;

			return current;
		}
	}

	private static int getChance(int current) {
		if (ch == 0) {
			ch = (ch + 1) % 16;

			return 0;
		} else if (ch == 1) {
			ch = (ch + 1) % 16;

			return 10;
		} else if (ch == 2) {
			ch = (ch + 1) % 16;

			return 11;
		} else if (ch == 3) {
			ch = (ch + 1) % 16;

			return 24;
		} else if (ch == 4) {
			ch = (ch + 1) % 16;

			return 39;
		} else if (ch == 5) {
			ch = (ch + 1) % 16;

			return 5;
		} else if (ch == 6 || ch == 7) {
			ch = (ch + 1) % 16;

			switch (current) {
			case 7:
				return 15;
			case 22:
				return 25;
			case 36:
				return 5;
			default:
				return 0;
			}
		} else if (ch == 8) {
			ch = (ch + 1) % 16;

			switch (current) {
			case 7:
			case 36:
				return 12;
			case 22:
				return 28;
			default:
				return 0;
			}
		} else if (ch == 9) {
			int next = (current - 3 + 40) % 40;
			ch = (ch + 1) % 16;

			if (next == 33) {
				next = getCommunityChest(next);
			}

			return next;
		} else {
			ch = (ch + 1) % 16;

			return current;
		}
	}

	private static void playGame(long nmoves) {
		int current = 0, doubles = 0;

		for (long i = 1; i <= nmoves; i++) {
			int r1 = roll(), r2 = roll();

			if (r1 == r2) {
				doubles++;
			} else {
				doubles = 0;
			}

			if (doubles == 3) {
				doubles = 0;
				current = 10;
			} else {
				current = (current + r1 + r2) % 40;

				switch (current) {
				case 30:
					current = 10;
					break;
				case 2:
				case 17:
				case 33:
					current = getCommunityChest(current);
					break;
				case 7:
				case 22:
				case 36:
					current = getChance(current);
					break;
				}
			}

			squares[current]++;
		}
	}
}
