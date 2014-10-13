package ua.vn.os.ulteam.model.dao.hibernate;

import org.springframework.orm.hibernate4.HibernateTemplate;
import ua.vn.os.ulteam.model.dao.GameDao;
import ua.vn.os.ulteam.model.dao.GenericDao;
import ua.vn.os.ulteam.model.entity.Game;

import java.util.List;

/**
 * @author os
 */
public class GameHibernateDao extends GenericDao<Game> implements GameDao {
    public GameHibernateDao(HibernateTemplate hibernateTemplate, Class<Game> type) {
        super(hibernateTemplate, type);
    }

    @Override
    public List<Game> getAllGames() {
        return (List<Game>) getHibernateTemplate().find("from Game");
    }
}
