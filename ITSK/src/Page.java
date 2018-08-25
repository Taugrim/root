import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class Page {

    private WebDriver driver;


    @FindBy(css = "div.header__menu__link")
    WebElement menu;

    @FindBy(css = ".menu-mobile__list")
    MenuContainer menuContainer;

    @FindBy(xpath = ".//*[@class='azs-filter__item']/label[text()='Топливо']/ ancestor::div[@class='azs-filter__item']")
    AzsFiltrContainer fuel;

    @FindBy(xpath = ".//*[@class='azs-filter__item']/label[text()='Услуги']/ ancestor::div[@class='azs-filter__item']")
    AzsFiltrContainer uslugi;
    @FindBy(css = "#yandex_map")
    MapContainer mapContainer;
    public void clickItem(AzsFiltrContainer f,String item)  {


        JavascriptExecutor js = (JavascriptExecutor)driver;

        try {
            js.executeScript("arguments[0].click();", f.getItem(item));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Page(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator( new HtmlElementLocatorFactory(driver)), this);
        this.driver = driver;
    }

}
