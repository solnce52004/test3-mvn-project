package dev.example.robots;

import dev.example.details.heads.HeadInterface;
import dev.example.details.legs.LegInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class T2000 implements RobotInterface {
    @Value("${T2000.name}")
    private String name = "T2000";

//    @Autowired
//    @Qualifier("sonyHead")
    private HeadInterface head;

//    @Autowired
//    @Qualifier("sonyLeg")
    private LegInterface leg;

    public T2000() {
    }

    public T2000(
            HeadInterface head,

            LegInterface leg
    ) {
        this.head = head;
        this.leg = leg;
    }

    public T2000(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHead(HeadInterface head) {
        this.head = head;
    }

    public void setLeg(LegInterface leg) {
        this.leg = leg;
    }

    public String getName() {
        return name;
    }

    public HeadInterface getHead() {
        return head;
    }

    public LegInterface getLeg() {
        return leg;
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
}
