package dev.example.db.connections.jdbc;

import dev.example.config.TestConfig;
import dev.example.db.connections.BaseConnection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.assertj.core.api.Assertions.assertThat;


@ContextConfiguration(
        classes = TestConfig.class,
        loader = AnnotationConfigContextLoader.class
)
@ExtendWith(SpringExtension.class)
class JdbcMySqlConnectionTest {

    @Autowired
    BaseConnection mySqlConnection;

    @Value("${test_db}")
    String test_database;

    @Test
    void getConnect_Should_Return_Success_Connect_On_Prod_DB() {
        System.out.println(BaseConnection.PROJECT_DB);
        assertThat(mySqlConnection.getConnect(BaseConnection.PROJECT_DB))
                .isNotNull();
    }

    @Test
    void getConnect_Should_Return_Success_Connect_On_Test_DB() {
        System.out.println(test_database);
        assertThat(mySqlConnection.getConnect(test_database))
                .isNotNull();
    }
}