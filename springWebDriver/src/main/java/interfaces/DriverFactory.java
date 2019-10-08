package interfaces;
import org.openqa.selenium.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public interface DriverFactory {
    public WebDriver getDriver();
    public By by(String x);
}
