import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

@Slf4j
public class XMLTest {
    @Test
    void readToDocument() throws IOException {
        log.info(FileUtils.readFileToString(new File("/home/q/Документы/git/utils/src/test/resources/xml.xml"), "UTF-8"));
//        log.info(FileUtils.readFileToString(new File(getClass().getClassLoader().getResource("xml.xml").getFile()), "UTF-8"));
    }
}
