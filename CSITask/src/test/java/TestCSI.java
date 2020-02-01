import helpers.EmailHelper;
import objects.Message;
import objects.User;
import org.junit.*;

public class TestCSI {
    User user;
    Message message;
    Message messageTest;
    EmailHelper emailHelper;
    @Before
    public void preconditions(){
        user=new User();
        user.email="some email";
        user.password="some password";
        message=new Message();
        message.snippetSender="Stepik Team";
        message.snippet="Рекомендуемые курсы на Stepik";
        message.text="Мы хотели бы порекомендовать вам некоторые курсы на ";
        message.theme="Рекомендуемые курсы на Stepik";
        emailHelper=new EmailHelper();
        emailHelper.open();
        emailHelper.login(user);

    }
    @Test
    public void test(){
        messageTest = emailHelper.getMessage(message);
        Assert.assertEquals("тема не совпала",messageTest.theme,message.theme);
        Assert.assertTrue("текст сообщения неверен",messageTest.text.contains(message.text));


    }
    @After
    public void  teardown(){
        emailHelper.logout();
        emailHelper.close();}
}
