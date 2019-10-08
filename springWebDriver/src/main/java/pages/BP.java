package pages;

import implemet.DF;
import interfaces.BasePage;
import interfaces.DriverFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.SimpleLog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

//@Component
//@Scope("prototype")
class BP implements BasePage {
Log log=new SimpleLog("hgkh");
    protected WebDriver driver;

//    public BP(DriverFactory driverFactory) {
//        this.driver = driverFactory.getDriver();
//        PageFactory.initElements(new HtmlElementDecorator( new HtmlElementLocatorFactory(driver)), this);

//    }

    public void set(DriverFactory driverFactory) {
        this.driver = driverFactory.getDriver();
//        PageFactory.initElements(new HtmlElementDecorator( new HtmlElementLocatorFactory(driver)), this);
    }
//    @Value("DriverFactory")
//    public void init(DriverFactory driverFactory) {
//        this.driver = driverFactory.getDriver();
//        PageFactory.initElements(new HtmlElementDecorator( new HtmlElementLocatorFactory(driver)), this);
//    }
}
