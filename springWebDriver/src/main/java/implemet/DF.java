package implemet;

import interfaces.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DF implements DriverFactory {
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
        return driver;
    }
}
