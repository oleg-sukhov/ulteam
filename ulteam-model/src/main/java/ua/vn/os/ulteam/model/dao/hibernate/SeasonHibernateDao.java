package ua.vn.os.ulteam.model.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate4.HibernateTemplate;
import ua.vn.os.ulteam.model.dao.GenericDao;
import ua.vn.os.ulteam.model.dao.SeasonDao;
import ua.vn.os.ulteam.model.entity.Season;

import java.util.List;

/**
 * @author oleg.sukhov
 */
public class SeasonHibernateDao extends GenericDao<Season> implements SeasonDao {
    public SeasonHibernateDao(HibernateTemplate hibernateTemplate, Class<Season> type) {
        super(hibernateTemplate, type);
    }

    @Override
    public List<Season> getAllSeasons() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Season.class);
        criteria.addOrder(Order.desc("name"));
        return (List<Season>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public Season getSeasonByName(String seasonName) {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from Season where name = :name");
        query.setString("name", seasonName);

        List<Season> seasons = query.list();

        int seasonsSize = seasons.size();

        if(seasonsSize != 1) {
            String errorMessage = "Season name must be unique!!!";
            logger.error(errorMessage);
            //TODO: thing about custom type of exception
            throw new RuntimeException(errorMessage);
        }

        return seasons.get(0);
    }
}
