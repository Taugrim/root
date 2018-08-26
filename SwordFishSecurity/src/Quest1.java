import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Quest1 {
    static Predicate<Set> pred=(colect)->colect.size()==1;
    public static void main(String[] args) {
        System.out.println( allStringSetsIdentical(new  String[][]{{"a", "b"},{"b", "a"},{"a", "b", "a"}}));
        System.out.println(allStringSetsIdentical(new String[][] {{"a","b"},{"b","b","a"},{"b","a"}}));
        System.out.println(allStringSetsIdentical(new String[][] {{"a","b"},{"a"},{"b"}}) );
        System.out.println(allStringSetsIdentical(new String[][] {{""},{"","",""}}) );

    }
    private static boolean allStringSetsIdentical(String[][] sets) {
      return pred.test(Stream.of(sets).map(w->new HashSet(Arrays.asList(w))).collect(Collectors.toSet()));}

}
