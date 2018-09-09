
--удаляет из массива элемент
del (x:xs) acc m|m==x=acc++xs
del (x:xs) acc m=del (xs) (acc++[x]) m
del _ acc m=acc
del2 a  m=del a [] m



--вставляет в массив элемент на заданное место
r (x:xs) acc n m|n>=length((x:xs)) =(x:xs)++[m]
r (x:xs) acc n m|n>0=r (xs) (acc++[x]) (n-1) m
r (x:xs) acc n m|n<=0=(acc++[m])++(x:xs)
rr (x:xs)  n m=r (del2 (x:xs) m) [] (n) m



--возвращает номер элемента
count (x:xs) z acc| z==x=acc
count (x:xs) z acc| z/=x=count (xs) z (acc+1)
count [] _ acc=acc
cou (x:xs) z =count (x:xs) z 0


--выводит все комбинации по номеру позиции
comb4 (x:xs) n= map (rr (x:xs)  n )(x:xs)

--из массива делает пирамидку
c (x:xs) acc =c xs ((x:xs):acc)
c [] acc = acc



--возвращает массив индексов
countArr (x:xs)=map (cou (x:xs) )(x:xs)

comb5 (x:xs)=concatMap (comb4 (x:xs))(countArr (x:xs))


