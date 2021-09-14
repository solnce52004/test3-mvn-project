package dev.example.printer;

import org.springframework.context.ApplicationContext;

public class PrintBean<T extends PrintBeanInterface> {
    public void printBeanNames(
            ApplicationContext context,
            Class<T> clazz,
            String[] beanNames
    ) {
        for (String beanId : beanNames) {
            final var bean = context.getBean(
                    beanId,
                    clazz
            );
            System.out.println(bean.getBeanName());
        }
    }

    public void printBeans(
            ApplicationContext context,
            Class<T> clazz,
            String[] beanNames
    ) {
        for (String beanId : beanNames) {
            System.out.println(
                    context.getBean(
                            beanId,
                            clazz
                    )
            );
        }
    }
}
