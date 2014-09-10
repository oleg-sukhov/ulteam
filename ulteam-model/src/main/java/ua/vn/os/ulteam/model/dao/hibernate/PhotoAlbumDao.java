package ua.vn.os.ulteam.model.dao.hibernate;

import ua.vn.os.ulteam.model.entity.PhotoAlbum;

import java.util.List;

/**
 * @Author os
 */
public interface PhotoAlbumDao {
    public List<PhotoAlbum> getAllPhotoAlbums();
    public PhotoAlbum getPhotoAlbumById(long id);
}
