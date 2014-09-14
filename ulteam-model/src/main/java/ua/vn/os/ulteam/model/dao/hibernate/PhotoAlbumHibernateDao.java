package ua.vn.os.ulteam.model.dao.hibernate;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import ua.vn.os.ulteam.model.entity.PhotoAlbum;

import java.util.List;

/**
 * @Author os
 */
public class PhotoAlbumHibernateDao extends HibernateDaoSupport implements PhotoAlbumDao {

    @Override
    public List<PhotoAlbum> getAllPhotoAlbums(int startPage, int numberOfPhotoAlbumsInPage) {
        if(startPage < 1) {
            startPage = PhotoAlbumDao.PAGE_START_INDEX;
        }

        DetachedCriteria criteria = DetachedCriteria.forClass(PhotoAlbum.class);
        criteria.addOrder(Order.desc("id"));
        return (List<PhotoAlbum>) getHibernateTemplate().findByCriteria(criteria, startPage, numberOfPhotoAlbumsInPage);
    }

    @Override
    public PhotoAlbum getPhotoAlbumById(long id) {
        return null;
    }

    @Override
    public long getPhotoAlbumsCount() {
        List result = getHibernateTemplate().find("select count(photoAlbum) from PhotoAlbum photoAlbum");
        return (long) result.get(0);
    }
}
