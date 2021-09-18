package dev.example.aop.loggers;

import dev.example.entities.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserLogger {
    //for xml configuration
//    public void logUserId(User user) {
//        System.out.println("user log: " + user.getId());
//    }


    @Pointcut("execution(* dev.example.dao.impls.UserDaoImpl.findById(..))")
    private void findUserById(){
        //
    }

    @AfterReturning(
            value = "findUserById()",
            returning = "user"
    )
    public void logUserId(JoinPoint joinPoint, User user) {
        System.out.println("@AfterReturning: Method: " + joinPoint.getSignature().getName());
        System.out.println("@AfterReturning: user id log: " + user.getId());
    }

    @Around(value = "within(dev.example.dao.impls.UserDaoImpl)")
    public Object aroundUserLog(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("@Around: before...");
        Object joinPoint = null;
        try{
            joinPoint = proceedingJoinPoint.proceed();
            System.out.println("@Around: proceed...");
            } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("@Around: after...");

        return joinPoint;
    }
}
