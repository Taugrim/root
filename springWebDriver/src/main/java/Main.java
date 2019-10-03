import implemet.DF;
import interfaces.DriverFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pages.KMPage;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
        DriverFactory driverFactory =(DriverFactory )context.getBean("DriverFactory");
//        KMPage   km= (KMPage)context.getBean("KMPage");
//        km.get();
    }
}
