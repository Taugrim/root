package tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DF
{
    static WebDriver driver;
    public DF() {
    }


    public static WebDriver getDriver() {
        System.setProperty("webdriver.gecko.driver","/usr/bin/geckodriver");
        driver = driver==null?new FirefoxDriver():driver;
        return driver;
    }

}
