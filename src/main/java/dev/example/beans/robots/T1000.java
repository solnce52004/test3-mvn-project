package dev.example.beans.robots;


import dev.example.beans.details.DetailInterface;
import dev.example.beans.details.Head;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class T1000 implements RobotInterface {

    private String name = "default name";

    private DetailInterface head = new Head();
    private DetailInterface leg;
    private DetailInterface hand;
    private List<DetailInterface> details = new ArrayList<>();

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

    public void setDetails(List<DetailInterface> details) {
        this.details = details;
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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getBeanName() {
        return name;
    }

    private List<DetailInterface> getDetails() {
        if (this.details.isEmpty()){
            this.details.add(getHead());
            this.details.add(getLeg());
            this.details.add(getHand());
        }
        return details;
    }

    @Override
    public void showDetails() {
        getDetails()
                .stream()
                .filter(Objects::nonNull)
                .forEach(System.out::println);
    }
}

