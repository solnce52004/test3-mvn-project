package dev.example.details.legs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("boshLegComponent")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BoshLeg implements LegInterface {

    private final String name;

    public String getName() {
        return name;
    }

    public BoshLeg(@Value("BoshLeg") String name) {
        this.name = name;
    }

    @Override
    public void go() {
        System.out.println(getName() + ":___" + this);
    }
}
