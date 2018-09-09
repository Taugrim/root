
q (x:xs) = max x $ if xs==[]then x else (q xs)



z [] _ _=[]
z _ [] _=[]
z _ _[] =[]
z (x:xs) (w:ws)(e:es)=(q [x,w,e]):(z (xs) (ws)(es))