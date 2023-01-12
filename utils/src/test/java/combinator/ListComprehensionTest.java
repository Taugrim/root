package combinator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static combinator.Combinator.fabric;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ListComprehensionTest {

    @Test
    void of() {
        var l = ListComprehension.of(
                List::of,
                () -> List.of(Map.entry("Q", 2), Map.entry("Q", 3)),
                () -> List.of(Map.entry("w", 6), Map.entry("w", 5), Map.entry("w", 7))

        );
        l.forEach(q -> log.info(String.valueOf(q)));
    }

    @Test
    void of2() {
        var l = ListComprehension.of(
                List::of,
                List.of(
                        () -> List.of("a1","a2").stream().map(q -> Map.entry("z", q)).collect(Collectors.toList()),
                        () -> List.of("q1", "q2").stream().map(q -> Map.entry("q", q)).collect(Collectors.toList()),
                        () -> List.of("w1", "w2").stream().map(q -> Map.entry("w", q)).collect(Collectors.toList()),
                        () -> List.of("e1", "e2").stream().map(q -> Map.entry("e", q)).collect(Collectors.toList()),
                        () -> List.of("r1","r2").stream().map(q -> Map.entry("r", q)).collect(Collectors.toList()))
        );
        l.forEach(q -> log.info(String.valueOf(q)));
    }

    public static <T, V> List<Map.Entry<V, T>> fabric(V key, List<T> values) {
        return values.stream().map(q -> Map.entry(key, q)).collect(Collectors.toList());
    }

}