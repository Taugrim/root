import combinator.XML;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static combinator.XML.*;

@Slf4j
public class XMLTest {
    @Test
    void readToXml() throws IOException {
        log.info(getXml());
//        log.info(FileUtils.readFileToString(new File(getClass().getClassLoader().getResource("xml.xml").getFile()), "UTF-8"));
    }

    @Test
    void getDocumentTest() throws IOException {
        log.info(getStringFromDocument(getDocument(getXml())));
    }

    @Test
    void getXpath3DocumentTest() throws IOException {
        getXpath3(getDocument(getXml())).forEach(q -> log.info(q.getKey()+"  "+q.getValue()));
    }
 @Test
    void getXpath2DocumentTest() throws IOException {
        getXpath2(getDocument(getXml())).forEach(q -> log.info(q));
    }
   @Test
    void getXpath3DocumentUUIDTest() throws IOException {
        getXpath3UUID(getDocument(getXml())).forEach(q -> log.info(q.getKey()+"  "+q.getValue()));
    }
 @Test
    void getXpath2DocumentUUIDTest() throws IOException {
        getXpath2UUID(getDocument(getXml())).forEach(q -> log.info(q.getKey()+"  "+q.getValue()));
    }
 @Test
    void getDocumentRegexValueTest() throws IOException {
     log.info(getStringFromDocument(getDocumentRegexValue(getDocument(getXml()))));
    }

    static String getXml() throws IOException {
        return readFromInputStream("/home/q/Документы/git/utils/src/test/resources/xml.xml");

    }
}
