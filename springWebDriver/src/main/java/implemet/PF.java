package implemet;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class PF extends PageFactory{

    @Autowired
    static
    DF driverFactory;
    public static void initElements(WebDriver driver, Object page) {
        HtmlElementLoader.populatePageObject(page, driver);
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driverFactory.getDriver())), page);
//        PageFactory.  initElements(new DefaultFieldDecorator( new DefaultElementLocatorFactory(driverFactory.getDriver())), page);


    }
    public static void initElements( Object page) {
//        HtmlElementLoader.populatePageObject(page, driverFactory.getDriver());
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driverFactory.getDriver())), page);
//        PageFactory.  initElements(new DefaultFieldDecorator( new DefaultElementLocatorFactory(driverFactory.getDriver())), page);


    }
}
