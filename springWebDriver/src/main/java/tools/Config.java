package tools;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("Config")
public class Config {
    @Value("${url}")
 public    String url;
}
