package pages;

import elements.ItemMail;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

public class MailPage extends BP{
     //list mails //*[@class='MailList-list-2L']//*[@draggable]"

     @FindBy(xpath = "//*[@class='MailList-list-2L']//*[@draggable]")
     TextInput email;
     @FindBy(xpath = ".//*[@data-cerber-head=\"logbtn\"]")
     Button logbtn;
     @FindBy(xpath = ".//*[@name=\"password\"]")
     TextInput password;
     @FindBy(xpath = ".//*[@data-cerber-head=\"pasbtn\"]")
     Button pasbtn;
     @FindBy(xpath = ".//*[@data-cerber-head=\"main::mail::w::inbox\"]")
     Button inbox;
     @FindBy(xpath = ".//*[@data-cerber-topline='user::id']")
     Button user;
     @FindBy(xpath = ".//*[@class='MailList-list-2L']//*[@draggable]")
     List<ItemMail> itemMailList;

}
