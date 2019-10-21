package aspects;

import interfaces.BasePage;
import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.aop.config.*;
import org.springframework.stereotype.Component;
import testFrame.BaseTest;

@Aspect
@Component("aspect")
//@AspectJAutoProxy
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AspectInit {

    @Pointcut("execution(* *.get(..))")
    public  void pointget(){
    }
    @Before("pointget()")
    public void initget(JoinPoint joinPoint){
        System.out.println("222222");
    }

   @Pointcut("execution(public void *.Main.main(String[]))")
    public  void pointMain(){
    }
    @Before("pointMain()")
    public void initMain(JoinPoint joinPoint){
        System.out.println("pointMain");
    }

    @Pointcut("execution(public * pages.KMPage.get())")
    public  void pointKMPageGet(){
    }
    @Before("pointKMPageGet()")
    public void initpointKMPageGet(JoinPoint joinPoint){
        System.out.println("pointKMPageGet");
    }
    @Pointcut("execution(public * *(..))")
    public  void pub(){
    }
    @Before("pub()")
    public void asppub(JoinPoint joinPoint){
        System.out.println(joinPoint.getTarget().getClass().getSimpleName());
    }
    @Pointcut("execution(* pages.*.*(..))")
    public  void p1(){
    }
   @Pointcut("execution(public void  pages.KMPage.get())")
    private void fooMethodPointcut(){

    }
    @Before("execution(* *(..))")
    private void foo(){
        System.out.println("11111111");
    }
    @Before("fooMethodPointcut()")
    public void beforeFooMethod(){
    }

    @AfterReturning("fooMethodPointcut()")
    public void afterReturningFooMethod(){
    }
    @Pointcut("@annotation(javax.annotation.PostConstruct)")
    public  void poinPostConstruct(){
    }
    @After("@annotation(interfaces.Post)")
//    @After("poinPostConstruct()")
    public void afterPostConstruct(JoinPoint joinPoint){
        System.out.println( joinPoint.getTarget().getClass().getSimpleName()+"   zzzzzzzzzz");
//        = LogManager.getLogger( joinPoint.getTarget().getClass() );
    }


}
