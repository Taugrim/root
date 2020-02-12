import helpers.EmailHelper;
import helpers.JugRuHelper;
import objects.Message;
import objects.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestJugRu {
    User userJUGRU;
    User userEmail;
    Message message;
    EmailHelper emailHelper;
    String email = "some email";
    String pasword = "some password";
    JugRuHelper jugRuHelper ;


    @Before
    public void preconditions() {
        jugRuHelper = new JugRuHelper();
        userJUGRU.email = email;
        userJUGRU.password = "some password";
        userEmail.email = email;
        userEmail.password = pasword;
        message.snippetSender = "JUG Ru Group Notifier";
        message.snippet = "регистрац";

    }

    @Test
    public void test() {
        registration(userJUGRU);
        email(userEmail, message);
        confirmationPassword(userJUGRU);
        Assert.assertTrue("личный кабинет не открыт",jugRuHelper.pc());
    }

    public void registration(User user) {
        jugRuHelper.open();
        jugRuHelper.createAccount(user);
    }


    public void confirmationPassword(User user) {
        jugRuHelper.passwordConfirmation(user);
    }

    public void email(User user, Message message) {
        emailHelper.open();
        emailHelper.login(user);
        emailHelper.getMessage(message).itemMail.clickHref("Подтвердить");
    }

    @After
    public void teardown() {
        emailHelper.close();
    }
}
