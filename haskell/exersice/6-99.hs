
r (x:xs) y= r xs (x:y) 
r [] y=y
isPalindrome x=x==( r x [])
		