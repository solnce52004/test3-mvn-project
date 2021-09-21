package dev.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "dev")
@PropertySource("classpath:application.properties")
//@EnableAspectJAutoProxy
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {
}
