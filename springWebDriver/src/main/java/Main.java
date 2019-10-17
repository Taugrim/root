import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pages.KMPage;
import tools.AppConfig;
import tools.Context;

@Configurable
public class Main {
    public static AnnotationConfigApplicationContext context;

    public static void main(String[] args) {
        context = Context.getContext();
        KMPage km = (KMPage) context.getBean("KMPage", KMPage.class);
        km.get();
        WebElement w = km.search;
        System.out.println(km.search);
    }
}
