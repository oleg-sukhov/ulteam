package ua.vn.os.ulteam.model.dao.hibernate;

import org.hibernate.criterion.DetachedCriteria;
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
    public List<Season> getAllSeason() {
        return (List<Season>) getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(Season.class));
    }
}
