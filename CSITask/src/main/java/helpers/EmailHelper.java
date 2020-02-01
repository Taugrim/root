package helpers;

import elements.ItemMail;
import objects.Message;
import objects.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.MailPage;
import pages.MessagePage;
import tools.DF;

public class EmailHelper {
    WebDriver wd;
    public HomePage homePage;
    public MailPage mailPage;
    public MessagePage messagePage;
    public EmailHelper(){
        this.wd= DF.getDriver();
        this.homePage=new HomePage();
        this.mailPage=new MailPage();
        this.messagePage=new MessagePage();

    }
    public void open(){
        wd.get("https://www.rambler.ru/");
    }
    public void login(User user){
        homePage.email.sendKeys(user.email);
        homePage.logbtn.click();
        homePage.password.sendKeys(user.password);
        homePage.pasbtn.click();
        homePage.inbox.click(); }

    public ItemMail findMessage(Message message){
        return mailPage.itemMailList.stream().
                filter(q->q.getSender().contains(message.snippetSender)||q.getTextSnippet().equals(message.snippet))
                .findAny().orElse(null);
    }
    public Message getMessage(Message message){
        findMessage(message).click();
        Message message1=new Message();
        message1.text=messagePage.message.getText();
        message1.theme=messagePage.theme.getText();
        return message1;
    }
    public void logout(){
        mailPage.user.click();
        WebDriverWait wait = new WebDriverWait(wd, 5);
        wait.until(ExpectedConditions.elementToBeClickable(mailPage.logout)).click();
    }
    public void close(){
        wd.close();
    }
}
