package ua.vn.os.ulteam.model.dao.hibernate;

import org.hibernate.FetchMode;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
    public List<Tournament> getTournamentsInSeason(Season season) {
        DetachedCriteria criteria =
                DetachedCriteria.forClass(Tournament.class, "t")
                        .setFetchMode("season", FetchMode.JOIN)
                        .createAlias("t.season", "s")
                        .add(Restrictions.eq("s.name", season.getName()))
                        .addOrder(Order.desc("t.name"));


        return (List<Tournament>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public Tournament getTournament(String seasonName, String tournamentName) {
        DetachedCriteria criteria =
                DetachedCriteria.forClass(Tournament.class, "t")
                        .setFetchMode("season", FetchMode.JOIN)
                        .createAlias("t.season", "s")
                        .add(Restrictions.eq("s.name", seasonName))
                        .add(Restrictions.eq("t.name", tournamentName));

        List<Tournament> tournaments = (List<Tournament>) getHibernateTemplate().findByCriteria(criteria);

        if(tournaments.size() > 1) {
            String errorMessage = "Cannot be more than one tournament with the sae name in season";
            throw new RuntimeException(errorMessage);
        }

        return tournaments.get(0);
    }
}
