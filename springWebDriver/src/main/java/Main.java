import implemet.DF;
import interfaces.DriverFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pages.KMPage;
import pages.P;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
        KMPage   km= (KMPage)context.getBean("KMPage");
        km.get();
        P p=(P)context.getBean("P");
        System.out.println(p.getUrl());
    }
}
