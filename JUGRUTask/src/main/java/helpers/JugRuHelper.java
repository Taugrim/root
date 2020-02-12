package helpers;

import objects.User;
import org.openqa.selenium.WebDriver;
import pages.HomeJugRuPage;
import pages.PersonalCabinetJugRuPage;
import pages.RegistrationJugRuPage;
import tools.DF;

public class JugRuHelper {
    WebDriver wd;
    public HomeJugRuPage homeJugRuPage;
    public RegistrationJugRuPage registrationJugRuPage;
    public PersonalCabinetJugRuPage personalCabinetJugRuPage;

    public JugRuHelper() {
        this.wd = DF.getDriver();
        this.homeJugRuPage = new HomeJugRuPage();
        this.registrationJugRuPage = new RegistrationJugRuPage();
        this.personalCabinetJugRuPage = new PersonalCabinetJugRuPage();

    }

    public void open() {
        wd.get("https://my.jugru.org/");
    }

    public void login(User user) {
        homeJugRuPage.email.click();
        homeJugRuPage.email.sendKeys(user.email);
        homeJugRuPage.password.click();
        homeJugRuPage.password.sendKeys(user.email);
        homeJugRuPage.submit.click();

    }

    public void createAccount(User user) {
        homeJugRuPage.registration.click();
        registrationJugRuPage.email.click();
        registrationJugRuPage.email.sendKeys(user.email);
        registrationJugRuPage.submit.click();
    }

    public boolean pc() {
        return personalCabinetJugRuPage.conf.isDisplayed();
    }
    public void passwordConfirmation(User user) {
        registrationJugRuPage.password.click();
        registrationJugRuPage.password.sendKeys(user.email);
        registrationJugRuPage.passwordConfirmation.click();
        registrationJugRuPage.passwordConfirmation.sendKeys(user.email);
        registrationJugRuPage.submit.click();
    }



    public void close() {
        wd.close();
    }

    public void createApiPC(User userJUGRU) {
    }
}
