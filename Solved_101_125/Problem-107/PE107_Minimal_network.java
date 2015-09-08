package Solved_101_125;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

/**
 * Minimal network
 * Problem 107
 * 
 * The following undirected network consists of seven vertices and twelve edges
 * with a total weight of 243.
 * 
 * The same network can be represented by the matrix below.
 * 		A	B	C	D	E	F	G
 * 	A	-	16	12	21	-	-	-
 * 	B	16	-	-	17	20	-	-
 * 	C	12	-	-	28	-	31	-
 * 	D	21	17	28	-	18	19	23
 * 	E	-	20	-	18	-	-	11
 * 	F	-	-	31	19	-	-	27
 * 	G	-	-	-	23	11	27	-
 * 
 * However, it is possible to optimise the network by removing some edges and
 * still ensure that all points on the network remain connected. The network
 * which achieves the maximum saving is shown below. It has a weight of 93,
 * representing a saving of 243 - 93 = 150 from the original network.
 * 
 * Using network.txt (right click and 'Save Link/Target As...'), a 6K text file
 * containing a network with forty vertices, and given in matrix form, find the
 * maximum saving which can be achieved by removing redundant edges whilst
 * ensuring that the network remains connected.
 */
public class PE107_Minimal_network {
	private static int max = Integer.MAX_VALUE;
	private static int[][] graph = new int[40][40];

	public static void main(String[] args) {
		long start = System.nanoTime();

		String line = null;

		try {
			File InFile = new File("PE107_network.txt");
			FileReader fileReader = new FileReader(InFile);
			BufferedReader reader = new BufferedReader(fileReader);
			int j = 0;

			while ((line = reader.readLine()) != null) {
				String[] raw = line.split(",");

				for (int i = 0; i < raw.length; i++) {
					if (raw[i].equals("-")) {
						graph[j][i] = max;
					} else {
						graph[j][i] = Integer.parseInt(raw[i]);
					}
				}

				j++;
			}

			reader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		long weight = calcweight(graph);
		long mstWeight = mst(graph);
		long result = weight - mstWeight;

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	private static long calcweight(int[][] graph) {
		long weight = 0;

		for (int i = 0; i < graph.length; ++i) {
			for (int j = i + 1; j < graph.length; ++j) {
				if (graph[i][j] != max) {
					weight += graph[i][j];
				}
			}
		}

		return weight;
	}

	private static long mst(int[][] graph) {
		int n = graph.length;
		boolean[] in = new boolean[n];
		Arrays.fill(in, false);
		in[0] = true;
		int insize = 1;
		long weight = 0;

		while (insize < n) {
			int cheapest = max;
			int toi = 0;

			for (int i = 0; i < n; ++i) {
				if (in[i] == true) {
					for (int j = 0; j < n; ++j) {
						if (i != j && in[j] == false) {
							if (graph[i][j] < cheapest) {
								cheapest = graph[i][j];
								toi = j;
							}
						}
					}
				}
			}

			in[toi] = true;
			insize++;
			weight += cheapest;
		}

		return weight;
	}
}
