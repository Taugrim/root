q (x:xs) z w|z==[]=q xs [x] w
q (x:xs) z w| (head z)==x=q xs (x:z) w
q (x:xs) z w| (head z)/=x=q xs [x] (z:w)

q [] z w=z:w
d x= q x [] []

f x =(length x,head x)
cc a=[f x|x<-d a]
data ListItem a = Single a | Multiple Int a
    deriving (Show)
helper (1,x)=Single x
helper (n,x)=Multiple n x
encode x=[helper s|s<-cc x]