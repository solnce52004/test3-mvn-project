package dev.example.printer;

import dev.example.beans.details.Head;
import dev.example.beans.robots.RobotFactoryInterface;
import dev.example.beans.robots.RobotInterface;
import dev.example.beans.robots.T1000;
import org.springframework.context.ApplicationContext;

public class PrintBeanT1000 extends PrintBean<T1000> {

    public static String[] getBeanNames() {
        return new String[]{
                "t1000",
                "t1000ConstructWithDetails",
                "t1000InjectBySetterUsingProperty",
                "t1000InjectBySetterUsingNamespace",
                "t1000WithHead"
        };
    }

    public static void printBeanNamesT1000(ApplicationContext context) {
        PrintBean.printBeanNames(
                context,
                T1000.class,
                getBeanNames()
        );
    }

    public static void printBeansT1000(ApplicationContext context) {
        PrintBean.printBeans(
                context,
                T1000.class,
                getBeanNames()
        );
    }

    public static void printDetailsT1000(ApplicationContext context) {
        for (String beanId : getBeanNames()) {
            final var bean = context.getBean(
                    beanId,
                    T1000.class
            );
            bean.showDetails();
        }
    }

    public static void useT1000Factory(ApplicationContext context) {
        final RobotFactoryInterface robotFactoryBean = (RobotFactoryInterface) context.getBean("t1000FactoryAbstract");

        // print def props on init
        RobotInterface b1 = robotFactoryBean.createRobot();
        RobotInterface b2 = robotFactoryBean.createRobot();
        RobotInterface b3 = robotFactoryBean.createRobot();

        // check set props after init by constructor
        System.out.println("b1 details:");
        b1.showDetails();

        System.out.println("b1 details action:");
        b1.showActionDetails();

        // check singleton
        System.out.println("robotFactoryBean: " + robotFactoryBean);
        System.out.println("robotFactoryBean: " + robotFactoryBean);

        // check prototype
        System.out.println("b1: " + b1);
        System.out.println("b2: " + b2);
        System.out.println("b3: " + b3);


    }
}
