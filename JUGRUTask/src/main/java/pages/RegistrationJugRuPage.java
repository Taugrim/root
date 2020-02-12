package pages;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class RegistrationJugRuPage extends BP{

     @FindBy(xpath = "//*[@id='registration-email']")
     public TextInput email;

     @FindBy(xpath = "//button[@type='submit']")
     public Button submit;

     @FindBy(xpath = "//*[@id='upfft-password']")
     public TextInput password;

     @FindBy(xpath = "//*[@id='upfft-passwordConfirmation']")
     public TextInput passwordConfirmation;

}
