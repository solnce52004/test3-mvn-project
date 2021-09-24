package dev.example.config.properties;

import org.springframework.context.annotation.PropertySource;


@PropertySource(
        value = "classpath:persistence.properties",
        ignoreResourceNotFound=true
)
public class PersistenceProperties {
    private String driver;
    private String url;
    private String user;
    private String password;

    private PersistenceProperties(String driver, String url, String user, String password) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
