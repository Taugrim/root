import helpers.EmailHelper;
import helpers.JugRuHelper;
import objects.Message;
import objects.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestLoginJugRu {
    User userJUGRU;
    Message message;
    String email = "some email";
    JugRuHelper jugRuHelper ;


    @Before
    public void preconditions() {
        jugRuHelper = new JugRuHelper();
        userJUGRU.email = email;
        userJUGRU.password = "some password";
        message.snippetSender = "JUG Ru Group Notifier";
        message.snippet = "регистрац";
        jugRuHelper.createApiPC(userJUGRU);

    }

    @Test
    public void test() {
      login(userJUGRU);
        Assert.assertTrue("личный кабинет не открыт",jugRuHelper.pc());
    }


    public void login(User user) {
        jugRuHelper.open();
        jugRuHelper.login(user);
    }


    @After
    public void teardown() {
        jugRuHelper.close();
    }
}
