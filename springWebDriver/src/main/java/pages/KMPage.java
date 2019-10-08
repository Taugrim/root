package pages;

import implemet.DF;
import interfaces.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("KMPage")
//@Scope("prototype")
//@Lazy
public class KMPage
        extends BP
{

    @Value("${url}")
    String url;

//    @Value("#{DriverFactory}")
//    DF df;
//    @PostConstruct
//    public void init(){
//        PageFactory.initElements(new DefaultFieldDecorator( new DefaultElementLocatorFactory(driver)), this);

//    }

//    @Lazy
//    @Value( "#{KMPage.driver.findElement(DriverFactory.by('.//*[@id='title_header']//*[@class='searchtag'])'))}")
//    @Value( "#{DriverFactory.find('.//div')}")
//    @Value( "#{KMPage.driver.findElement(DriverFactory.find('.//body'))}")
//    @Value( "#{KMPage.driver.findElement(By.xpath('.//*[@id='title_header']//*[@class='searchtag'])')}")
    @FindBy(xpath = ".//*[@id='title_header']//*[@class='searchtag']")
//  public WebDriver search;
  public   WebElement search;
//@Autowired

//    public KMPage(DriverFactory driverFactory) {
//        super(driverFactory);
//    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void get() {
//        driver.get(url);
//        System.out.println(driver.getPageSource());
        System.out.println("KM get");
    }
}
