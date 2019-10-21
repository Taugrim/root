package pages;

import implemet.DF;
import interfaces.DriverFactory;
import interfaces.Post;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import tools.Config;

import javax.annotation.PostConstruct;

@Component("KMPage")
//@Scope("prototype")
@Lazy
public class KMPage extends BP {
    @Value("${url}")
    String url;
    @Lazy
    @FindBy(xpath = ".//*[@id='title_header']//*[@class='searchtag']")
    public WebElement search;
    public void setUrl(String url) {
        this.url = url;
    }

    public void get() {
        driver.get(url);
//        System.out.println(driver.getPageSource());
        System.out.println("KM get");
    }
//    @Post
//    public void init(){}
}
