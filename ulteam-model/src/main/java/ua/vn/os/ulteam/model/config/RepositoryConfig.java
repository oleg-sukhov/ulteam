package ua.vn.os.ulteam.model.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.DriverAction;
import java.util.Properties;

/**
 * Created by os on 11.07.14.
 */

@Configuration
@PropertySource("classpath:ua/vn/os/ulteam/model/config/datasource.properties")
public class RepositoryConfig {


    @Value("${jdbc.connection.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.connection.url}")
    private String url;

    @Value("${jdbc.connection.username}")
    private String username;

    @Value("${jdbc.connection.password}")
    private String password;

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
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
