package dev.example.config;

import dev.example.dao.impls.UserDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration()
@PropertySource(
        value = "classpath:hibernate.properties",
        ignoreResourceNotFound = true
)
@Import(TestPersistenceConfig.class)
@EnableTransactionManagement
public class HibernateConfig {

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) throws IOException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("dev.example.entities");
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.afterPropertiesSet();

        return sessionFactory.getObject();
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        InputStream in = HibernateConfig.class
                .getClassLoader()
                .getResourceAsStream("hibernate.properties");

        if (in != null) {
            try {
                properties.load(in);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        properties.put("hibernate.url", "jdbc:mysql://localhost:3306/test_db?characterEncoding=utf8");
//        properties.put("hibernate.driver_class", "com.mysql.cj.jdbc.Driver");
//        properties.put("hibernate.username", "root");
//        properties.put("hibernate.password", "Zerkalo82");
//
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
//        properties.put("hibernate.show_sql", true);
//        properties.put("hibernate.format_sql", true);
//        properties.put("hibernate.current_session_context_class", "thread");
//        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    @Bean
    public UserDaoHibernate userDaoHibernate(SessionFactory sessionFactory) {
        return new UserDaoHibernate(sessionFactory);
    }

//    @Bean
//    public SessionFactory sessionFactory() {
//        SessionFactory sessionFactory = null;
//        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
////                .configure("test-hibernate.cfg.xml")
//                .loadProperties(new File("src/test/resources/hibernate.properties"))
//                .build();
//
//        try {
//            final MetadataSources metadataSources = new MetadataSources(serviceRegistry)
////                .addAnnotatedClass(Users.class)
//                    ;
//
//            Reflections reflections = new Reflections("dev.example.entities", new TypeAnnotationsScanner());
//            final Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Entity.class, true);
//
//            for(Class<?> clazz : classes) {
//                System.out.println(clazz);
//                metadataSources.addAnnotatedClass(clazz);
//            }
//
//            Metadata metadata = metadataSources
//                    .getMetadataBuilder()
//                    .build();
//
//            sessionFactory = metadata.
//                    getSessionFactoryBuilder()
//                    .build();
//
//
//        } catch (Exception e) {
//            StandardServiceRegistryBuilder.destroy(serviceRegistry);
//            throw e;
//        }
//
//        return sessionFactory;
//    }

//    @Bean
//    public SessionFactory sessionFactory() {
//        SessionFactory sessionFactory = null;
//        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
////                .configure("test-hibernate.cfg.xml")
//                .build();
//        try {
//            sessionFactory = new MetadataSources(registry)
////                    .addAnnotatedClass(Users.class)
////                    .addPackage("dev.example.entities")
//                    .buildMetadata()
//                    .buildSessionFactory();
//        } catch (Exception e) {
//            StandardServiceRegistryBuilder.destroy(registry);
//        }
//        return sessionFactory;
//    }
}
