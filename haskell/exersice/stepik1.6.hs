integration :: (Double -> Double) -> Double -> Double -> Double
integration f a b = let
    n=200;
	g r=r;
	a1=g a;
	b1=g b;
    h=(a1-b1)/n ;
    sumF  =[f x|x<-[b1,b1+h..a1]];
	s =[z|z<-sumF]
    summ =h/500+((sum s  )+((f a)+(f b))/2)*h;
    in summ;
