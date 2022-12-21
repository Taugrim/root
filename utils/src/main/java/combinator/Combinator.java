package combinator;


import java.util.*;
import java.util.stream.Collectors;


public class Combinator {
    public static void main(String[] args) {
        combineListMapEntryes(  List.of(
                        fabric("a",List.of("a1"))     ,
                        fabric("q",List.of("q1","q2"))     ,
                        fabric("w",List.of("w1","w2","w3")),
                        fabric("e",List.of("e1","e2"))     ,
                        fabric("r",List.of("r1"))
        )
        ).forEach(q->System.out.println(q.entrySet().stream().map(qq->" ("+qq.getKey()+","+qq.getValue()+") ").collect(Collectors.joining(" "))));
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
    public static <T, V> List<Map.Entry<V, T>> fabric(V key, List<T> values) {
        return values.stream().map(q -> Map.entry(key, q)).collect(Collectors.toList());
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
        return values.entrySet().stream().map(q -> Map.ofEntries(q, entry)).collect(Collectors.toList());
    }


    public static <T, V> List<Map<T, V>> combinationsListEntriesToListMaps(List<Map.Entry<T, V>> first, List<Map<T, V>> last) {
        return first.stream().flatMap(q->combinationsEntriToListMaps(q,last).stream()).collect(Collectors.toList());
    }

    public static <T, V> List<Map<T, V>> combinationsEntriToListMaps(Map.Entry<T,V> entry, List<Map<T,V>> last) {
        return last.stream().map(q->{
            Map<T,V> m=new HashMap<>();
            m.putAll(q);
            m.put(entry.getKey(),entry.getValue())
            ;return m;}).collect(Collectors.toList());
    }

    /**
     * комбинирует в первоначальную мапу исходный список пар и новую пару
     *
     * @param entry пара
     * @param values исходный спсок пар
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> List<Map<T, V>> addOneEntryToListMapEntry(Map.Entry<T, V> entry, List<Map.Entry<T, V>> values) {

        return values.stream().map(q -> Map.ofEntries(q, entry)).collect(Collectors.toList());
    }

    /**
     * комбинирует два исходных списка
     * @param first первый исходный список пар
     * @param last второй исходный список пар
     * @return лис тмап из всех авриантов значений первого и второго списка
     * @param <T>
     * @param <V>
     */
    public static <T, V> List<Map<T, V>> combineListMapEntryToListMapEntry(List<Map.Entry<T, V>> first, List<Map.Entry<T, V>> last) {
        return first.stream().flatMap(q -> addOneEntryToListMapEntry(q, last).stream()).collect(Collectors.toList());
    }

    public static <T, V> List<Map<T, V>> combineListMapEntryes(List<List<Map.Entry<T, V>>> first) {
        return first.isEmpty()? Collections.EMPTY_LIST
                :first.size()==1?Collections.singletonList(listEntriesToMap(first.get(0)))
                :first.size()==2?combineListMapEntryToListMapEntry(first.get(0),first.get(1))
                :combinationsListEntriesToListMaps(first.get(0),combineListMapEntryes(first.subList(1,first.size())));

    }
    public static <T, V> Map<T, V> listEntriesToMap(List<Map.Entry<T, V>> list) {
        return  list.stream().collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }
}
