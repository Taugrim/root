import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

class AzsItem extends HtmlElement {
    public AzsItem() {
        super();

    }
    @FindBy(css="span")

    HtmlElement text;

   @Override
    public String getText(){
       return text.getText();
   }
}