package implemet;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.springframework.beans.factory.annotation.Autowired;

public class PF extends PageFactory{

    @Autowired
    static
    DF driverFactory;
    public static void initElements(WebDriver driver, Object page) {
        PageFactory.  initElements(new DefaultFieldDecorator( new DefaultElementLocatorFactory(driver)), page);


    }
    public static void initElements( Object page) {
        PageFactory.  initElements(new DefaultFieldDecorator( new DefaultElementLocatorFactory(driverFactory.getDriver())), page);


    }
}
