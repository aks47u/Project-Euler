package Solved_076_100;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Path sum: four ways
 * Problem 83
 * 
 * NOTE: This problem is a significantly more challenging version of Problem 81.
 * 
 * In the 5 by 5 matrix below, the minimal path sum from the top left to the
 * bottom right, by moving left, right, up, and down, is indicated in bold red
 * and is equal to 2297.
 * 
 * 131 673 234 103  18
 * 201  96 342 965 150
 * 630 803 746 422 111
 * 537 699 497 121 956
 * 805 732 524  37 331
 * 
 * Find the minimal path sum, in matrix.txt (right click and 'Save Link/Target
 * As...'), a 31K text file containing a 80 by 80 matrix, from the top left to
 * the bottom right by moving left, right, up, and down.
 */
public class PE083_Path_sum_four_ways {
	static int[][] grid;
	static List<Vertex> graph = new ArrayList<Vertex>();

	public static void main(String[] args) throws IOException {
		long start = System.nanoTime();

		String fileName = "PE083_matrix.txt";
		String lineString = "";
		List<String> listData = new ArrayList<String>();
		BufferedReader data = new BufferedReader(new FileReader(fileName));

		while ((lineString = data.readLine()) != null) {
			listData.add(lineString);
		}
		
		data.close();

		assignArray(listData.size());
		for (int index = 0, row_counter = 0; index <= listData.size() - 1; ++index, row_counter++) {
			populateArray(listData.get(index), row_counter);
		}

		int gridSize = grid.length;

		for (int index = 0; index <= gridSize - 1; ++index) {
			for (int innerIndex = 0; innerIndex <= gridSize - 1; ++innerIndex) {
				graph.add(new Vertex(grid[index][innerIndex]));
			}
		}

		for (int i = 0; i < graph.size(); ++i) {
			if ((i + 1) % gridSize > 0) {
				graph.get(i).adjacencies.add(new Edge(graph.get(i + 1),
						0 + graph.get(i + 1).value));
			}

			if (i < graph.size() - gridSize) {
				graph.get(i).adjacencies.add(new Edge(graph.get(i + gridSize),
						0 + graph.get(i + gridSize).value));
			}

			if (i > gridSize) {
				graph.get(i).adjacencies.add(new Edge(graph.get(i - gridSize),
						0 + graph.get(i - gridSize).value));
			}

			if ((i + gridSize) % gridSize > 0) {
				graph.get(i).adjacencies.add(new Edge(graph.get(i - 1),
						0 + graph.get(i - 1).value));
			}
		}

		computePaths(graph.get(0));

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println((int) graph.get(graph.size() - 1).minDistance);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}

	public static void populateArray(String str, int row) {
		int counter = 0;
		String[] data = str.split(",");

		for (int index = 0; index <= data.length - 1; ++index) {
			grid[row][counter++] = Integer.parseInt(data[index]);
		}
	}

	public static void assignArray(int no_of_row) {
		grid = new int[no_of_row][no_of_row];
	}

	public static class Vertex implements Comparable<Vertex> {
		public final int value;
		public List<Edge> adjacencies;
		public double minDistance = Double.POSITIVE_INFINITY;

		public Vertex(int value) {
			this.value = value;
			this.adjacencies = new ArrayList<Edge>();
		}

		public int compareTo(Vertex other) {
			return Double.compare(minDistance, other.minDistance);
		}
	}

	static public class Edge {
		public final Vertex target;
		public final double weight;

		public Edge(Vertex argTarget, double argWeight) {
			target = argTarget;
			weight = argWeight;
		}
	}

	public static void computePaths(Vertex source) {
		source.minDistance = source.value;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();

			for (Edge e : u.adjacencies) {
				Vertex v = e.target;
				double weight = e.weight;
				double distanceThroughU = u.minDistance + weight;

				if (distanceThroughU < v.minDistance) {
					vertexQueue.remove(v);
					v.minDistance = distanceThroughU;
					vertexQueue.add(v);
				}
			}
		}
	}
}
