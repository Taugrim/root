package pages;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class HomeJugRuPage extends BP {

    @FindBy(xpath = "//header//*[@href='/auth/registration?from=%2F']")
    public Button registration;
    @FindBy(xpath = "//*[@id='login-email']")
    public TextInput email;
    @FindBy(xpath = "//*[@id='login-password']")
    public TextInput password;
    @FindBy(xpath = "//button[@type='submit']")
    public Button submit;
}
