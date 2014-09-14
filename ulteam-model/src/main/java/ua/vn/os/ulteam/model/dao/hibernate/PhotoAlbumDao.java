package ua.vn.os.ulteam.model.dao.hibernate;

import ua.vn.os.ulteam.model.entity.PhotoAlbum;

import java.util.List;

/**
 * @Author os
 */
public interface PhotoAlbumDao {
    public static final int PAGE_START_INDEX = 0;
    public List<PhotoAlbum> getAllPhotoAlbums(int startPage, int numberOfPhotoAlbumsInPage);
    public PhotoAlbum getPhotoAlbumById(long id);
    public long getPhotoAlbumsCount();
}
