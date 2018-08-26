import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * код более читаем
 * применеый специализированные инструменты
 * статические данные вынесены из кода
 * раширять номенклатуру штатов можно ,не копаясь в коде
 * повышена абстракция кода
 */
public class Quest2XML {
    static FileInputStream streamState;
    static String path = "src\\source\\";
    static Properties propState;
    static Map<String, String> vocabular = new HashMap();
    static DocumentBuilderFactory factory;
    static Document doc;
    static StringWriter writer;
    static Transformer transformer;
    static Element root;
    static List<Element> items;
    static UnaryOperator genItem = (state) -> {
        Element item = doc.createElement((String) state);
        item.setAttribute("value", (String) state);
        return item;};

    static void ini() throws ParserConfigurationException, TransformerConfigurationException, IOException {
        factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        writer = new StringWriter();
        doc = factory.newDocumentBuilder().newDocument();
        root = doc.createElement("select");
        root.setAttribute("name", "state");
        doc.appendChild(root);

        transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        items=new ArrayList<>();
        streamState = new FileInputStream(path + "state.properties");
        propState = new Properties();
        propState.load(streamState);
        propState.entrySet().stream().forEach(q -> vocabular.put((String) q.getKey(), (String) q.getValue()));
    }
    public static String createStateSelectList() throws TransformerException {
        vocabular.keySet().stream().forEach(q ->root.appendChild((Node) genItem.apply(q)) );
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
       return writer.toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>","").trim();
    }

    public static String parseSelectedState(String s) {
        return vocabular.get(s);
    }

    public static String displayStateFullName(String abbr) {
        return vocabular.entrySet().stream().filter(q -> q.getValue().equals(abbr)).findFirst().get().getKey();
    }
    static void strims(List s){
               s.stream().forEach(q -> System.out.println(q));
    }
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException {
        long start = System.nanoTime();
        ini();
        System.out.println(createStateSelectList());
        strims( vocabular.keySet().stream().map(q -> parseSelectedState(q)).collect(Collectors.toList()));

        strims(vocabular.values().stream().map(q -> displayStateFullName(q)).collect(Collectors.toList()));
//сравниваю быстродействие со стримами
        long finish = System.nanoTime();
        long fin = 75025753;
        long timeConsumedMillis = finish - start;

        System.out.println(fin-timeConsumedMillis);
    }

}
