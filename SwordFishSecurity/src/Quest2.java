import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * код более читаем
 * прменение стрингбилдера  ускоряет гененрацию строки
 * статические данные вынесены из кода
 * раширять номенклатуру штатов можно ,не копаясь в коде
 * повышена абстракция кода
 * если есть причина не использовать инструменты для генерации HTML/XML
 * такой прием можно применять к генерации остальных частяй страницы
 */
public class Quest2 {
    static FileInputStream streamState;
    static FileInputStream streamTag;
    static Properties propState;
    static Properties propTag;

    static Map<String, String> vocabular = new HashMap();
    static Map<String, String> mapTag = new HashMap();

    //шаблоны тегов
    static String root ;
    static String item ;
    static BinaryOperator rootTag = (form, val) -> String.format(String.valueOf(form), val);
    static BinaryOperator itemTag = (form, val) -> String.format(String.valueOf(form), val, val);
    /**
     * добавлены для удобства
     */
    static UnaryOperator format = (str) -> rootTag.apply(root, str);
    static UnaryOperator formatItem = (str) -> itemTag.apply(item, str);
    //можно перенести ресурсы в другое место
    static String path = "src\\source\\";

    public  static void ini() throws IOException {
        streamState = new FileInputStream(path + "state.properties");
        streamTag = new FileInputStream(path + "tags.properties");
        propState = new Properties();
        propTag = new Properties();
        propState.load(streamState);
        propTag.load(streamTag);
        propState.entrySet().stream().forEach(q -> vocabular.put((String) q.getKey(), (String) q.getValue()));
        propTag.entrySet().stream().forEach(q -> mapTag.put((String) q.getKey(), (String) q.getValue()));
        root = mapTag.get("root");
        item = mapTag.get("item");
    }


    public static String createStateSelectList() {
        StringBuilder str = new StringBuilder();
        vocabular.keySet().stream().forEach(q -> str.append(formatItem.apply(q)));
        return (String) format.apply(new String(str));
    }

    public static String parseSelectedState(String s) {
        return vocabular.get(s);
    }

    public static String displayStateFullName(String abbr) {
        return vocabular.entrySet().stream().filter(q -> q.getValue().equals(abbr)).findFirst().get().getKey();
    }

    static void streams(List s){
        s.stream().forEach(q -> System.out.println(q));
    }
    public static void main(String[] args) {
        long start = System.nanoTime();


        try {
            ini();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(createStateSelectList());
        streams( vocabular.keySet().stream().map(q -> parseSelectedState(q)).collect(Collectors.toList()));

        streams(vocabular.values().stream().map(q -> displayStateFullName(q)).collect(Collectors.toList()));
//сравниваю быстродействие с xml
        long finish = System.nanoTime();
        long fin = 75025753;
        long timeConsumedMillis = finish - start;
        System.out.println(timeConsumedMillis);
    }
}
