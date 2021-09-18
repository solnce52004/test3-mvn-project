package dev.config.aop.loggers;

import dev.example.entities.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserLogger {
    //for xml configuration
//    public void logUserId(User user) {
//        System.out.println("user log: " + user.getId());
//    }

    @Pointcut("execution(* dev.example.dao.impls.UserDaoImpl.findById(..))")
    private void findUserByIdPointcut() {
        //
    }

    @Pointcut("@annotation(dev.config.annotations.PrintUserId)")
    private void annotatedPrintUserIdPointcut() {
        //
    }

    @AfterReturning(
            value = "findUserByIdPointcut() && annotatedPrintUserIdPointcut()",
            returning = "user"
    )
    public void afterReturningUserIdLogAdvice(
            @NonNull JoinPoint joinPoint,
            @NonNull User user
    ) {
        System.out.println("@AfterReturning: Method: " + joinPoint.getSignature().getName());
        System.out.println("@AfterReturning: user id log: " + user.getId());
    }

    @Around(value = "within(dev.example.dao.impls.UserDaoImpl)")
    public Object aroundUserLogAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("@Around: before...");
        Object joinPoint = null;
        try {
            joinPoint = proceedingJoinPoint.proceed();
            System.out.println("@Around: proceed...");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("@Around: after...");

        return joinPoint;
    }

    @After("annotatedPrintUserIdPointcut()")
    public void afterUserIdLogAdvice(@NonNull JoinPoint joinPoint) {
        System.out.println("@After...: Method: " + joinPoint.getSignature().getName());
    }
}
