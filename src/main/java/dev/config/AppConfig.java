package dev.config;

import dev.example.details.heads.BoshHead;
import dev.example.details.heads.HeadInterface;
import dev.example.details.heads.SonyHead;
import dev.example.details.legs.BoshLeg;
import dev.example.details.legs.LegInterface;
import dev.example.details.legs.SonyLeg;
import dev.example.robots.RobotInterface;
import dev.example.robots.T1000;
import dev.example.robots.T2000;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@PropertySource("classpath:application.properties")
@Configuration
public class AppConfig {
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public HeadInterface boshHead() {
        return new BoshHead();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public LegInterface boshLeg() {
        return new BoshLeg();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public HeadInterface sonyHead() {
        return new SonyHead();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public LegInterface sonyLeg() {
        return new SonyLeg();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RobotInterface t1000(@Value("${T1000.name}") String name) {
        return new T1000(name);
    }

//    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public RobotInterface t1000() {
//        return new T1000();
//    }

//    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public RobotInterface t2000() {
//        return new T2000();
//    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RobotInterface t2000(
            @Qualifier("boshHead") HeadInterface head,
            @Qualifier("boshLeg") LegInterface leg
    ) {
        return new T2000(head, leg);
    }
}
