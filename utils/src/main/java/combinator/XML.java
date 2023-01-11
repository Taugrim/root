package combinator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.lang3.builder.CompareToBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class XML {
    public static ObjectNode documentToJObjectNode(Document document){

        try {
            return new XmlMapper().readValue(getStringFromDocument(document).getBytes(),ObjectNode.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
        public static String jsonNodeToString(ObjectNode objectNode){

        try {
            return new JsonMapper().writeValueAsString(objectNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static Document jsonNodeToDocument(ObjectNode objectNode){

        try {
            return getDocument( new XmlMapper().writeValueAsString(objectNode));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * разделяеть xpath на имена нод работает на абсолютном и отностильном пути
     *
     * @param xpath
     * @return
     */
    public static List<Map.Entry<String, String>> splitXpath(String xpath) {
        return Stream.of(xpath.split("/")).filter(q -> !q.equals(""))
                .map(q -> Map.entry(xpath.substring(0, xpath.indexOf(q) - 1), xpath.substring(xpath.lastIndexOf(q))))
                .filter(q -> !q.getKey().equals(""))
                .collect(Collectors.toList());
    }
    public static String getTextNode(Document document, String param, int i) {
        return document.getDocumentElement().getElementsByTagName(param).getLength() == 0 ? null
                : document.getDocumentElement().getElementsByTagName(param).item(i).getTextContent().equals("") ? null
                : document.getDocumentElement().getElementsByTagName(param).item(i).getTextContent();
    }

    /**
     * создать копию документа
     *
     * @param document
     * @return
     */
    public static Document copyDocument(Document document) {
        Node originalRoot = document.getDocumentElement();
        Document copy = createDocument();
        Node copyRoot = copy.importNode(originalRoot, true);
        copy.appendChild(copyRoot);
        return copy;
    }

    public static Document createDocument() {
        return createDocumentBuilder().newDocument();
    }

    public static DocumentBuilder createDocumentBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        return builder;
    }

    /**
     * удаляет ноду
     *
     * @param document
     * @param xpath
     * @return
     */
    public static Document deleteNode(Document document, String xpath) {
        Document copy = copyDocument(document);
        Node node = getNodeByXpath(copy, xpath);
        if (node != null) {
            node.getParentNode().removeChild(node);
        }
        return copy;
    }


    public static String deleteNode(String document, String xpath) {
        return getStringFromDocument(deleteNode(getDocument(document), xpath));
    }

    public static Document deleteNodes(Document document, String xpath) {
        Document doc = document;
        while (getNodeByXpath(doc, xpath) != null)
            doc = deleteNode(doc, xpath);
        return doc;
    }

    public static Map<String, String> createFromRightSet(Document xml, BiFunction<Map.Entry<String, String>, Document, Map.Entry<String, String>> manipulator) {
        return getXpath3(xml).stream().map(q -> manipulator.apply(q, xml)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Node createNode(String name) {
        return (Node) createDocument().createElement(name.replaceAll("([\\[\\]\\(\\)/,])", "_").replaceAll("//", "_"));

    }

    public static Node createNode(String name, String value) {
        Node node = createNode(name);
        node.setTextContent(value);
        return node;
    }

    public static Node setAttribute(Node node, String nameA, String valueA) {
        ((Element) node).setAttribute(nameA, valueA);
        return node;
    }

    public static Document getDocumentRegexValue(Document doc) {
        Set<String> xpath = getXpath2(doc);
        for (String xp : xpath) {
            String node = getValueNodeByXpath(doc, xp);
            if (node == null) continue;
            doc = replaceValueTag(doc, xp, Generator.genRegex(node));
        }
        return doc;
    }

    public static Document getDocument(String xml) {
        Document document = null;
        try {
            document = xml == null ? null :
                    createDocumentBuilder().parse(new InputSource(new StringReader(xml)));
        } catch (Exception e) {
        }
        return document;
    }

    public static Document replaceValueTag(Document xml, String xpath, String value) {
        Document copy = copyDocument(xml);
        getNodesByXpath(copy, xpath).forEach(node -> node.setTextContent(value));
        return copy;
    }

    private static Node getNodeByXpath(Document copy, String xpath) {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath1 = xPathFactory.newXPath();
        XPathExpression expression = null;
        try {
            expression = xpath1.compile(xpath);
            return (Node) expression.evaluate(copy, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getValueNodeByXpath(Document document, String xpath) {
        Node node = getNodeByXpath(document, xpath);
        return node == null ? null : getValueNode(node);
    }

    private static String getValueNode(Node node) {
        return (!node.getTextContent().equals(
                !node.hasChildNodes() ? null :
                        node.getChildNodes().item(0).getNodeValue())) ? getStringfromChildsNode(node)
                : node.getTextContent();

    }

    private static String getStringfromChildsNode(Node node) {
        StringBuffer stringBuffer = new StringBuffer();
        getChildsNode(node).stream().forEach(q -> stringBuffer.append("<" + q.getNodeName() + ">" + getValueNode(q) + "<" + q.getNodeName() + "/>\n"));
        return stringBuffer.toString();
    }

    public static String getValueNodeEndByXpath(Document document, String xpath) {
        return getValueNodeEnd(getNodeByXpath(document, xpath));
    }

    private static String getValueNodeEnd(Node node) {
        return node != null ?
                node.hasChildNodes() ?
                        node.getChildNodes().item(0).getTextContent().equals(node.getTextContent()) ?
                                node.getTextContent()
                                : ""
                        : ""
                : "";
    }

    public static List<Node> getNodesByXpath(Document document, String xpath) {
        return filterByXpath(getXpath3(document), xpath).stream()
                .map(q -> getNodeByXpath(document, q.getKey())).collect(Collectors.toList());
    }

    public static List<Map.Entry<String, String>> filterByXpath(
            Set<Map.Entry<String, String>> xpathAndValue,
            String filterXpath
    ) {
        return
                filterByXpath(xpathAndValue.stream().collect(Collectors.toList()),
                        Stream.of(filterXpath.split("//"))
                                .flatMap(q->Stream.of(q.split("/"))).collect(Collectors.toList()));


    }
 public static List<Map.Entry<String, String>> filterByXpath(
            List<Map.Entry<String, String>> xpathAndValue,
            List<String> filterXpath
    ) {
        return filterXpath.size() == 0 ? xpathAndValue :
                filterByXpath(xpathAndValue.stream().filter(q -> q.getKey().contains(filterXpath.get(0))).collect(Collectors.toList()),
                        filterXpath.stream().skip(1).collect(Collectors.toList()));


    }

    public static Node getNodeByXpath(String response, String xpath) {
        return getNodeByXpath(getDocument(response), xpath);
    }

    public static Node addNodeToXpath(Document document, String xpath, String child) {
        return addNodeToNodes(getNodeByXpath(document, xpath), createNodes(sortSiblings(xpath, namesBuIndex(child), document)));
    }

    private static List<Node> createNodes(List<String> names) {
        return names.stream().map(q->(Node)createDocument().createElement(q)).collect(Collectors.toList());
    }

    public static List<String> sortSiblings(String xpath, List<String> names, Document document) {
        return IntStream.range(1, names.size())
                .filter(i -> getNodeByXpath(document, xpath + "/" + names.get(i - 1) + "[" + i + "]") != null)
                .mapToObj(i -> names.get(i - 1))
                .collect(Collectors.toList());
    }

    public static List<String> namesBuIndex(String name) {
        String patternIndexName = "([a-z,A-Z]*)\\[(\\d)\\]";
        return name.matches(patternIndexName) ?
                IntStream.of(1, Integer.parseInt(name.replaceAll(patternIndexName, "$2")))
                        .mapToObj(q -> name.replaceAll(patternIndexName, "$1")).collect(Collectors.toList()) :
                Collections.singletonList(name.replaceAll(patternIndexName, "$1"));
    }

    public static Document createNodeByXpathToDocument(Document document, List<String> listXpath, String root) {
        return listXpath.size() == 0 ? document :
                getNodeByXpath(document, root + "/" + listXpath.get(0)) == null ?
                        createNodeByXpathToDocument(
                                addNodeToXpath(document, root, listXpath.get(0)).getOwnerDocument(),
                                listXpath.subList(1, listXpath.size()), root + "/" + listXpath.get(0)) :
                        createNodeByXpathToDocument(document, listXpath.subList(1, listXpath.size()), root + "/" + listXpath.get(0));
    }

    public static <T, V> Document createNodeByXpathToDocument(Document doc, Map.Entry<String, String> xpathValue) {
        Document document = copyDocument(doc);
        List<Map.Entry<String, String>> listXpath = splitXpath(xpathValue.getKey());
        List<Map.Entry<String, String>> listXpathExist = splitXpath(xpathValue.getKey());
        listXpath.stream()
                .filter(q -> getNodeByXpath(document, q.getKey()) != null).collect(Collectors.toList());
        List<Map.Entry<String, String>> listXpathNoExist = splitXpath(xpathValue.getKey());
        listXpath.stream()
                .filter(q -> getNodeByXpath(document, q.getKey()) == null).collect(Collectors.toList());
        Comparator<Map.Entry<String, String>> comparator = (q1, q2) ->
                (new CompareToBuilder()).append(q1.getKey(), q2.getKey()).append(q1.getValue(), q2.getValue()).toComparison();
        listXpathExist.sort(comparator);
        listXpathNoExist.sort(comparator);
        Collections.reverse(listXpathExist);
        Node nodeRoot = listXpathExist.size() == 0 ? document.getFirstChild() :
                getNodeByXpath(document, listXpathNoExist.get(0).getKey());
        List<Node> nodeList = listXpathNoExist.size() == 0 ? null : Stream.of(
                        listXpathExist.get(0).getValue().split("/")).filter(q -> !q.equals("/")).map(q -> createNode(q))
                .collect(Collectors.toList());

        if (nodeList != null) {
            nodeList.add(0, createNode(listXpathNoExist.get(0).getKey().substring(listXpathNoExist.get(0).getKey().lastIndexOf("/") + 1)));
        }
        return listXpathNoExist.size() == 0 ? document : addChildsToNode(nodeRoot, nodeList).getOwnerDocument();
    }

    private static Node addChildsToNode(Node nodeRoot, List<Node> nodeList) {
        return nodeList.size() == 0 ? nodeRoot :
                nodeList.size() == 1 ? addNodeToNode(nodeRoot, nodeList.get(0)) :
                        addChildsToNode(addNodeToNode(nodeRoot, nodeList.get(0)), nodeList.subList(1, nodeList.size()));
    }


    private static Node addNodeToNode(Node nodeRoot, Node node) {
        nodeRoot.getOwnerDocument().adoptNode(node);
        nodeRoot.appendChild(node);
        return nodeRoot;
    }

    private static Node addNodeToNodes(Node nodeRoot, List<Node> node) {
        node.forEach(q -> addNodeToNode(nodeRoot, q));
        return nodeRoot;
    }

    public static String getStringFromDocument(Document document) {
        StreamResult result = null;
        try {
            Transformer transfomer = TransformerFactory.newInstance().newTransformer();
            result = new StreamResult(new StringWriter());
            DOMSource source = new DOMSource(document);
            transfomer.transform(source, result);
        } catch (Exception e) {
        }
        return result.getWriter().toString();
    }

    public static List<Node> getChildsNode(Node node) {
        NodeList nl = node.getChildNodes();
        return IntStream.range(0, nl.getLength() - 1).mapToObj(q -> nl.item(q))
                .filter(q -> !q.getNodeName().equals("#text"))
                .collect(Collectors.toList());
    }

    static List<String> prepereListXpath(String root, Iterator<Map.Entry<String, JsonNode>> iterator) {
        List<String> lis = new ArrayList<>();
        if (!iterator.hasNext()) lis.add(root);
        while (iterator.hasNext()) {
            Map.Entry<String, JsonNode> it = iterator.next();
            ;
            if (it.getValue().isArray())
                lis.addAll(prepereListXpath(root + "//" + it.getKey(), ((ArrayNode) it.getValue()).fields()));
            lis.addAll(prepereListXpath(root + "//" + it.getKey(), it.getValue().fields()));

        }
        return lis;
    }

    public static Set<Map.Entry<String, String>> getXpath3(Node xml) {
        return xml == null ? Collections.emptySet() : xml.hasChildNodes() ?
                IntStream.range(0, xml.getChildNodes().getLength())
                        .mapToObj(q -> xml.getChildNodes().item(q))
                        .map(q -> getXpath3(q))
                        .flatMap(q -> q.stream()).collect(Collectors.toSet())
                : Collections.emptySet();
    }

    public static Set<String> getXpath2(Node xml) {
        return xml.hasChildNodes() ?
                IntStream.range(0, xml.getChildNodes().getLength())
                        .mapToObj(q -> xml.getChildNodes().item(q))
                        .map(q -> getXpath2(q))
                        .flatMap(q -> q.stream()).collect(Collectors.toSet())
                : Collections.emptySet();
    }

    public static String prepare(Node xml) {
        return (xml.getNodeName().equals("#document") ? "" :
                prepare(xml.getParentNode()) + "//" + xml.getNodeName())
                .replaceAll("//.{1,3}:", "//")
                .replaceAll("//#.{0,}", "");
    }

    public static String prepare2(Node xml) {
        return (xml.getNodeName().equals("#document") ? "" :
                prepare2(xml.getParentNode()) + "//" + xml.getNodeName() + getNodeNum(xml))
                .replaceAll("//.{1,3}:", "//")
                .replaceAll("//#.{0,}", "");
    }

    public static Map.Entry<String, String> prepare3(Node xml) {
        return xml.getNodeName().equals("#document") ? Map.entry("", xml.getNodeValue()) : Map.entry(
                prepare2(xml.getParentNode()) + "//" + xml.getNodeName() + getNodeNum(xml)
                        .replaceAll("//.{1,3}:", "//")
                        .replaceAll("//#.{0,}", ""), xml.getTextContent().replaceAll("(\n *)*", ""));
    }

    private static String getNodeNum(Node node) {
        List<Node> nodeList = IntStream.range(0, node.getParentNode().getChildNodes().getLength())
                .mapToObj(i -> node.getParentNode().getChildNodes().item(i))
                .filter(i -> i.getNodeName().equals(node.getNodeName())).collect(Collectors.toList());
        int num = IntStream.range(0, nodeList.size())
                .filter(i -> nodeList.get(i).equals(node))
                .findFirst().orElse(0);
        return String.format("[%s]", num + 1);
    }
}