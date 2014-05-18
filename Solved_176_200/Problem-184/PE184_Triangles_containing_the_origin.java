package Solved_176_200;

import java.util.LinkedList;

/**
 * Triangles containing the origin
 * Problem 184
 * 
 * Consider the set Ir of points (x,y) with integer co-ordinates in the interior
 * of the circle with radius r, centered at the origin, i.e. x2 + y2 < r2.
 * 
 * For a radius of 2, I2 contains the nine points (0,0), (1,0), (1,1), (0,1),
 * (-1,1), (-1,0), (-1,-1), (0,-1) and (1,-1). There are eight triangles having
 * all three vertices in I2 which contain the origin in the interior. Two of
 * them are shown below, the others are obtained from these by rotation.
 * 
 * For a radius of 3, there are 360 triangles containing the origin in the
 * interior and having all vertices in I3 and for I5 the number is 10600.
 * 
 * How many triangles are there containing the origin in the interior and having
 * all three vertices in I105?
 */
public class PE184_Triangles_containing_the_origin {
	public static void main(String[] args) {
		long start = System.nanoTime();

		int r = 105;
		int[][] arr = new int[r * r][2];
		int position = 0;
		long result = 0;

		for (int y = 0; y < r; y++) {
			for (int x = r - 1; x > 0; x--) {
				if (y * y + x * x < r * r) {
					arr[position][0] = y;
					arr[position][1] = x;
					position++;
				}
			}
		}

		int[][] array = new int[position][2];
		System.arraycopy(arr, 0, array, 0, position);

		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i][0] * array[j][1] > array[j][0] * array[i][1]) {
					int temp[] = new int[2];
					temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}

		LinkedList<Integer> l = new LinkedList<Integer>();
		int temp = 1;

		for (int i = 0; i < position - 1; i++) {
			if (array[i][0] * array[i + 1][1] == array[i + 1][0] * array[i][1]) {
				temp++;
			} else {
				l.add(new Integer(temp));
				temp = 1;
			}
		}

		l.add(new Integer(1));
		int size = l.size();

		for (int i = 0; i < size; i++) {
			l.add(l.get(i));
		}

		size = l.size();
		double tem1 = 0;
		double tem2 = 0;

		for (int i = size - 1; i > 0; i--) {
			tem1 = tem1 + ((Integer) (l.get(i))).intValue();
			tem2 = tem2 + ((Integer) (l.get(i))).intValue()
					* ((Integer) (l.get(i))).intValue();
			result += ((Integer) (l.get(i - 1))).intValue()
					* (tem1 * tem1 - tem2) / 2;
		}

		result *= 2;

		long end = System.nanoTime();
		long runtime = end - start;
		System.out.println(result);
		System.out.println("Runtime: " + runtime / 1000000 + "ms (" + runtime
				+ "ns)");
	}
}
