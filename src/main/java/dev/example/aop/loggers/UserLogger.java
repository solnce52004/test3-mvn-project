package dev.example.aop.loggers;

import dev.example.aop.annotations.PrintUser;
import dev.example.dao.impls.UserDaoImpl;
import dev.example.entities.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserLogger {

    private static final Logger USER_DAO_IMPL_LOG = LoggerFactory.getLogger(UserDaoImpl.class);
    private static final Logger USER_LOGGER = LoggerFactory.getLogger(UserLogger.class);

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
        final String targetMethod = joinPoint.getSignature().getName();

        if (printUser.isPrint()) {
            USER_DAO_IMPL_LOG.info("@Before(isPrint):method: {}", targetMethod);
        }

        USER_LOGGER.info("@Before:(advice)method: {}", targetMethod);
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
        final String targetMethod = joinPoint.getSignature().getName();

        if (printUser.isPrint()) {
            USER_DAO_IMPL_LOG.info("@AfterReturning(isPrint):method: {}", targetMethod);
            //проверить настройки скоупа логгера для данного класса
            USER_DAO_IMPL_LOG.debug("@AfterReturning(isPrint) DEBUG :method: {}", targetMethod);
        }

        USER_LOGGER.info("@AfterReturning:(advice)method: {}", targetMethod);
        USER_LOGGER.info("@AfterReturning:(advice)userId: {}", user.getId());
    }

    @After(
            value = "annotatedPrintUserPointcut(printUser)",
            argNames = "joinPoint,printUser"
    )
    public void afterAdvice(
            @NonNull JoinPoint joinPoint,
            PrintUser printUser
    ) {
        final String targetMethod = joinPoint.getSignature().getName();

        if (printUser.isPrint()) {
            USER_DAO_IMPL_LOG.info("@After(isPrint):method: {}", targetMethod);
        }

        USER_LOGGER.info("@After:(advice)method: {}", targetMethod);
    }

    @Around(
            value = "within(dev.example.dao.impls.UserDaoImpl) && annotatedPrintUserPointcut(printUser)",
            argNames = "proceedingJoinPoint,printUser"
    )
    public Object aroundAdvice(
            ProceedingJoinPoint proceedingJoinPoint,
            PrintUser printUser
    ) {
        final String targetMethod = proceedingJoinPoint.getSignature().getName();

        if (printUser.isPrint()) {
            USER_DAO_IMPL_LOG.info("@After(isPrint):method: {}", targetMethod);
        }

        USER_LOGGER.info("@Around:(advice)method: {}", targetMethod);
        USER_LOGGER.info("@Around:(advice) before proceed...");

        Object joinPoint = null;

        try {
            joinPoint = proceedingJoinPoint.proceed();

            // теперь сработает @Before
            // теперь сработает @AfterReturning
            // теперь сработает @After
            // (остальное ниже - только после @After)
            USER_LOGGER.info("@Around:(advice) proceed...");
        } catch (Throwable throwable) {
            USER_LOGGER.error(throwable.getMessage());
        }

        // сработает только после @After
        USER_LOGGER.info("@Around:(advice) after proceed...");

        return joinPoint;
    }
}
