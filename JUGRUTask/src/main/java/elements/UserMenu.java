package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class UserMenu extends TypifiedElement {
    protected UserMenu(WebElement wrappedElement) {
        super(wrappedElement);
    }
    public  void logout(){
        getWrappedElement().findElement(By.xpath("//*[@data-cerber-topline=\"user::id::logout\"]")).click();
    }
}
