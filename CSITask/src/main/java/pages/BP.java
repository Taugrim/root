package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;
import tools.DF;


class BP  {

    private WebDriver driver;
    public BP() {
        init();
        HtmlElementLoader.populatePageObject(this, driver);
    }


    DF driverFactory;

    public void init(){
        this.driver = driverFactory.getDriver();

    }
}
