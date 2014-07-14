package ua.vn.os.ulteam.model.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by os on 11.07.14.
 */

@Configuration
@PropertySource("classpath:ua/vn/os/ulteam/model/config/datasource.properties")
public class DataSourceConfig {

    @Value("${jdbc.connection.url}")
    private String url;

    @Value("${jdbc.connection.username}")
    private String username;

    @Value("${jdbc.connection.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource(url, username, password);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
