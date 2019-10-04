package pages;

import interfaces.BasePage;
import interfaces.DriverFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.SimpleLog;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

class BP implements BasePage {
Log log=new SimpleLog("hgkh");
    protected WebDriver driver;

    public BP(DriverFactory driverFactory) {
        this.driver = driverFactory.getDriver();
//        PageFactory.initElements(new HtmlElementDecorator( new HtmlElementLocatorFactory(driver)), this);

    }

    public void set(DriverFactory driverFactory) {
        this.driver = driverFactory.getDriver();
//        PageFactory.initElements(new HtmlElementDecorator( new HtmlElementLocatorFactory(driver)), this);
    }
}
