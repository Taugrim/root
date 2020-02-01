package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class ItemMail extends TypifiedElement {

    protected ItemMail(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void clickChekBox(){
        new CheckBox(getWrappedElement().findElement(By.xpath("//*[@class='MailListItem-checkbox-1q']"))).select();
    }
    public void clickStar(){
        getWrappedElement().findElement(By.xpath("//*[@class='MailListItem-star-Sl']")).click();
    }
    public String getTextSnippet(){
        return getWrappedElement().findElement(By.xpath("//*[@class='MailListItem-snippet-25']")).getAttribute("title");}
    public String getSender(){
       return getWrappedElement().findElement(By.xpath("//*[@class='MailListItem-star-Sl']")).getAttribute("title");
    }
    public String getDate(){
       return getWrappedElement().findElement(By.xpath("//*[@class='MailListItem-date-hq']")).getAttribute("title");
    }
}
