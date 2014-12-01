package ua.vn.os.ulteam.model.dao.hibernate;

import org.springframework.orm.hibernate4.HibernateTemplate;
import ua.vn.os.ulteam.model.dao.GenericDao;
import ua.vn.os.ulteam.model.dao.TourDao;
import ua.vn.os.ulteam.model.entity.Game;
import ua.vn.os.ulteam.model.entity.Tour;

import java.util.List;

/**
 * @author oleg.sukhov
 */
public class TourHibernateDao extends GenericDao<Tour> implements TourDao {
    public TourHibernateDao(HibernateTemplate hibernateTemplate, Class<Tour> type) {
        super(hibernateTemplate, type);
    }
}
