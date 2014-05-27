main = print $ sum [n | n <- [1..1000-1], n `mod` 5 == 0 || n `mod` 3 == 0]
