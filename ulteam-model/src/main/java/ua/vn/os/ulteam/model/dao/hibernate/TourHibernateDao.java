package ua.vn.os.ulteam.model.dao.hibernate;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateTemplate;
import ua.vn.os.ulteam.model.dao.GenericDao;
import ua.vn.os.ulteam.model.dao.TourDao;
import ua.vn.os.ulteam.model.entity.Game;
import ua.vn.os.ulteam.model.entity.Tour;
import ua.vn.os.ulteam.model.entity.Tournament;

import java.util.List;

/**
 * @author oleg.sukhov
 */
public class TourHibernateDao extends GenericDao<Tour> implements TourDao {
    public TourHibernateDao(HibernateTemplate hibernateTemplate, Class<Tour> type) {
        super(hibernateTemplate, type);
    }

    @Override
    public Tour getTour(String seasonName, String tournamentName, String tourName) {
        DetachedCriteria criteria =
                DetachedCriteria.forClass(Tour.class, "tr")
                        .createAlias("tr.tournament", "t")
                        .createAlias("t.season", "s")
                        .setFetchMode("tournament", FetchMode.JOIN)
                        .setFetchMode("t.season", FetchMode.JOIN)
                        .add(Restrictions.eq("s.name", seasonName))
                        .add(Restrictions.eq("t.name", tournamentName))
                        .add(Restrictions.eq("tr.name", tourName))
                        .addOrder(Order.desc("tr.name"));

        List<Tour> tours = (List<Tour>) getHibernateTemplate().findByCriteria(criteria);

        if(tours.size() > 1) {
            String errorMessage = "Cannot be more than one tour with the same name in tournament";
            throw new RuntimeException(errorMessage);
        }

        return tours.get(0);
    }
}
