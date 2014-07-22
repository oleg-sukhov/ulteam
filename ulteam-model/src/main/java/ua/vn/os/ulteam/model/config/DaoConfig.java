package ua.vn.os.ulteam.model.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.vn.os.ulteam.model.dao.NewsDao;
import ua.vn.os.ulteam.model.dao.jdbcTemplate.NewsJdbcTemplateDao;

/**
 * Created by os on 16.07.14.
 */
@Configuration
@Import({RepositoryConfig.class})
public class DaoConfig {

    @Bean
    public NewsDao newsDao() {
        return new NewsJdbcTemplateDao();
    }





}
