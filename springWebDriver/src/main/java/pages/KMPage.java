package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import tools.Config;

import javax.annotation.PostConstruct;

@Component("KMPage")
//@Scope("prototype")
@Lazy
public class KMPage extends BP {
//    public KMPage() {
//        HtmlElementLoader.populatePageObject(this, driverFactory.getDriver());
//    }

    @Value("${url}")
    String url;
    @Lazy
    @FindBy(xpath = ".//*[@id='title_header']//*[@class='searchtag']")
    public TextInput search;
    public void setUrl(String url) {
        this.url = url;
    }

    public void get() {
        log.info("construct KMPage");
        driver.get(url);
//        System.out.println(driver.getPageSource());
        System.out.println("KM get");
    }
}
