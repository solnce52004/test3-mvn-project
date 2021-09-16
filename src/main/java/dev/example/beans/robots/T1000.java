package dev.example.beans.robots;

import dev.example.beans.details.DetailInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;

public class T1000 implements RobotInterface {

    private String name = "default name";

    @Autowired
    @Qualifier("headSpin")
    private DetailInterface head;

    @Autowired
    @Qualifier("legGo")
    private DetailInterface leg;
    private DetailInterface hand;

    @Autowired // но как отфильтровать по квалфаерам тут?
    private Set<DetailInterface> details = new HashSet<>();

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setHead(DetailInterface head) {
        this.head = head;
    }
    public void setLeg(DetailInterface leg) {
        this.leg = leg;
    }
    public void setHand(DetailInterface hand) {
        this.hand = hand;
    }

    public DetailInterface getHead() {
        return head;
    }
    public DetailInterface getLeg() {
        return leg;
    }
    public DetailInterface getHand() {
        return hand;
    }

    public void setDetails(Set<DetailInterface> details) {
        this.details = details;
    }

    private Set<DetailInterface> getDetails() {
        if (details.size() <= 1) {
            details.add(getHead());
            details.add(getLeg());
            details.add(getHand());
        }
        return details;
    }

    public T1000() {
    }

    public T1000(String name, DetailInterface head) {
        this.name = name;
        this.head = head;
    }

    public T1000(String name) {
        this.name = name;
        showDetails();
    }

    @Override
    public String getBeanName() {
        return getName();
    }

    @Override
    public void showDetails() {
        getDetails()
                .stream()
                .filter(Objects::nonNull)
                .forEach(System.out::println);
    }

    @Override
    public void showActionDetails() {
        getDetails()
                .stream()
                .filter(Objects::nonNull)
                .forEach(DetailInterface::action);
    }
}

