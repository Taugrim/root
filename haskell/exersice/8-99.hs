q (x:xs:xxs) w|x==xs = q (xs:xxs) w
q (x:xs:xxs) w = q (xs:xxs) (x:w)
q (x:[]) w = reverse (x:w)
a x  = q x []
