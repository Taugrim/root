duplicate (x:xs) z n=duplicate (xs ) (a x n z) n
duplicate [] z n=z


a x n z=[x|s<-[1..n]]++z

dd x n=duplicate x [] n