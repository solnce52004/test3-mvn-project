package dev.example.config;

import dev.example.dao.interfaces.UserDao;
import dev.example.services.UserService;
import dev.example.util.validators.UserValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration()
public class TestConfig {

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
//        return mock(UserDao.class);
//    }
}
