package ua.vn.os.ulteam.model.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ua.vn.os.ulteam.model.dao.*;
import ua.vn.os.ulteam.model.dao.hibernate.*;
import ua.vn.os.ulteam.model.entity.*;

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

    @Bean
    public SeasonDao seasonDao() {
        return new SeasonHibernateDao(repositoryConfig.hibernateTemplate(), Season.class);
    }

    @Bean
    public TournamentDao tournamentDao() {
        return new TournamentHibernateDao(repositoryConfig.hibernateTemplate(), Tournament.class);
    }

}
