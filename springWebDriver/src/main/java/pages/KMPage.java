package pages;

import interfaces.DriverFactory;
import org.openqa.selenium.WebDriver;

public class KMPage extends BP{
    String url="https://www.km.ru";

    public KMPage(DriverFactory driverFactory) {
        super(driverFactory);
    }
    public  void get(){
        driver.get(url);
    }
}
