package dev.example;

import dev.example.robots.RobotInterface;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        RobotInterface t1000 = context.getBean("t1000", RobotInterface.class);
        t1000.action();
        t1000.say();
        RobotInterface t2000 = context.getBean("t2000", RobotInterface.class);
        t2000.action();
        t2000.say();
    }
}
