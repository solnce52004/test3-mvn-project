package dev.example;

import dev.example.printer.PrintBeanHead;
import dev.example.printer.PrintBeanT1000;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static final String HELLO_WORLD = "Hello World!";

    public static void main(String[] args) {
        System.out.println(sayHello());

        final ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        //T1000
        PrintBeanT1000.printBeanNamesT1000(context);
        PrintBeanT1000.printBeansT1000(context);
        PrintBeanT1000.printDetailsT1000(context);
        PrintBeanT1000.useT1000Factory(context);

        //Head
        PrintBeanHead.printBeanHead(context);
        PrintBeanHead.printActionHead(context);
    }

    public static String sayHello() {
        return HELLO_WORLD;
    }
}
