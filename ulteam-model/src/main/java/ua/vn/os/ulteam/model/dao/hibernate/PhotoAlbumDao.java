package ua.vn.os.ulteam.model.dao.hibernate;

import ua.vn.os.ulteam.model.dao.Crud;
import ua.vn.os.ulteam.model.entity.PhotoAlbum;

import java.util.List;

/**
 * @Author os
 */
public interface PhotoAlbumDao extends Crud<PhotoAlbum> {
    public static final int PAGE_START_INDEX = 0;
    public List<PhotoAlbum> getAllPhotoAlbums(int startPage, int numberOfPhotoAlbumsInPage);
    public long getPhotoAlbumsCount();
}
