package dev.example.entities;

import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;
import java.util.Random;

public class User {

    public static final long DEF_ID = 1L;
    public static final String DEF_NAME = "Peter";
    public static final String DEF_ROLE = "quest";

    // TODO: уточнить
    //в каких случаях при вызове конструктора поля по дефолту будут заполнены из пропертей @Value
    @Value("${default.user.id}")
    private long id = Math.abs((new Random()).nextLong());
    private String name;
    private String role = DEF_ROLE;

    public User(String name) {
        this.name = name;
    }

    public User(
            String name,
            String role
    ) {
        this.name = name;
        this.role = role;
    }

    public User(long id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public User() {
    }

    public static User getDefaultUser(){
        return new User(DEF_ID, DEF_NAME, DEF_ROLE);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() && Objects.equals(getName(), user.getName()) && Objects.equals(getRole(), user.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getRole());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
