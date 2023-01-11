package combinator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static combinator.Combinator.fabric;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class ListComprehensionTest {

    @Test
    void of() {
       var l= ListComprehension.of(
                List::of,
               ()->List.of(1,2,3),
               ()->List.of(4,5,6)
        );
       l.forEach(q->log.info(String.valueOf(q)));
    }
}