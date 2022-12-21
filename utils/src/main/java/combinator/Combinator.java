package combinator;


import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;


public class Combinator {
    public static void main(String[] args) {
        combinations(List.of(
                        fabric("a", List.of("a1")),
                        fabric("q", List.of("q1", "q2")),
                        fabric("w", List.of("w1", "w2", "w3")),
                        fabric("e", List.of("e1", "e2")),
                        fabric("r", List.of("r1"))
                )
        ).forEach(q -> System.out.println(q.entrySet().stream().map(qq -> " (" + qq.getKey() + "," + qq.getValue() + ") ").collect(Collectors.joining(" "))));
        System.out.println("");
        System.out.println("");
        System.out.println("");
        Deque<Deque<Map.Entry<String, String>>> l = new LinkedList();
        l.addAll((List.of(
                fabric("a", List.of("a1")),
                fabric("q", List.of("q1", "q2")),
                fabric("w", List.of("w1", "w2")),
                fabric("e", List.of("e1", "e2","e3","e4")),
                fabric("r", List.of("r1"))
        )));

        combinationsPairWise(l
        ).forEach(q -> System.out.println(q.entrySet().stream().map(qq -> " (" + qq.getKey() + "," + qq.getValue() + ") ").collect(Collectors.joining(" "))));
    }

    /**
     * образует из одного ключа и листа значений лист ентри
     *
     * @param key
     * @param values
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> LinkedList<Map.Entry<V, T>> fabric(V key, List<T> values) {
        return values.stream().map(q -> Map.entry(key, q)).collect(collectingAndThen(toList(), LinkedList::new));
    }

    /**
     * комбинирует мапу с новой парой значений
     *
     * @param entry  новая пара
     * @param values исходная мапа
     * @param <T>
     * @param <V>
     * @return создает лисит мап где ка каждому значению мапы добавлена новая пара и образована новая мапа
     */
    public static <T, V> List<Map<T, V>> addOneEntryToMap(Map.Entry<T, V> entry, Map<T, V> values) {
        return values.entrySet().stream().map(q -> Map.ofEntries(q, entry)).collect(toList());
    }


    public static <T, V> List<Map<T, V>> combinationsListEntriesToListMaps(List<Map.Entry<T, V>> first, List<Map<T, V>> last) {
        return first.stream().flatMap(q -> combinationsEntriToListMaps(q, last).stream()).collect(toList());
    }

    public static <T, V> List<Map<T, V>> combinationsEntriToListMaps(Map.Entry<T, V> entry, List<Map<T, V>> last) {
        return last.isEmpty() ? List.of(Map.ofEntries(entry)) : last.stream().map(q -> {
            Map<T, V> m = new HashMap<>();
            m.putAll(q);
            m.put(entry.getKey(), entry.getValue())
            ;
            return m;
        }).collect(toList());
    }

    /**
     * комбинирует в первоначальную мапу исходный список пар и новую пару
     *
     * @param entry  пара
     * @param values исходный спсок пар
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> List<Map<T, V>> addOneEntryToListMapEntry(Map.Entry<T, V> entry, List<Map.Entry<T, V>> values) {

        return values.stream().map(q -> Map.ofEntries(q, entry)).collect(toList());
    }

    /**
     * комбинирует два исходных списка
     *
     * @param first первый исходный список пар
     * @param last  второй исходный список пар
     * @param <T>
     * @param <V>
     * @return лис тмап из всех авриантов значений первого и второго списка
     */
    public static <T, V> List<Map<T, V>> combineListMapEntryToListMapEntry(List<Map.Entry<T, V>> first, List<Map.Entry<T, V>> last) {
        return first.stream().flatMap(q -> addOneEntryToListMapEntry(q, last).stream()).collect(toList());
    }

    public static <T, V> List<Map<T, V>> combinations(List<List<Map.Entry<T, V>>> first) {
        return first.isEmpty() ? Collections.EMPTY_LIST
                : first.size() == 1 ? Collections.singletonList(listEntriesToMap(first.get(0)))
                : first.size() == 2 ? combineListMapEntryToListMapEntry(first.get(0), first.get(1))
                : combinationsListEntriesToListMaps(first.get(0), combinations(first.subList(1, first.size())));

    }

    public static <T, V> List<Map<T, V>> combinationsPairWise(Deque<Deque<Map.Entry<T, V>>> first) {
        ;
        return pairWise(first, new LinkedList<>(), new ArrayList<Map<T, V>>(), new HashMap<T, V>());

    }

    public static <T, V> List<Map<T, V>> pairWise(Deque<Deque<Map.Entry<T, V>>> first, Deque<Deque<Map.Entry<T, V>>> last, List<Map<T, V>> acc, Map<T, V> map) {
        List<Map<T, V>> res = Collections.emptyList();
        if (first.isEmpty() && last.isEmpty()) {
            acc.add(map);
            res = acc;
        } else if (first.isEmpty()) {
            acc.add(map);
            res = pairWise(last, first, acc, new HashMap<>());
        } else {
            Deque<Map.Entry<T, V>> deq = first.pollFirst();
            Map.Entry<T, V> en = deq.pollFirst();
            map.put(en.getKey(), en.getValue());
            res = en == null ?
                    pairWise(first, last, acc, map) :
                    pairWise(first, addDeque(deq, last), acc, map)
            ;
        }
        return res;
    }

    public static <T> Deque<Deque<T>> addDeque(Deque<T> elem, Deque<Deque<T>> deque) {
        Deque<Deque<T>> d = new LinkedList();
        d.addAll(deque);
        if (elem != null && !elem.isEmpty()) {
            d.addLast(elem);
        }
        return d;

    }

    public static <T> List<T> addAllList(List<T> elem, List<T> deque) {
        List<T> d = new ArrayList<>();
        d.addAll(deque);
        d.addAll(elem);
        return d;

    }

    public static <T, V> Map<T, V> listEntriesToMap(List<Map.Entry<T, V>> list) {
        return list.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
