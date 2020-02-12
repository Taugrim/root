import helpers.EmailHelper;
import helpers.JugRuHelper;
import objects.Message;
import objects.User;

public class Main {
    static String email = "some email";
    static String pasword = "some password";

    public static void main(String[] args) {
        User userJUGRU = new User();
        userJUGRU.email = email;
        userJUGRU.password = "some password";
        User userEmail = new User();
        userEmail.email = email;
        userEmail.password = pasword;
        Message message = new Message();
        message.snippetSender = "JUG Ru Group Notifier";
        message.snippet = "регистрац";

        registration(userJUGRU);
        email(userEmail, message);
        confirmationPassword(userJUGRU);
        login(userJUGRU);
    }

    public static void registration(User user) {
        JugRuHelper jugRuHelper = new JugRuHelper();
        jugRuHelper.open();
        jugRuHelper.createAccount(user);
        System.out.println("");

    }

    public static void login(User user) {
        JugRuHelper jugRuHelper = new JugRuHelper();
        jugRuHelper.open();
        jugRuHelper.login(user);
    }

    public static void confirmationPassword(User user) {
        JugRuHelper jugRuHelper = new JugRuHelper();
        jugRuHelper.passwordConfirmation(user);
    }

    public static void email(User user, Message message) {
        EmailHelper emailHelper = new EmailHelper();
        emailHelper.open();
        emailHelper.login(user);
        Message m = emailHelper.getMessage(message);
        m.itemMail.clickHref("Подтвердить");
    }
}
