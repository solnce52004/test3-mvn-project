package dev.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = "dev.example")
@PropertySource("classpath:application.properties")
@Configuration
public class AppConfig {

}
