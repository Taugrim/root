package tools;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import tools.tools.AppConfig;

public class Context {
     static  AnnotationConfigApplicationContext context;
    public static AnnotationConfigApplicationContext getContext(){
        if (context==null){
         context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.scan("implemet");
        context.scan("pages");
        context.scan("aspects");
        context.refresh();
    }
        return  context;
    }
}
