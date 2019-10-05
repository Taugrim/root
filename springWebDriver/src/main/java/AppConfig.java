import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:prop.properties")
public class AppConfig {
    @Autowired
    private Environment environment;
    @Bean
    public Config config(){
        Config c =new Config();
        c.url=environment.getProperty("url");
        return c;
    }
}
