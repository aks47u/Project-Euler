package Solved_201_225;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Combined Volume of Cuboids
 * Problem 212
 * 
 * An axis-aligned cuboid, specified by parameters { (x0,y0,z0), (dx,dy,dz) },
 * consists of all points (X,Y,Z) such that x0 <= X <= x0+dx, y0 <= Y <= y0+dy
 * and z0 <= Z <= z0+dz. The volume of the cuboid is the product, dx × dy × dz.
 * The combined volume of a collection of cuboids is the volume of their union
 * and will be less than the sum of the individual volumes if any cuboids
 * overlap.
 * 
 * Let C1,...,C50000 be a collection of 50000 axis-aligned cuboids such that Cn
 * has parameters
 * 
 * x0 = S6n-5 modulo 10000
 * y0 = S6n-4 modulo 10000
 * z0 = S6n-3 modulo 10000
 * dx = 1 + (S6n-2 modulo 399)
 * dy = 1 + (S6n-1 modulo 399)
 * dz = 1 + (S6n modulo 399)
 * 
 * where S1,...,S300000 come from the "Lagged Fibonacci Generator":
 * 
 * For 1 <= k <= 55, Sk = [100003 - 200003k + 300007k^3] (modulo 1000000)
 * For 56 <= k, Sk = [Sk-24 + Sk-55] (modulo 1000000)
 * 
 * Thus, C1 has parameters {(7,53,183),(94,369,56)}, C2 has parameters
 * {(2383,3563,5079),(42,212,344)}, and so on.
 * 
 * The combined volume of the first 100 cuboids, C1,...,C100, is 723581599.
 * 
 * What is the combined volume of all 50000 cuboids, C1,...,C50000 ?
 */
public class PE212_Combined_Volume_of_Cuboids {
	public static void main(String[] args) {
		long start = System.nanoTime();

		LaggedFibonacci l = new LaggedFibonacci(300000);
		CuboidIndex ci = new CuboidIndex(200);
		int cuboids = 50000;
		int maxIndex = 6 * cuboids;

		for (int i = 0; i < maxIndex; i += 6) {
			Cuboid c = new Cuboid(l.get(i).intValue() % 10000, l.get(i + 1)
					.intValue() % 10000, l.get(i + 2).intValue() % 10000,
					1 + (l.get(i + 3).intValue() % 399), 1 + (l.get(i + 4)
							.intValue() % 399),
							1 + (l.get(i + 5).intValue() % 399));
			ci.index(c);
		}

		BigInteger result = ci.findCombinedVolume();

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}

class LaggedFibonacci extends ArrayList<BigInteger> {
	private static final long serialVersionUID = -5206590647017546477L;
	private static final BigInteger c1 = new BigInteger("100003");
	private static final BigInteger c2 = new BigInteger("200003");
	private static final BigInteger c3 = new BigInteger("300007");
	private static final BigInteger modLimit = new BigInteger("1000000");

	public LaggedFibonacci(int totalSize) {
		this(createSeed(new BigInteger("55")), totalSize, 24, 55, modLimit);
	}

	public LaggedFibonacci(List<BigInteger> seed, int totalSize, int dA,
			int dB, BigInteger modulus) {
		super(seed);

		for (int i = seed.size(); i <= totalSize; i++) {
			BigInteger newValue = get(i - dA).add(get(i - dB));

			if (modulus != null) {
				newValue = newValue.mod(modulus);
			}

			add(newValue);
		}
	}

	public static List<BigInteger> createSeed(BigInteger limit) {
		List<BigInteger> result = new ArrayList<BigInteger>();

		for (BigInteger k = BigInteger.ONE; k.compareTo(limit) <= 0; k = k
				.add(BigInteger.ONE)) {
			BigInteger newValue = c1.subtract(c2.multiply(k)).add(
					c3.multiply(k).multiply(k).multiply(k));
			newValue = newValue.mod(modLimit);
			result.add(newValue);
		}

		return result;
	}
}

class Cuboid {
	private int x0, y0, z0;
	private int dx, dy, dz;

