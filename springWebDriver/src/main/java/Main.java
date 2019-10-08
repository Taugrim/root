import aspects.AspectInit;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pages.KMPage;
@Configurable
public class Main {

    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.scan("implemet");
        context.scan("pages");
        context.scan("aspects");
        context.refresh();
//        AspectInit a = (AspectInit) context.getBean(AspectInit.class);
//       a.pointget();
        KMPage km = (KMPage) context.getBean("KMPage",KMPage.class);
//        KMPage km =  context.getBean(KMPage.class);
        km.get();
        WebElement w = km.search;
//        System.out.println(km.search);
//        P p=(P)context.getBean("P");
//        System.out.println(p.getUrl());
    }
}
