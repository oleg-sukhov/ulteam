package ua.vn.os.ulteam.model.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ua.vn.os.ulteam.model.dao.GameDao;
import ua.vn.os.ulteam.model.dao.NewsDao;
import ua.vn.os.ulteam.model.dao.hibernate.GameHibernateDao;
import ua.vn.os.ulteam.model.dao.hibernate.NewsHibernateDao;
import ua.vn.os.ulteam.model.dao.PhotoAlbumDao;
import ua.vn.os.ulteam.model.dao.hibernate.PhotoAlbumHibernateDao;
import ua.vn.os.ulteam.model.entity.Game;
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

    @Bean
    public GameDao gameDao() {
        return new GameHibernateDao(repositoryConfig.hibernateTemplate(), Game.class);
    }

}
