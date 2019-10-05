import implemet.DF;
import interfaces.DriverFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pages.KMPage;
import pages.P;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.scan("implemet");
        context.scan("pages");
        context.refresh();
        KMPage   km= (KMPage)context.getBean(KMPage.class);
        km.get();
        P p=(P)context.getBean("P");
        System.out.println(p.getUrl());
    }
}
