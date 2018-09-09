
r (x:xs) y= r xs (x:y) 
r [] y=y
myReverse x= r x []
		