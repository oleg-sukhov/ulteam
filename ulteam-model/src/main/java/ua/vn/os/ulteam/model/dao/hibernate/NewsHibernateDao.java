package ua.vn.os.ulteam.model.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.vn.os.ulteam.model.dao.NewsDao;
import ua.vn.os.ulteam.model.entity.News;

import java.util.List;

/**
 * @author os
 */
public class NewsHibernateDao extends HibernateDaoSupport implements NewsDao {

    @Override
    public long createNews(News news) {
        return (Long)getHibernateTemplate().save(news);
    }

    @Override
    public News getNewsById(long id) {
        return getHibernateTemplate().get(News.class, id);
    }

    @Override
    public List<News> getAllNews() {
        return null;//(List<News>) getHibernateTemplate().find("from News");
    }

    @Override
    public void updateNews(News news) {
        if(news.getId() == null) {
            getHibernateTemplate().saveOrUpdate(news);
        } else {
            getHibernateTemplate().refresh(news);
        }
    }

    @Override
    public void deleteNewsById(final long id) {
        getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery("delete from News n where n.id = :id");
                query.setParameter("id", id);
                return query.executeUpdate();
            }
        });
    }

    @Override
    public long getNewsCount() {
        List result = getHibernateTemplate().find("select count(news) from News news");
        return (long) result.get(0);
    }
}
