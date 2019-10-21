package pages;

import implemet.DF;
import implemet.PF;
import interfaces.BasePage;
import interfaces.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
//@Scope("prototype")
class BP implements BasePage {
    Logger log;

    public BP() {
        log=  LogManager.getLogger(this.getClass() );

    }

    protected WebDriver driver;
    @Autowired
    DF driverFactory;

    @PostConstruct
    public void init(){
        this.driver = driverFactory.getDriver();
        PF.initElements( this);
    }
    public void set(DriverFactory driverFactory) {
        this.driver = DF.getDriver();
    }

    public void setLog(Logger log) {
        this.log = log;
    }
}
