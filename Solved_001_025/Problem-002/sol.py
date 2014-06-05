x = y = 1
sum = 0

while (sum < 4000000):
	sum += (x + y)
	x, y = x + 2 * y, 2 * x + 3 * y
  
print sum
