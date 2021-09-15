package dev.example.details.heads;

import org.springframework.stereotype.Component;

@Component
public class BoshHead implements HeadInterface {
    private String name = "BoshHead";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void think() {
        System.out.println(name + " is thinking");
    }
}
