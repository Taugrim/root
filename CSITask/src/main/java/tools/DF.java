package tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;


public class DF
{
    static WebDriver driver;
    public DF() {
    }


    public static WebDriver getDriver() {
        System.setProperty("webdriver.gecko.driver","/usr/bin/geckodriver");
        driver = driver==null?new FirefoxDriver():driver;
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        return driver;
    }

}
