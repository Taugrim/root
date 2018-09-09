


f  (n,ns) = ns:f(ns,(n+ ns))
fibStream = [0,1]++f(1,1)
q= take 10 $ fibStream 