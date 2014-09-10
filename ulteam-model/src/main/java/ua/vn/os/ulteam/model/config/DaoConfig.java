package ua.vn.os.ulteam.model.config;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.vn.os.ulteam.model.aspect.LogAspect;
import ua.vn.os.ulteam.model.dao.NewsDao;
import ua.vn.os.ulteam.model.dao.hibernate.NewsHibernateDao;
import ua.vn.os.ulteam.model.dao.hibernate.PhotoAlbumDao;
import ua.vn.os.ulteam.model.dao.hibernate.PhotoAlbumHibernateDao;

/**
 * @Author os
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

    @Bean
    public PhotoAlbumDao photoAlbumDao() {
        PhotoAlbumHibernateDao photoAlbumDao = new PhotoAlbumHibernateDao();
        photoAlbumDao.setHibernateTemplate(repositoryConfig.hibernateTemplate());
        return photoAlbumDao;
    }

}
