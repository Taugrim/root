import com.gargoylesoftware.htmlunit.WebClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import tools.DF;

public class Main {
    public static void main(String[] args) {
        WebDriver wd=DF.getDriver();
//        wd.get("km.ru");

        wd.get("https://www.rambler.ru/");
        wd.findElement(By.xpath(".//*[@name=\"email\"]")).sendKeys("");
        wd.findElement(By.xpath(".//*[@data-cerber-head=\"logbtn\"]")).click();
        wd.findElement(By.xpath(".//*[@name=\"password\"]")).sendKeys("");
        wd.findElement(By.xpath(".//*[@data-cerber-head=\"pasbtn\"]")).click();

        wd.findElement(By.xpath(".//*[@data-cerber-head=\"main::mail::w::inbox\"]")).click();


        //list mails //*[@class='MailList-list-2L']//*[@draggable]"
        //checkbox //*[@class='MailListItem-checkbox-1q']
        //star //*[@class='MailListItem-star-Sl']
        //sender //*[@class='MailListItem-star-Sl'] @title
        //snipet //*[@class='MailListItem-snippet-25'] @title
        //snipet //*[@class='MailListItem-date-hq'] @title



        // user //*[@data-cerber-topline='user::id']
        // user data-cerber-topline="user::id::logout"
//        wd.get("mockPages/home.html");
        System.out.println(wd.getPageSource());
    }
}
