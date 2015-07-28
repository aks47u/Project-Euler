#include <stdio.h>

int main(void) {
	int result = 0;

	for (int i = 1; i < 1000; i++) {
		if (i % 3 == 0 || i % 5 == 0) {
			result += i;
		}
	}

	printf("%d\n", result);

	return 0;
}
