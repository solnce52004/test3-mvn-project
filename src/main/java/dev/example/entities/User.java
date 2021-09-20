package dev.example.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class User {
    Logger logger = LoggerFactory.getLogger(User.class);

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
        logger.debug("User DEBUG: created new instance");
        logger.info("User INFO: created new instance");
        logger.error("User ERROR: created new instance");
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
