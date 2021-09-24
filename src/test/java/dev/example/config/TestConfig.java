package dev.example.config;

import dev.example.dao.impls.UserDaoMySqlJdbc;
import dev.example.dao.impls.UserDaoMySqlJdbcTemplate;
import dev.example.db.connections.BaseConnection;
import dev.example.db.connections.jdbc.JdbcMySqlConnection;
import dev.example.services.UserService;
import dev.example.util.validators.UserValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import static org.mockito.Mockito.mock;

@Configuration()
@PropertySource(
        value = "classpath:test-application.properties",
        ignoreResourceNotFound = true
)
public class TestConfig {

    @Value("${test_db}")
    String test_database;

    @Bean
    public BaseConnection mySqlConnection(){
        return new JdbcMySqlConnection();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer testPropertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public UserValidator userValidator(UserService userService){
        return new UserValidator(userService);
    }

    @Bean
    public UserService userService(){
        return mock(UserService.class);
    }

//    @Bean
//    public UserDao userDao(){
//        return mock(UserDaoImpl.class);
//    }

    @Bean
    public UserDaoMySqlJdbc userDaoMyDqlJdbc(BaseConnection mySqlConnection){
        return new UserDaoMySqlJdbc(mySqlConnection, test_database);
    }
}
