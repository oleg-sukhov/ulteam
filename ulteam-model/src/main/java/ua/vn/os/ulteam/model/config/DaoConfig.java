package ua.vn.os.ulteam.model.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.vn.os.ulteam.model.dao.NewsDao;
import ua.vn.os.ulteam.model.dao.hibernate.NewsHibernateDao;

/**
 * Created by os on 16.07.14.
 */
@Configuration
@Import({RepositoryConfig.class})
public class DaoConfig {

    @Autowired
    RepositoryConfig repositoryConfig;

    @Bean
    public NewsDao newsDao() {
        NewsHibernateDao newsDao = new NewsHibernateDao();
        newsDao.setHibernateTemplate(repositoryConfig.hibernateTemplate());
        return newsDao;
    }
}
