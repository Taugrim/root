package pages;

import elements.ItemMail;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

public class MailPage extends BP{

     @FindBy(xpath = ".//*[@data-cerber-topline='user::id']")
     public   Button user;
      @FindBy(xpath = ".//*[@data-cerber-topline=\"user::id::logout\"]")
     public   Button logout;
     @FindBy(xpath = ".//*[@class='MailList-list-2L']//*[@draggable]")
     public    List<ItemMail> itemMailList;

}
