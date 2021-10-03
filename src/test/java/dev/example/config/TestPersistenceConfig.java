package dev.example.config;

import dev.example.dao.impls.UserDaoMySqlJdbcTemplate;
import dev.example.dao.impls.UserDaoMysqlNamedParameterJdbcTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration()
@PropertySource(
        value = "classpath:test-persistence.properties",
        ignoreResourceNotFound = true
)
public class TestPersistenceConfig {
    @Value("${db.driver}")
    String driver;
    @Value("${db.url}")
    String url;
    @Value("${db.user}")
    String userName;
    @Value("${db.password}")
    String password;

    @Bean
    public DataSource dataSource() {
        final var driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(driver);
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setUsername(userName);
        driverManagerDataSource.setPassword(password);

        return driverManagerDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public UserDaoMySqlJdbcTemplate userDaoMySqlJdbcTemplate() {
        return new UserDaoMySqlJdbcTemplate(jdbcTemplate());
    }

    @Bean
    public UserDaoMysqlNamedParameterJdbcTemplate userDaoMysqlNamedParameterJdbcTemplate() {
        return new UserDaoMysqlNamedParameterJdbcTemplate(namedParameterJdbcTemplate());
    }
}