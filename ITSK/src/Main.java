import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "resource\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://opti-24.com");
        Page page = new Page(driver);
        page.menu.click();
        page.menuContainer.getItem("Сеть АЗС").click();
        page.clickItem(page.fuel, ("G-98"));
        page.clickItem(page.uslugi, ("AdBlue фасованный"));
        System.out.println(  page.mapContainer.getValue());
        System.out.println(page.mapContainer.getQvantity());

    }
}
