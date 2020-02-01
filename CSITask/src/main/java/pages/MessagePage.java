package pages;

import elements.ItemMail;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

public class MessagePage extends BP{

     @FindBy(xpath = "//*[@class='MailList-list-2L']//*[@draggable]")
     public  TextInput message;
     @FindBy(xpath = ".//*[@data-cerber-head=\"logbtn\"]")
     public   Button theme;


}
