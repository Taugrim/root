package pages;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class HomePage extends BP{

     @FindBy(xpath = ".//*[@name=\"email\"]")
     public TextInput email;
     @FindBy(xpath = ".//*[@data-cerber-head=\"logbtn\"]")
     public  Button logbtn;
     @FindBy(xpath = ".//*[@name=\"password\"]")
     public   TextInput password;
     @FindBy(xpath = ".//*[@data-cerber-head=\"pasbtn\"]")
     public   Button pasbtn;
     @FindBy(xpath = ".//*[@data-cerber-head=\"main::mail::w::inbox\"]")
     public   Button inbox;
}
