package ua.vn.os.ulteam.model.dao.hibernate;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate4.HibernateTemplate;
import ua.vn.os.ulteam.model.dao.GenericDao;
import ua.vn.os.ulteam.model.dao.TournamentDao;
import ua.vn.os.ulteam.model.entity.Season;
import ua.vn.os.ulteam.model.entity.Tournament;

import java.util.List;

/**
 * @author oleg.sukhov
 */
public class TournamentHibernateDao extends GenericDao<Tournament> implements TournamentDao {

    public TournamentHibernateDao(HibernateTemplate hibernateTemplate, Class<Tournament> type) {
        super(hibernateTemplate, type);
    }

    @Override
    public List<Tournament> getAllTournaments() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tournament.class);
        criteria.addOrder(Order.desc("name"));
        return (List<Tournament>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<Tournament> getTournamentsInSeason(Season season) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Tournament.class);
        criteria.addOrder(Order.desc("name"))
                .setFetchMode("Season", FetchMode.JOIN);

        return (List<Tournament>) getHibernateTemplate().findByCriteria(criteria);
    }
}
