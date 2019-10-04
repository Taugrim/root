package pages;

import interfaces.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class KMPage extends BP {
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
