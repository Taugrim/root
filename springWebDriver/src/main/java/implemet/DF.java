package implemet;

import interfaces.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("DF")
@Scope("singleton")
public class DF implements DriverFactory {
    WebDriver driver;
    public DF() {
    }

    public DF(String path) {
        this.path = path;
    }

    String path="C:\\gitTaugrim\\root\\springWebDriver\\src\\main\\resources\\chromedriver.exe";
    public WebDriver getDriver() {
//        FirefoxOptions options = new FirefoxOptions();
//        DesiredCapabilities d = new DesiredCapabilities();
//        d.setCapability("marionette", false);
//       d. setCapability(FirefoxDriver.BINARY, "C:\\gitTaugrim\\root\\springWebDriver\\src\\main\\resources\\FirefoxPortable\\FirefoxPortable.exe");
//        options.addPreference("marionette", false);
//        options.setBinary("C:\\gitTaugrim\\root\\springWebDriver\\src\\main\\resources\\FirefoxPortable\\FirefoxPortable.exe");
//        System.setProperty("webdriver.gecko.driver", "C:\\gitTaugrim\\root\\springWebDriver\\src\\main\\resources\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver",path );
//        WebDriver driver = new FirefoxDriver(d);
        WebDriver driver = new HtmlUnitDriver();
//        WebDriver driver = new ChromeDriver();
        this.driver=driver;
        return driver;
    }

    public By by(String x) {
        return By.xpath(x);
    }
//    public WebElement find(String x){
//        return driver.findElement(by(x));
//    }
    public By find(String x){
        return By.xpath(x);
    }
}
