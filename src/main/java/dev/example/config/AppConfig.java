package dev.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "dev")
@PropertySource("classpath:application.properties")
//@EnableAspectJAutoProxy
public class AppConfig {
}
