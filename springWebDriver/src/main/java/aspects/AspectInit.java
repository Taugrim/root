package aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.aop.config.*;
import org.springframework.stereotype.Component;

@Aspect
@Component("aspect")
//@AspectJAutoProxy
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AspectInit {
//    @Pointcut("get(* @FindBy)")
//    public  void pointinit(){
//    }
//    @Before("pointinit()")
//    public void init(JoinPoint joinPoint){
//        System.out.println(joinPoint.getTarget().getClass().getSimpleName());
//    }
    @Pointcut("execution(* *.get(..))")
    public  void pointget(){
    }
    @Before("pointget()")
    public void initget(JoinPoint joinPoint){
        System.out.println(joinPoint.getTarget().getClass().getSimpleName());
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
//    @Before("p1()")
//    public void asp1(JoinPoint joinPoint){
//
//        System.out.println(joinPoint.getTarget().getClass().getSimpleName());
//    }
//    @AfterReturning(pointcut = "asp1", returning = "result")
//    public void stepStop(JoinPoint joinPoint, Object result) {
//        System.out.println("AfterReturning");
//    }

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
}
