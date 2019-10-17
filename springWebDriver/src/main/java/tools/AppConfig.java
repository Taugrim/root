package tools;

import aspects.AspectInit;
import implemet.DF;
import interfaces.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import pages.KMPage;
import sun.misc.Contended;

@Configuration
@PropertySource("classpath:prop.properties")
@ComponentScan({"implemet",
"pages","aspects"})
public class AppConfig {
    @Autowired
       KMPage km;
    @Autowired
    private Environment environment;
    @Bean
    public Config config(){
        Config c =new Config();
        c.url=environment.getProperty("url");
        return c;
    }
    @Qualifier("By")
    public By by;
//    @Bean
//    public WebDriver getDriver(){
//        return new DF().getDriver();
//    }
//    @Bean
//    @Qualifier("KMPage")
//    public KMPage getKMPage(){
//        return new KMPage();
////        return new KMPage(getDriverFactory ());
//    }
     @Bean
    public DriverFactory getDriverFactory(){
        return new DF();
    }
//    @Bean
//    public AspectInit getAspectInit(){
//        return new AspectInit();
//    }
}
