package pages;

import elements.ItemMail;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

public class MessagePage extends BP{

     @FindBy(xpath = "//*[@id='letter']")
     public TextBlock message;
     @FindBy(xpath = ".//*[@class='LetterHeader-subject-3U']")
     public   TextBlock theme;


}
