package dev.example.robots;

import dev.example.details.heads.HeadInterface;
import dev.example.details.legs.LegInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * бин класса будет создан:
 * без использования фабрики - если SCOPE_SINGLETON,
 * при использовании фабрики - с любым скоупом
 */
@Component
//@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class T1000 implements RobotInterface {

    @Value("${T1000.name}")
    private String name = "T1000";

    // указание final ОБЯЗЫВАЕТ сделать инжект через конструкор
    private final HeadInterface head;
    private final LegInterface leg;

    //приватный коструктор для использования только в фабрике
    @Autowired
    private T1000(
            @Qualifier("boshHeadComponent") HeadInterface head,
            @Qualifier("boshLegComponent") LegInterface leg
    ) {
        this.head = head;
        this.leg = leg;
        action();
        say();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void action() {
        head.think();
        leg.go();
    }

    @Override
    public void say() {
        System.out.println("i am " + getName());
    }

    @Override
    public RobotInterface getInstance() {
        RobotInterface t = new T1000(head, leg);
        System.out.println("T1000:___" + t);

        return t;
    }
}
