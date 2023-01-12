package combinator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import types.Diffs;
import types.TypeValues;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static combinator.Combinator.combinationsUUID;
import static combinator.Combinator.fabric;
import static combinator.XML.*;

@Slf4j
public class XMLTest {
    String etalon="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
            "<q>\n" +
            "    <w>\n" +
            "        <e>1</e>\n" +
            "    </w>\n" +
            "    <r/>\n" +
            "    <t1>2</t1>\n" +
            "    <t2>3</t2>\n" +
            "    <y>\n" +
            "        <u1></u1>\n" +
            "        <u2>4</u2>\n" +
            "    </y>\n" +
            "</q>";
    @Test
    void readToXml() throws IOException {
        Assertions.assertEquals(etalon,getXml());
    }

    @Test
    void getDocumentTest() throws IOException {
        log.info(getStringFromDocument(getDocument(getXml())));
    }

    @Test
    void getXpath3DocumentTest() throws IOException {
        getXpath3(getDocument(getXml())).forEach(q -> log.info(q.getKey() + "  " + q.getValue()));
    }

    @Test
    void getXpath2DocumentTest() throws IOException {
        getXpath2(getDocument(getXml())).forEach(q -> log.info(q));
    }

    @Test
    void getXpath3DocumentUUIDTest() throws IOException {
        getXpath3UUID(getDocument(getXml())).forEach(q -> log.info(q.getKey() + "  " + q.getValue()));
    }

    @Test
    void getXpath2DocumentUUIDTest() throws IOException {
        getXpath2UUID(getDocument(getXml())).forEach(q -> log.info(q.getKey() + "  " + q.getValue()));
    }

    @Test
    void getDocumentRegexValueTest() throws IOException {
        log.info(getStringFromDocument(getDocumentRegexValue(getDocument(getXml()))));
    }
    @Test
    void replaceValuesTagsUUIDTest() throws IOException {
        List<Map.Entry<UUID, List<Map.Entry<String, String>>>> list= combinationsUUID(List.of(
                        fabric("/q[1]//t2[1]", List.of(
                                "TEST1",
                                "TEST2",
                                "TEST3",
                                "TEST4"
                        ))

                ));
        list.forEach(q->log.info(q.getKey()+"  "+q.getValue()));
        replaceValuesTagsUUID(getDocument(getXml()),list
                ).forEach(q -> log.info(q.getKey() + "  " + getStringFromDocument(q.getValue())));
    }
  @Test
    void replaceValuesTagsUUIDTYPESTest() throws IOException {
        List<Map.Entry<UUID, List<Map.Entry<String, TypeValues>>>> list= combinationsUUID(List.of(
                        fabric("/q[1]//t4[1]", List.of(
                              TypeValues.LOGICBREAK,
                                TypeValues.BREAK,
                                TypeValues.EMPTY,
                                TypeValues.NULL,
                                TypeValues.SELPH,
                                TypeValues.DEL
                                ))

                ));
        list.forEach(q->log.info(q.getKey()+"  "+q.getValue()));
        replaceValuesTagsUUID(getDocument(getXml()),list
                ).forEach(q -> log.info(q.getKey() + "  " + getStringFromDocument(q.getValue())));
    }

    static String getXml() throws IOException {
        return readFromInputStream("/home/q/Документы/git/utils/src/test/resources/xml.xml");

    }
static String getXml2() throws IOException {
        return readFromInputStream("/home/q/Документы/git/utils/src/test/resources/xml2.xml");

    }

    @Test
    void diffTest() throws IOException {
        Map<Diffs, Map<String, Map.Entry<String, String>>> res=
                diff(getDocument(getXml()),getDocument(getXml2()), q->!q.getKey().equals("//q[1]//y[1]//u1[1]"));
        res.get(Diffs.NEW).entrySet().forEach(q->log.info("NEW ={}",q.getKey()+"  "+q.getValue().getKey()+"  "+q.getValue().getValue()));
        res.get(Diffs.MERGE).entrySet().forEach(q->log.info("MERGE ={}",q.getKey()+"  "+q.getValue().getKey()+"  "+q.getValue().getValue()));
        res.get(Diffs.OLD).entrySet().forEach(q->log.info("OLD ={}",q.getKey()+"  "+q.getValue().getKey()+"  "+q.getValue().getValue()));
    }
}
