package dev.example.entities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class User {
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
