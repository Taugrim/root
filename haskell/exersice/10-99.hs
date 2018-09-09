q (x:xs) z w|z==[]=q xs [x] w
q (x:xs) z w| (head z)==x=q xs (x:z) w
q (x:xs) z w| (head z)/=x=q xs [x] ((if (length z>1)then(length z,(head(z)))else z):w)

q [] z w=((length z,(head(z))):w)
