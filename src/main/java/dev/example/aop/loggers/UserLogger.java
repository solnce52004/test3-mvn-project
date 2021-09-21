package dev.example.aop.loggers;

import dev.example.aop.annotations.PrintUser;
import dev.example.entities.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserLogger {

    @Pointcut("execution(* dev.example.dao.impls.UserDaoImpl.findById(..))")
    private void findUserByIdPointcut() {
        //
    }

    @Pointcut("execution(* dev.example.dao.impls.UserDaoImpl.findByName(..))")
    private void findUserByNamePointcut() {
        //
    }

    // Pointcut для методов с кастомной аннотацией PrintUser
    // с указанием значения аргумента в аннотации
    // (ограничиваем поиск только по методам и по аннотации)
    @Pointcut("execution(* *(..)) && @annotation(printUser))")
    public void annotatedPrintUserPointcut(PrintUser printUser) {
        //
    }

    @Before(
            value = "findUserByNamePointcut() && annotatedPrintUserPointcut(printUser)",
            argNames = "joinPoint,printUser"
    )
    private void beforeAdvice(
            @NonNull JoinPoint joinPoint,
            PrintUser printUser
    ) {
        System.out.println("@Before: isPrint: " + printUser.isPrint());
        System.out.println("@Before: Method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(
            value = "(findUserByNamePointcut() ||" +
                    "findUserByIdPointcut()) && " +
                    "annotatedPrintUserPointcut(printUser)",
            returning = "user",
            argNames = "joinPoint,user,printUser"
    )
    public void afterReturningAdvice(
            @NonNull JoinPoint joinPoint,
            @NonNull User user,
            PrintUser printUser
    ) {
        System.out.println("@AfterReturning: isPrint: " + printUser.isPrint());
        System.out.println("@AfterReturning: Method: " + joinPoint.getSignature().getName());
        System.out.println("@AfterReturning: user id log: " + user.getId());
    }

    @After(
            value = "annotatedPrintUserPointcut(printUser)",
            argNames = "joinPoint,printUser"
    )
    public void afterAdvice(
            @NonNull JoinPoint joinPoint,
            PrintUser printUser
    ) {
        System.out.println("@After...: isPrint: " + printUser.isPrint());
        System.out.println("@After...: Method: " + joinPoint.getSignature().getName());
    }

    @Around(
            value = "within(dev.example.dao.impls.UserDaoImpl) && annotatedPrintUserPointcut(printUser)",
            argNames = "proceedingJoinPoint,printUser"
    )
    public Object aroundAdvice(
            ProceedingJoinPoint proceedingJoinPoint,
            PrintUser printUser
    ) {
        System.out.println("@Around: isPrint: " + printUser.isPrint());
        System.out.println("@Around: before...");
        Object joinPoint = null;
        try {
            joinPoint = proceedingJoinPoint.proceed();

            // теперь сработает @Before
            // теперь сработает @AfterReturning
            // теперь сработает @After
            // (остальное ниже - только после @After)
            System.out.println("@Around: proceed...");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        // сработает только после @After
        System.out.println("@Around: after...");

        return joinPoint;
    }
}
