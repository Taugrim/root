package interfaces;

import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public interface BasePage {
    public void set(DriverFactory driverFactory);
}
