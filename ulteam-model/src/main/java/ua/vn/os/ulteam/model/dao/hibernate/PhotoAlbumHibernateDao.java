package ua.vn.os.ulteam.model.dao.hibernate;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import ua.vn.os.ulteam.model.entity.PhotoAlbum;

import java.util.List;

/**
 * @Author os
 */
public class PhotoAlbumHibernateDao extends HibernateDaoSupport implements PhotoAlbumDao {

    @Override
    public List<PhotoAlbum> getAllPhotoAlbums() {
        List<PhotoAlbum> photoAlbums = (List<PhotoAlbum>) getHibernateTemplate().find("from PhotoAlbum");
        return photoAlbums;
    }

    @Override
    public PhotoAlbum getPhotoAlbumById(long id) {
        return null;
    }
}
