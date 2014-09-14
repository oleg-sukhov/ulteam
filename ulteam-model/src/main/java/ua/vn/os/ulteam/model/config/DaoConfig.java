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
import ua.vn.os.ulteam.model.entity.News;
import ua.vn.os.ulteam.model.entity.PhotoAlbum;

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
        return new NewsHibernateDao(repositoryConfig.hibernateTemplate(), News.class);
    }

    @Bean
    public PhotoAlbumDao photoAlbumDao() {
        return new PhotoAlbumHibernateDao(repositoryConfig.hibernateTemplate(), PhotoAlbum.class);
    }

}
