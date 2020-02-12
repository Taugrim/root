package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class PersonalCabinetJugRuPage extends BP {

    @FindBy(xpath = ".//*[text()=' Ваши конференции ']")
    public WebElement conf;

}
