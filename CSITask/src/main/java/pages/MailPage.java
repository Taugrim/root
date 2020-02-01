package pages;

import elements.ItemMail;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

public class MailPage extends BP{


     @FindBy(xpath = "//*[@class='MailList-list-2L']//*[@draggable]")
     public  TextInput email;
     @FindBy(xpath = ".//*[@data-cerber-head=\"logbtn\"]")
     public   Button logbtn;
     @FindBy(xpath = ".//*[@name=\"password\"]")
     public   TextInput password;
     @FindBy(xpath = ".//*[@data-cerber-head=\"pasbtn\"]")
     public   Button pasbtn;
     @FindBy(xpath = ".//*[@data-cerber-head=\"main::mail::w::inbox\"]")
     public   Button inbox;
     @FindBy(xpath = ".//*[@data-cerber-topline='user::id']")
     public   Button user;
     @FindBy(xpath = ".//*[@class='MailList-list-2L']//*[@draggable]")
     public    List<ItemMail> itemMailList;

}
