package dev.example.config;

import dev.example.dao.impls.UserDaoImpl;
import dev.example.dao.impls.UserDaoJdbcMysql;
import dev.example.dao.interfaces.UserDao;
import dev.example.db.connections.jdbc.BaseConnection;
import dev.example.db.connections.jdbc.MySqlConnection;
import dev.example.services.UserService;
import dev.example.util.validators.UserValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.TestPropertySource;

import static org.mockito.Mockito.mock;

@Configuration()
@PropertySource("classpath:test-application.properties")
public class TestConfig {

    @Value("${test_db}")
    String test_database;

    @Bean
    public BaseConnection mySqlConnection(){
        return new MySqlConnection();
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
    UserDaoJdbcMysql userDaoJdbcMysql(BaseConnection mySqlConnection){
        return new UserDaoJdbcMysql(mySqlConnection, test_database);
    }
}
