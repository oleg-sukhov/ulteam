package ua.vn.os.ulteam.model.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import ua.vn.os.ulteam.model.dao.Crud;
import ua.vn.os.ulteam.model.entity.Entity;
import ua.vn.os.ulteam.model.entity.News;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author os
 */
public class GenericDao<T extends Entity> extends HibernateDaoSupport implements Crud<T> {

    private Class<T> type;

    public GenericDao(HibernateTemplate hibernateTemplate, Class<T> type) {
        this.type = type;
        setHibernateTemplate(hibernateTemplate);
    }

    @Override
    public Long saveOrUpdate(T entity) {
        return (Long)getHibernateTemplate().save(entity);
    }

    @Override
    public T get(Long id) {
        return  (T)getHibernateTemplate().get(type, id);
    }

    @Override
    public void update(T entity) {
        if(entity.getId() == null) {
            getHibernateTemplate().saveOrUpdate(entity);
        } else {
            getHibernateTemplate().refresh(entity);
        }
    }

    @Override
    public void delete(T entity) {
        getHibernateTemplate().delete(entity);
    }

    @Override
    public void delete(final Long id) {
        getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery("delete from "+ type.getCanonicalName() + " entity where entity.id = :id");
                query.setParameter("id", id);
                return query.executeUpdate();
            }
        });
    }

    @Override
    public long getCount() {
        List result = getHibernateTemplate().find("select count(entity) from " + type.getCanonicalName() + " entity");
        return (Long) result.get(0);
    }
}
