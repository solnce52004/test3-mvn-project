package dev.example;

import dev.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        //XML
//        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        // set config class by register
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
    }
}
