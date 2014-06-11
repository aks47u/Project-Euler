using System;

public class Test {
	public static void Main() {
		int limit = 4000000;
		int previous = 1;
		int result = 0;
		int x = 0;
		
		for (int i = 1; i <= limit; i += previous) {
			if(x > 0) {
				previous = i - previous;
			}
			
			x++;
			
			if (i % 2 == 0) {
				result += i;
			}
		}
		
		Console.WriteLine(result);
	}
}
