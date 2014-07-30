package ua.vn.os.ulteam.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ua.vn.os.ulteam.service.config.ServiceConfig;

/**
 * @author os
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ua.vn.os.ulteam.web.controller")
@Import({ServiceConfig.class})
public class ControllerConfig {

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".html");
        return resolver;
    }
}