	public Cuboid(int x0, int y0, int z0, int dx, int dy, int dz) {
		this.x0 = x0;
		this.y0 = y0;
		this.z0 = z0;
		this.dx = dx;
		this.dy = dy;
		this.dz = dz;
	}

	public Cuboid() {
	}

	public int volume() {
		return dx * dy * dz;
	}

	public List<Cuboid> clip(int interval) {
		int xStart = interval * ((int) (x0 / interval));
		int xEnd = interval * ((int) (x0 + dx) / interval);
		int yStart = interval * ((int) (y0 / interval));
		int yEnd = interval * ((int) (y0 + dy) / interval);
		int zStart = interval * ((int) (z0 / interval));
		int zEnd = interval * ((int) (z0 + dz) / interval);
		List<Cuboid> result = new ArrayList<Cuboid>();

		for (int ix = xStart; ix <= xEnd; ix += interval) {
			for (int iy = yStart; iy <= yEnd; iy += interval) {
				for (int iz = zStart; iz <= zEnd; iz += interval) {
					int cxstart = (ix > xStart) ? ix : x0;
					int cxend = (ix < xEnd) ? (ix + interval) : (x0 + dx);
					int cystart = (iy > yStart) ? iy : y0;
					int cyend = (iy < yEnd) ? (iy + interval) : (y0 + dy);
					int czstart = (iz > zStart) ? iz : z0;
					int czend = (iz < zEnd) ? (iz + interval) : (z0 + dz);
					result.add(new Cuboid(cxstart, cxend - cxstart, cystart,
							cyend - cystart, czstart, czend - czstart));
				}
			}
		}

		return result;
	}

	public int getX0() {
		return x0;
	}

	public void setX0(int x0) {
		this.x0 = x0;
	}

	public int getY0() {
		return y0;
	}

	public void setY0(int y0) {
		this.y0 = y0;
	}

	public int getZ0() {
		return z0;
	}

	public void setZ0(int z0) {
		this.z0 = z0;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public int getDz() {
		return dz;
	}

	public void setDz(int dz) {
		this.dz = dz;
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof Cuboid)) {
			return false;
		}

		Cuboid other = (Cuboid) o;

		return (other.x0 == x0) && (other.y0 == y0) && (other.z0 == z0)
				&& (other.dx == dx) && (other.dy == dy) && (other.dz == dz);
	}

	public boolean envelops(Cuboid b) {
		return ((getX0() <= b.getX0())
				&& (getX0() + getDx() >= b.getX0() + b.getDx())
				&& (getY0() <= b.getY0())
				&& (getY0() + getDy() >= b.getY0() + b.getDy())
				&& (getZ0() <= b.getZ0()) && (getZ0() + getDz() >= b.getZ0()
				+ b.getDz()));
	}

	public Cuboid intersection(Cuboid b) {
		int minX = Math.max(x0, b.x0);
		int maxX = Math.min(x0 + dx, b.x0 + b.dx);
		int minY = Math.max(y0, b.y0);
		int maxY = Math.min(y0 + dy, b.y0 + b.dy);
		int minZ = Math.max(z0, b.z0);
		int maxZ = Math.min(z0 + dz, b.z0 + b.dz);

		if ((maxX > minX) && (maxY > minY) && (maxZ > minZ)) {
			return new Cuboid(minX, minY, minZ, maxX - minX, maxY - minY, maxZ
					- minZ);
		} else {
			return null;
		}
	}

