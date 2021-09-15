package dev.example.details.legs;

import org.springframework.stereotype.Component;

@Component
public class SonyLeg implements LegInterface {

    private String name = "SonyLeg";

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
