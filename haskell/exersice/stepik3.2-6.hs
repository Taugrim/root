import Data.Char 
q []=True
q (x:xs) =(isUpper x) && q xs 
qq z | (q z)/=True=z
qq z =""

--удаляет из массива элемент
del (x:xs) acc m|m==x=del (xs) (acc) m
del (x:xs) acc m=del (xs) (acc++[x]) m
del [] acc m=acc
del2   m a=del a [] m

s str=unwords. del2 "".map qq .  words $ str