package dev.example.robots;

import dev.example.details.heads.HeadInterface;
import dev.example.details.legs.LegInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class T1000 implements RobotInterface {
    private String name = "T1000";
    @Autowired
    @Qualifier("boshHead")
    private HeadInterface head;
    @Autowired
    @Qualifier("boshLeg")
    private LegInterface leg;

    public T1000() {
    }

    public T1000(
            HeadInterface head,
            LegInterface leg
    ) {
        this.head = head;
        this.leg = leg;
    }

    public T1000(String name) {
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
