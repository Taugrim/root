package pages;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class HomePage extends BP{

     @FindBy(xpath = ".//*[@name=\"email\"]")
     TextInput email;
     @FindBy(xpath = ".//*[@data-cerber-head=\"logbtn\"]")
     Button logbtn;
     @FindBy(xpath = ".//*[@name=\"password\"]")
     TextInput password;
     @FindBy(xpath = ".//*[@data-cerber-head=\"pasbtn\"]")
     Button pasbtn;
     @FindBy(xpath = ".//*[@data-cerber-head=\"main::mail::w::inbox\"]")
     Button inbox;
}
