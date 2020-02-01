package tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class DF
{
    WebDriver driver;
    public DF() {
    }


    public static WebDriver getDriver() {
        System.setProperty("webdriver.gecko.driver","/usr/bin/geckodriver");


        WebDriver driver = new FirefoxDriver();
        return driver;
    }

}
