package ua.vn.os.ulteam.model.dao.hibernate;

import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
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
public class NewsHibernateDao extends GenericDao<News> implements NewsDao {

    public NewsHibernateDao(HibernateTemplate hibernateTemplate, Class<News> type) {
        super(hibernateTemplate, type);
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
}
