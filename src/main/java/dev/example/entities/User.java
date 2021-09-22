package dev.example.entities;

import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;

public class User {

    public static final int DEF_ID = 789;
    public static final String DEF_NAME = "Peter";
    public static final int DEF_AGE = 100;

    // TODO: уточнить
    //в каких случаях при вызове конструктора поля по дефолту будут заполнены из пропертей @Value
    @Value("${default.user.id}")
    private int id;
    @Value("${default.user.name}")
    private String name;
    @Value("${default.user.age}")
    private int age;

    public User(
            int id,
            String name,
            int age
    ) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() && age == user.age && name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name, age);
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
