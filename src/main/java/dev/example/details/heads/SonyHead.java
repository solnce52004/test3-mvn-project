package dev.example.details.heads;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SonyHead implements HeadInterface {
    @Value("SonyHead")
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public void think() {
        System.out.println(getName() + " is thinking");
    }
}
