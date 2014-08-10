package ua.vn.os.ulteam.model.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ua.vn.os.ulteam.model.dao.NewsDao;
import ua.vn.os.ulteam.model.dao.hibernate.NewsDaoHibernateImpl;

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
        NewsDaoHibernateImpl newsDao = new NewsDaoHibernateImpl();
        newsDao.setHibernateTemplate(repositoryConfig.hibernateTemplate());
        return newsDao;
    }
}
