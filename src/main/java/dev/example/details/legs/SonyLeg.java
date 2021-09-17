package dev.example.details.legs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SonyLeg implements LegInterface {

    @Value("SonyLeg")
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public void go() {
        System.out.println(getName() + " is coming");
    }
}