	public static int combinedVolume(Set<Cuboid> cuboids) {
		long volume = 0;
		long groups = (1 << cuboids.size());

		for (long l = 1; l < groups; l++) {
			boolean add = (Long.bitCount(l) % 2 == 1);
			Cuboid combine = null;
			boolean first = true;
			int index = 0;

			for (Iterator<Cuboid> it = cuboids.iterator(); it.hasNext(); index++) {
				Cuboid current = it.next();

				if ((l & (1L << index)) != 0L) {
					if (first) {
						combine = current;
						first = false;
					} else {
						combine = combine.intersection(current);

						if (combine == null) {
							break;
						}
					}
				}
			}

			if (combine != null) {
				if (add) {
					volume += combine.volume();
				} else {
					volume -= combine.volume();
				}
			}
		}

		return (int) volume;
	}

	public int hashCode() {
		return (x0 << 16) ^ (y0 << 8) ^ (z0) ^ (dx << 18) ^ (dy << 10)
				^ (dz << 2);
	}

	public String toString() {
		return "(" + x0 + "," + y0 + "," + z0 + "):(" + dx + "x" + dy + "x"
				+ dz + ")";
	}
}

class CuboidIndex extends HashMap<Cuboid, Set<Cuboid>> {
	private static final long serialVersionUID = -8899307732986751902L;
	private int interval;

	public CuboidIndex(int interval) {
		super();
		this.interval = interval;
	}

	public void index(Cuboid c) {
		int xStart = interval * ((int) (c.getX0() / interval));
		int xEnd = interval * ((int) (c.getX0() + c.getDx()) / interval);
		int yStart = interval * ((int) (c.getY0() / interval));
		int yEnd = interval * ((int) (c.getY0() + c.getDy()) / interval);
		int zStart = interval * ((int) (c.getZ0() / interval));
		int zEnd = interval * ((int) (c.getZ0() + c.getDz()) / interval);

		for (int ix = xStart; ix <= xEnd; ix += interval) {
			for (int iy = yStart; iy <= yEnd; iy += interval) {
				for (int iz = zStart; iz <= zEnd; iz += interval) {
					int cxstart = (ix > xStart) ? ix : c.getX0();
					int cxend = (ix < xEnd) ? (ix + interval) : (c.getX0() + c
							.getDx());
					int cystart = (iy > yStart) ? iy : c.getY0();
					int cyend = (iy < yEnd) ? (iy + interval) : (c.getY0() + c
							.getDy());
					int czstart = (iz > zStart) ? iz : c.getZ0();
					int czend = (iz < zEnd) ? (iz + interval) : (c.getZ0() + c
							.getDz());
					Cuboid key = new Cuboid(ix, iy, iz, interval, interval,
							interval);
					Set<Cuboid> s = get(key);

					if (s == null) {
						s = new HashSet<Cuboid>();
						put(key, s);
					}

					addCuboid(s, new Cuboid(cxstart, cystart, czstart, cxend
							- cxstart, cyend - cystart, czend - czstart));
				}
			}
		}
	}

	public BigInteger findCombinedVolume() {
		BigInteger volume = BigInteger.ZERO;

		for (Cuboid key : keySet()) {
			Set<Cuboid> values = get(key);
			volume = volume.add(new BigInteger(""
					+ Cuboid.combinedVolume(values)));
		}

		return volume;
	}

	public boolean isFull(Set<Cuboid> s) {
		if (s.size() >= 1) {
			Iterator<Cuboid> p = s.iterator();

			return isFull(p.next());
		}

		return false;
	}

	public boolean isFull(Cuboid c) {
		return ((c.getDx() == interval) && (c.getDy() == interval) && (c
				.getDz() == interval));
	}

	public void addCuboid(Set<Cuboid> s, Cuboid t) {
		for (Cuboid c : s) {
			if (c.envelops(t)) {
				return;
			}
		}

		if (isFull(t)) {
			s.clear();
			s.add(t);
		} else {
			Iterator<Cuboid> it = s.iterator();

			while (it.hasNext()) {
				Cuboid d = it.next();

				if (t.envelops(d)) {
					it.remove();
				}
			}

			s.add(t);
		}
	}
}
