package combinator;


import java.util.Collections;
import java.util.List;
import java.util.Map;
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
    public static <T,V,R> List<List<R>> of(
            BiFunction<List<T>,List<T>,R>merger,
            List<Supplier<List<T>>> list){
        return (List<List<R>>) list.stream().map(q->q.get()).reduce((q1, q2)-> (List<T>) merger.apply( q1,  q2)).orElse(  Collections.emptyList());
    }


}
