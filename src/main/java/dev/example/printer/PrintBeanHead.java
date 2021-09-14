package dev.example.printer;

import dev.example.beans.details.Head;
import org.springframework.context.ApplicationContext;

public class PrintBeanHead extends PrintBean<Head>{

    public static String[] getBeanNames(){
        // Test bean scope (prototype)
        return new String[] {
                Head.BEAN_NAME,
//                Head.BEAN_NAME,
//                Head.BEAN_NAME,
        };
    }

    public static void printBeanHead(ApplicationContext context){
        new PrintBean<Head>().printBeans(
                context,
                Head.class,
                getBeanNames()
        );
    }

    public static void printActionHead(ApplicationContext context
    ) {
        for (String beanId : getBeanNames()) {
            final var bean = context.getBean(
                    beanId,
                    Head.class
            );
            bean.action();
        }
    }
}
