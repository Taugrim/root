import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Quest3 {
    static int maxCharsPerLine = 6;
    static String str = "123456 7890\n\n \n \n123 456789.";
    static UnaryOperator builder = (s) -> {
        StringBuilder strB = new StringBuilder();
        ((List) s).stream()
                .forEach(q -> strB.append(q + "\n"));
        int i = strB.length();
        strB.delete(i - 1, i);
        return strB.toString();
    };
    static BinaryOperator replaser = (s,max) -> {
        StringBuilder sb = new StringBuilder((String) s);
        int sd = ((String) s).length();
        for (int i = (int) max; i < sd; i = i + (int) max + 1, sd++) {
            if (sb.charAt(i) == ' ') sb.replace(i, i , "\n");
            else sb.insert(i, "\n");

        }
        return (sb).toString();
    };
    static UnaryOperator formatter = (f) -> {
        return
//                ((String) rootTag).length() >= maxCharsPerLine ?
                        ((String) f)
                .replaceAll(" {2,}", " ")
                .replaceAll(" \n", "\n")
                .replaceAll("\n {2,}", "\n")
                .replaceAll("\\A", "")
                .replaceAll(" \\Z", "")
                .replaceAll(" \\$", "")
                .replaceAll(" \\b", "")
                .replaceAll("\\b ", "")
                .trim();
//                : (String) ((String) rootTag).trim();
    };
    static UnaryOperator splitter = (string) -> {
      List a= new ArrayList<>();
      a.addAll(Arrays.asList(((String) string).split("\n")));
      return a;
    };
    static BinaryOperator strimmer = (list,max) -> {
      return  (  (List) list).stream().map(q -> replaser.apply(q,max)).collect(Collectors.toList());
    };
    public static String wrapText (String string, int maxCharsPerLine){
        List v = (List) strimmer.apply((splitter.compose(formatter)).apply(string),maxCharsPerLine);
        v.forEach(q -> formatter.apply(q));
        return (String) builder.apply(v);
    }

    public static void main(String[] args) {

        System.out.println(wrapText(str,maxCharsPerLine))

        ;






    }
}
