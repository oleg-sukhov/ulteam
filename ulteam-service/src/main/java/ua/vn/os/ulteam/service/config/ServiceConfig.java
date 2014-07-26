package ua.vn.os.ulteam.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ua.vn.os.ulteam.model.config.DaoConfig;
import ua.vn.os.ulteam.service.logic.NewsService;
import ua.vn.os.ulteam.service.logic.impl.NewsServiceImpl;

/**
 * @author os
 */
@Configuration
@Import(DaoConfig.class)
public class ServiceConfig {

    @Bean
    public NewsService newsService() {
        return new NewsServiceImpl();
    }
}
