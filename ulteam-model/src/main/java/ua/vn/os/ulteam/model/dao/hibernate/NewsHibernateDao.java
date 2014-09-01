package ua.vn.os.ulteam.model.dao.hibernate;

import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.vn.os.ulteam.model.dao.NewsDao;
import ua.vn.os.ulteam.model.entity.News;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author os
 */
public class NewsHibernateDao extends HibernateDaoSupport implements NewsDao {

    public NewsHibernateDao() {
    }

    @Override
    public long createNews(News news) {
        return (Long)getHibernateTemplate().save(news);
    }

    @Override
    public News getNewsById(long id) {
        News news = getHibernateTemplate().get(News.class, id);

        if(news == null) {
            throw new EntityNotFoundException("News with id -> " + id + " not found in database!!!");
        }

        return news;
    }

    @Override
    public List<News> getAllNews() {
        List<News> newsList = (List<News>) getHibernateTemplate().find("from News");

        if(newsList == null || newsList.isEmpty()) {
            throw new EntityNotFoundException("Got empty news list from database!!!");
        }

        return newsList;
    }

    @Override
    public List<News> getAllNews(int startPage, int numberOfNews) {
        if(startPage < 1) {
            startPage = NewsDao.PAGE_START_INDEX;
        }

        DetachedCriteria criteria = DetachedCriteria.forClass(News.class);
        criteria.addOrder(Order.desc("id"));
        return (List<News>) getHibernateTemplate().findByCriteria(criteria, startPage, numberOfNews);

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
