package ua.vn.os.ulteam.model.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.vn.os.ulteam.model.dao.NewsDao;
import ua.vn.os.ulteam.model.entity.News;
import ua.vn.os.ulteam.model.entity.Photo;
import ua.vn.os.ulteam.model.entity.PhotoAlbum;

import javax.sql.DataSource;
import java.sql.DriverAction;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author oleg.sukhov
 */

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:ua/vn/os/ulteam/model/config/datasource.properties")
public class RepositoryConfig {

    @Value("${connection.driverClassName}")
    private String driverClassName;

    @Value("${connection.url}")
    private String url;

    @Value("${connection.username}")
    private String username;

    @Value("${connection.password}")
    private String password;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHbm2ddlAuto;

    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public HibernateTemplate hibernateTemplate() {
        HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory());
        hibernateTemplate.setCheckWriteOperations(false);
        return hibernateTemplate;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory());
    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder sessionFactoryBuilder = new LocalSessionFactoryBuilder(dataSource());
        updateHibernateProperties(sessionFactoryBuilder.getProperties());
        sessionFactoryBuilder.addAnnotatedClasses(News.class, Photo.class, PhotoAlbum.class );
        new SchemaUpdate(sessionFactoryBuilder, sessionFactoryBuilder.getProperties()).execute(true, true);
        return sessionFactoryBuilder.buildSessionFactory();
    }

    private Properties updateHibernateProperties(Properties properties) {
        properties.setProperty("hibernate.dialect", hibernateDialect);
        properties.setProperty("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
        properties.setProperty("hibernate.show_sql", hibernateShowSql);

        return properties;
    }
}
