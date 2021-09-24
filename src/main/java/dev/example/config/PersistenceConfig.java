package dev.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource(
        value = "classpath:persistence.properties",
        ignoreResourceNotFound=true
)
public class PersistenceConfig {
    @Bean
    public DataSource dataSource(
            @Value("${db.driver}") String driver,
            @Value("${db.url}") String url,
            @Value("${db.user}") String user,
            @Value("${db.password}") String password
    ){
        final var driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(driver);
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setUsername(user);
        driverManagerDataSource.setPassword(password);

        return driverManagerDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
