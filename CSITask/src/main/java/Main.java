import com.gargoylesoftware.htmlunit.WebClient;
import helpers.EmailHelper;
import objects.Message;
import objects.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import tools.DF;

public class Main {
    public static void main(String[] args) {
        User user=new User();
        user.email="";
        user.password="";
        Message message=new Message();
        message.snippetSender="Stepik Team";
        message.snippet="Рекомендуемые курсы на Stepik";
        message.text="Мы хотели бы порекомендовать вам некоторые курсы на ";
        message.theme="Рекомендуемые курсы на Stepik";
        EmailHelper emailHelper=new EmailHelper();
        emailHelper.open();
        emailHelper.login(user);
        Message m= emailHelper.getMessage(message);
        m.theme.equals(message.theme);
        m.text.contains(message.text);
        emailHelper.logout();
        emailHelper.close();


    }
}
