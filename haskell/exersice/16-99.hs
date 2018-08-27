dropp :: [a]->Int->Int->[a]->[a]
dropp (x:xs) n nn sx=dropp xs (n-1) nn (x:sx)
dropp (x:xs) 1 nn sx=dropp ( xs)  nn   nn  sx

dropp [] _ _ sx  = sx


d x n =dropp x n n []