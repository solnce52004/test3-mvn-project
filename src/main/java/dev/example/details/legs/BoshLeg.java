package dev.example.details.legs;

import org.springframework.stereotype.Component;

@Component("boshLegComponent")
public class BoshLeg implements LegInterface {

    private String name = "BoshLeg";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void go() {
        System.out.println(name + " is coming");
    }
}
