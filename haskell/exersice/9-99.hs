q (x:xs) z w|z==[]=q xs [x] w
q (x:xs) z w| (head z)==x=q xs (x:z) w
q (x:xs) z w| (head z)/=x=q xs [x] (z:w)

q [] z w=z:w
d x= q x [] []
