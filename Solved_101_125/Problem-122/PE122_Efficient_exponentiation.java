package Solved_101_125;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Efficient exponentiation
 * Problem 122
 * 
 * The most naive way of computing n^15 requires fourteen multiplications:
 * 
 * n × n × ... × n = n^15
 * 
 * But using a "binary" method you can compute it in six multiplications:
 * 
 * n × n = n^2
 * n^2 × n^2 = n^4
 * n^4 × n^4 = n^8
 * n^8 × n^4 = n^12
 * n^12 × n^2 = n^14
 * n^14 × n = ^n15
 * 
 * However it is yet possible to compute it in only five multiplications:
 * 
 * n × n = n^2
 * n^2 × n = n^3
 * n^3 × n^3 = n^6
 * n^6 × n^6 = n^12
 * n^12 × n^3 = n^15
 * 
 * We shall define m(k) to be the minimum number of multiplications to compute
 * n^k; for example m(15) = 5.
 * 
 * For 1 <= k <= 200, find SUM m(k).
 */
public class PE122_Efficient_exponentiation {
	public static void main(String[] args) {
		long start = System.nanoTime();
		
		int n = 200, result = 0, generation = 0;
		int[] m = new int[n + 1];
		Set<BitSet> curgen = new HashSet<BitSet>();
		m[1] = 0;
		BitSet progenitor = new BitSet();
		progenitor.set(1);
		curgen.add(progenitor);
		
		while (curgen.size() > 0) {
			Set<BitSet> nextgen = new HashSet<BitSet>();
			generation++;
			
			for (Iterator<BitSet> it = curgen.iterator(); it.hasNext();) {
				BitSet product = it.next();
				int a = product.length() - 1;
				
				for (int b = product.nextSetBit(0); b >= 0; b = product
						.nextSetBit(b + 1)) {
					BitSet child = (BitSet) product.clone();
					child.set(a + b);
					
					if (a + b <= n) {
						if (m[a + b] == 0) {
							m[a + b] = generation;
							result += generation;
						}
						
						if (m[a + b] == generation) {
							nextgen.add(child);
						}
					}
				}
			}
			
			curgen = nextgen;
		}
		
		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
