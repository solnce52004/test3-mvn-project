package dev.example.details.heads;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("boshHeadComponent")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BoshHead implements HeadInterface {
    private final String name;

    public String getName() {
        return name;
    }

    public BoshHead(@Value("BoshHead") String name) {
        this.name = name;
    }

    @Override
    public void think() {
        System.out.println(getName() + ":___" + this);
    }
}
