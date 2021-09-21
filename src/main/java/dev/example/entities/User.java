package dev.example.entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

public class User {
//    private final static Logger log = LoggerFactory.getLogger(User.class);
    private final static Logger log = LogManager.getLogger(User.class.getName());

    @Value("55")
    private final int id;
    private final String name;
    private final int age;

    public User(
            @Value("55") int id,
            @Value("${default.user.name}") String name,
            @Value("${default.user.age}") int age
    ) {
        this.id = id;
        this.name = name;
        this.age = age;

        //try
        log.trace("User INFO: created new instance");
        log.debug("User INFO: created new instance");
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
