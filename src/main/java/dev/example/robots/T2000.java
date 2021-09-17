package dev.example.robots;

import dev.example.details.heads.HeadInterface;
import dev.example.details.legs.LegInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
// по прежнему синглтон
public class T2000 implements RobotInterface {
    private final String name;
    private final HeadInterface head;
    private final LegInterface leg;

    @Autowired
    public T2000(
            @Value("${T2000.name}") String name,
            @Qualifier("sonyHead") HeadInterface head,
            @Qualifier("sonyLeg") LegInterface leg
    ) {
        this.name = name;
        this.head = head;
        this.leg = leg;
        action();
        say();
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
        RobotInterface t = new T2000(name, head, leg);
        System.out.println("T2000:___" + t);

        return t;
    }
}
