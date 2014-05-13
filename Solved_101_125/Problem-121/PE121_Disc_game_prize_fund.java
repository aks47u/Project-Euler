package Solved_101_125;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Disc game prize fund
 * Problem 121
 * 
 * A bag contains one red disc and one blue disc. In a game of chance a player
 * takes a disc at random and its colour is noted. After each turn the disc is
 * returned to the bag, an extra red disc is added, and another disc is taken at
 * random.
 * 
 * The player pays £1 to play and wins if they have taken more blue discs than
 * red discs at the end of the game.
 * 
 * If the game is played for four turns, the probability of a player winning is
 * exactly 11/120, and so the maximum prize fund the banker should allocate for
 * winning in this game would be £10 before they would expect to incur a loss.
 * Note that any payout will be a whole number of pounds and also includes the
 * original £1 paid to play the game, so in the example given the player
 * actually wins £9.
 * 
 * Find the maximum prize fund that should be allocated to a single game in
 * which fifteen turns are played.
 */
public class PE121_Disc_game_prize_fund {
	public static void main(String[] args) {
		long start = System.nanoTime();

		List<Fraction> fractions = new ArrayList<Fraction>();
		int n = 15;

		for (int i = 0; i < n; ++i) {
			fractions.add(new Fraction(BigInteger.ONE, BigInteger
					.valueOf(i + 2)));
		}

		Fraction probability = new Fraction(BigInteger.ZERO, BigInteger.ONE);

		for (int i = 0; i < 1 << n; ++i) {
			if (Integer.bitCount(i) >= n / 2 + 1) {
				Fraction frac = new Fraction(BigInteger.ONE, BigInteger.ONE);

				for (int j = 0; j < n; ++j) {
					if (((i >> j) & 1) == 1) {
						frac.multiply(fractions.get(j));
					} else {
						Fraction r = new Fraction(
								fractions.get(j).d.subtract(BigInteger.ONE),
								fractions.get(j).d);
						frac.multiply(r);
					}
				}

				probability.add(frac).simplify();
			}
		}

		int result = 0;
		BigInteger p = probability.n;

		while (p.compareTo(probability.d) < 0) {
			p = p.add(probability.n);
			++result;
		}

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static class Fraction {
		private BigInteger n;
		private BigInteger d;

		public Fraction(BigInteger n, BigInteger d) {
			this.n = new BigInteger("0").add(n);
			this.d = new BigInteger("0").add(d);
		}

		public Fraction add(Fraction f) {
			this.n = this.n.multiply(f.d).add(f.n.multiply(this.d));
			this.d = this.d.multiply(f.d);

			return this;
		}

		public Fraction multiply(Fraction f) {
			this.n = this.n.multiply(f.n);
			this.d = this.d.multiply(f.d);

			return this;
		}

		public Fraction simplify() {
			BigInteger g = this.n.gcd(this.d);
			this.n = this.n.divide(g);
			this.d = this.d.divide(g);

			return this;
		}
	}
}
