package pages;

import interfaces.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
@Component
@Scope("prototype")
public class KMPage extends BP {

    @Value("${url}")
    String url ;
//    String url = "https://www.km.ru";
    @FindBy(xpath = ".//*[@id='title_header']//*[@class='searchtag']")
    HtmlElement search;

    public KMPage(DriverFactory driverFactory) {
        super(driverFactory);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void get() {
        driver.get(url);
        System.out.println(driver.getPageSource());
    }
}
