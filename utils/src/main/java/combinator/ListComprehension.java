package combinator;


import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ListComprehension {
    public static <T,V,R> List<List<R>> of(
            BiFunction<T,V,R>merger,
            Supplier<List<T>>gen1,
            Supplier<List<V>>gen2
    ){
        var ts=gen1.get();
        var vs=gen2.get();
        return    ts.stream().map(t->vs.stream().map(v->merger.apply(t,v)).collect(Collectors.toList())).collect(Collectors.toList());

    }



}
