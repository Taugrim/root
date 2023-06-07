package combinator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import types.TypeValues;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static combinator.Combinator.*;
@Slf4j
public class CombinatorTest {
    @Test
    void combinationsCTest(){
        combinations(List.of(
                        fabric("a", List.of("a1")),
                        fabric("q", List.of("q1", "q2")),
                        fabric("w", List.of("w1", "w2", "w3")),
                        fabric("e", List.of("e1", "e2")),
                        fabric("r", List.of("r1"))
                )
        ).forEach(q -> log.info(q.toString()));

    }
    @Test
    void combinationsListTest(){
        combinations(List.of(
                fabricList(List.of("a","b"),List.of(
                                TypeValues.LOGICBREAK,
                                TypeValues.BREAK,
                                TypeValues.EMPTY,
                                TypeValues.NULL,
                                TypeValues.SELPH,
                                TypeValues.DEL
                        ))

                )
        ).forEach(q -> log.info(q.toString()));

    }
    @Test
    void TangoTest(){
     combinations(List.of(
                        fabric("шаг1", List.of("вперед левой","вправо правой","назад левой","назад правой","крест левой","смена веса на левую","назад правой","влево левой","влево правой")),
                        fabric("шаг2", List.of("вперед левой","вправо правой","назад левой","назад правой","крест левой","смена веса на левую","назад правой","влево левой","влево правой")),
                        fabric("шаг3", List.of("вперед левой","вправо правой","назад левой","назад правой","крест левой","смена веса на левую","назад правой","влево левой","влево правой")),
                        fabric("шаг4", List.of("вперед левой","вправо правой","назад левой","назад правой","крест левой","смена веса на левую","назад правой","влево левой","влево правой")),
                        fabric("шаг5", List.of("вперед левой","вправо правой","назад левой","назад правой","крест левой","смена веса на левую","назад правой","влево левой","влево правой")),
                        fabric("шаг6", List.of("вперед левой","вправо правой","назад левой","назад правой","крест левой","смена веса на левую","назад правой","влево левой","влево правой")),
                        fabric("шаг7", List.of("вперед левой","вправо правой","назад левой","назад правой","крест левой","смена веса на левую","назад правой","влево левой","влево правой")),
                        fabric("шаг8", List.of("вперед левой","вправо правой","назад левой","назад правой","крест левой","смена веса на левую","назад правой","влево левой","влево правой"))

                )
      ).forEach(q -> log.info(q.toString()));


    }@Test
    void TangoTest2(){
      combinations(List.of(
                        fabric("направление", List.of("вперед","назад","влево","вправо","оче вперед","оче назад","поворот налево","поворот направо","смена веса")),
                        fabric("нога", List.of("левая","правая")),
                        fabric("роль", List.of("парнер","партрнерша"))

                )
      ).forEach(q -> log.info(q.toString()));

    }
    @Test
    void combinationsUUIDTest(){
        combinationsUUID(List.of(
                        fabric("a", List.of("a1")),
                        fabric("q", List.of("q1", "q2")),
                        fabric("w", List.of("w1", "w2", "w3")),
                        fabric("e", List.of("e1", "e2")),
                        fabric("r", List.of("r1"))
                )
        ).forEach(q -> log.info(q.getKey()+"  "+q.getValue().stream().map(qq -> " (" + qq.getKey() + "," + qq.getValue() + ") ").collect(Collectors.joining(" "))));

    }
    @Test
    void combinationsSliserUUIDTest(){
        Deque<Deque<Map.Entry<String, String>>> l = new LinkedList();
        l.addAll((List.of(
                fabric("a", List.of("a1")),
                fabric("q", List.of("q1", "q2")),
                fabric("w", List.of("w1", "w2")),
                fabric("e", List.of("e1", "e2", "e3", "e4")),
                fabric("r", List.of("r1"))
        )));

        combinationsSliserUUID(l
        ).forEach(q -> log.info(q.getKey()+"  "+q.getValue().entrySet().stream().map(qq -> " (" + qq.getKey() + "," + qq.getValue() + ") ").collect(Collectors.joining(" "))));

    }
    @Test
    void pairWiseListUUIDTest(){
        Deque<Deque<Map.Entry<String, String>>> ll = new LinkedList();
        ll.addAll((List.of(
                fabric("a", List.of("a1")),
                fabric("q", List.of("q1", "q2")),
                fabric("w", List.of("w1", "w2")),
                fabric("e", List.of("e1", "e2", "e3", "e4")),
                fabric("r", List.of("r1"))
        )));
        pairWiseListUUID(ll
        ).forEach(q -> log.info(q.getKey()+"  "+q.getValue().entrySet().stream().map(qq -> " (" + qq.getKey() + "," + qq.getValue() + ") ").collect(Collectors.joining(" "))));
    }
}
