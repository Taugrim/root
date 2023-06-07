package combinator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import types.TypeValues;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static combinator.Combinator.*;
import static combinator.XML.*;
import static combinator.XML.readFromInputStream;
@Slf4j
public class integrationTests {
    @Test
    public void test() throws IOException {
        List<Map.Entry<UUID, List<Map.Entry<String, TypeValues>>>> list= combinationsUUID(List.of(
                        fabricList(getXpath2(getDocument(getXmlSimple())),List.of(
//                                TypeValues.LOGICBREAK
//                                ,TypeValues.BREAK
                                TypeValues.EMPTY
//                                ,TypeValues.NULL
//                                ,TypeValues.SELPH
//                                ,TypeValues.DEL
                        ))

                )
        );
//                .forEach(q -> log.info(q.toString()));
        list.forEach(q->log.info(q.getKey()+"  "+q.getValue()));
        replaceValuesTagsUUID(getDocument(getXmlSimple()),list
        ).forEach(q -> log.info(q.getKey() + "  " + getStringFromDocument(q.getValue())));
    }
    static String getXml() throws IOException {
        return readFromInputStream("/home/q/Документы/git/utils/src/test/resources/xml.xml");

    }
    static String getXmlSimple() throws IOException {
        return readFromInputStream("/home/q/Документы/git/utils/src/test/resources/simple.xml");

    }
    static String getXml2() throws IOException {
        return readFromInputStream("/home/q/Документы/git/utils/src/test/resources/xml2.xml");

    }

}
